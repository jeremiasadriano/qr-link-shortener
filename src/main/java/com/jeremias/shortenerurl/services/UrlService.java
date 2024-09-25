package com.jeremias.shortenerurl.services;

import com.jeremias.shortenerurl.dtos.request.UrlRequest;

public interface UrlService {
    String shortURl(UrlRequest urlRequest);

    String genNewShortUrl(String shortUrl);

    String getBaseUrl(String shortUrl);
}
