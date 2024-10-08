package com.jeremias.shortenerurl.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jeremias.shortenerurl.exceptions.handlers.EntityBadRequestException;
import com.jeremias.shortenerurl.exceptions.handlers.EntityForbiddenException;
import com.jeremias.shortenerurl.exceptions.handlers.EntityNotFoundException;
import com.jeremias.shortenerurl.models.Url;
import com.jeremias.shortenerurl.repositories.UrlRepository;
import com.jeremias.shortenerurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public Url shortUrl(String baseUrl) {
        if (Objects.isNull(baseUrl) || baseUrl.isEmpty())
            throw new EntityBadRequestException("The url cannot be null!");

        String genId = generateShortUrl();
        Url url = Url.builder()
                .baseUrl(baseUrl)
                .shorterUrl(genId)
                .build();
        return this.urlRepository.save(url);
    }

    @Override
    public String getBaseUrl(String shortUrl) {
        Url url = this.urlRepository.findByShorterUrl(shortUrl);
        if (Objects.isNull(url)) throw new EntityNotFoundException("Url not found!");
        if (LocalDateTime.now().isAfter(url.getExpirationTime())) {
            this.urlRepository.delete(url);
            throw new EntityForbiddenException("Url has expired!");
        }
        return url.getBaseUrl();
    }

    @Override
    public BufferedImage generateQRCodeImage(String shortUrl) throws Exception {
        Url url = this.urlRepository.findByShorterUrl(shortUrl);
        if (Objects.isNull(url)) return null;
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(url.getBaseUrl(), BarcodeFormat.QR_CODE, 250, 250);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private String generateShortUrl() {
        StringBuilder builder = new StringBuilder(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                builder.append(random.nextInt(10));
            } else {
                builder.append((char) (random.nextInt(26) + 'a'));
            }
        }
        return builder.toString().toUpperCase();
    }

    @Scheduled(fixedRate = 1800000) // It´ll remove all expired links after 30 min
    public void cleanupExpiredUrls() {
        log.info("Cleaning up expired URLs...");
        List<Url> expiredUrls = urlRepository.findAllByExpirationTimeBefore(LocalDateTime.now());
        log.info(String.valueOf(expiredUrls.size()));
        urlRepository.deleteAll(expiredUrls);
        log.info("Expired URLs cleaned up!");
    }
}
