package com.jeremias.shortenerurl.services;

public interface UrlService {
    String shortUrl(String baseUrl);

    String getBaseUrl(String shortUrl);
}
