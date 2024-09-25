package com.jeremias.shortenerurl.services.impl;

import com.jeremias.shortenerurl.models.Url;
import com.jeremias.shortenerurl.repositories.UrlRepository;
import com.jeremias.shortenerurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public String shortURl(String baseUrl) {
        if (baseUrl.isEmpty()) throw new NullPointerException("URL is empty");
        Url url = Url.builder()
                .baseUrl(baseUrl)
                .shorterUrl(generateShortUrl())
                .build();
        return this.urlRepository.save(url).getShorterUrl();
    }

    @Override
    public String getBaseUrl(String shortUrl) {
        Url url = this.urlRepository.findByShorterUrl(shortUrl);
        if (url.getBaseUrl().isEmpty()) throw new IllegalArgumentException("URL not found!");
        if (LocalDateTime.now().isAfter(url.getExpirationTime())) {
            this.urlRepository.delete(url);
        }
        return url.getBaseUrl();
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
