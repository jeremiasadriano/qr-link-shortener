package com.jeremias.shortenerurl.services;

import com.jeremias.shortenerurl.models.Url;

import java.awt.image.BufferedImage;

public interface UrlService {
    Url shortUrl(String baseUrl);

    String getBaseUrl(String shortUrl);

    BufferedImage generateQRCodeImage(String shortUrl) throws Exception;
}
