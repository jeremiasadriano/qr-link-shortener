package com.jeremias.shortenerurl.services;

public interface UrlService {
    String shortURl(String baseUrl);

    String getBaseUrl(String shortUrl);
}
