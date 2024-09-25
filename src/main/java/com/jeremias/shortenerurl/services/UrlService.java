package com.jeremias.shortenerurl.services;

import java.awt.image.BufferedImage;

public interface UrlService {
    String shortUrl(String baseUrl);

    String getBaseUrl(String shortUrl);

    BufferedImage generateQRCodeImage(String shortUrl) throws Exception;
}
