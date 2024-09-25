package com.jeremias.shortenerurl.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jeremias.shortenerurl.models.Url;
import com.jeremias.shortenerurl.repositories.UrlRepository;
import com.jeremias.shortenerurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public String shortUrl(String baseUrl) {
        if (baseUrl.isEmpty()) return "URL is empty";
        Url url = Url.builder()
                .baseUrl(baseUrl)
                .shorterUrl(generateShortUrl())
                .build();
        return this.urlRepository.save(url).getShorterUrl();
    }

    @Override
    public String getBaseUrl(String shortUrl) {
        Url url = this.urlRepository.findByShorterUrl(shortUrl);
        if (Objects.isNull(url)) return "URL not found!";
        if (LocalDateTime.now().isAfter(url.getExpirationTime())) {
            this.urlRepository.delete(url);
            return "URL expired!";
        }
        return url.getBaseUrl();
    }

    @Override
    public BufferedImage generateQRCodeImage(String shortUrl) throws Exception {
        Url url = this.urlRepository.findByShorterUrl(shortUrl);
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(url.getBaseUrl(), BarcodeFormat.QR_CODE, 200, 200);
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
}
