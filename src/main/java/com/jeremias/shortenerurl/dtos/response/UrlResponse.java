package com.jeremias.shortenerurl.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UrlResponse(String baseUrl, String shortUrl, String qrUrl, LocalDateTime expirationTime) {
}
