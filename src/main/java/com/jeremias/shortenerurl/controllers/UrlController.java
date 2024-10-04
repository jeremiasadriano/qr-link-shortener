package com.jeremias.shortenerurl.controllers;

import com.jeremias.shortenerurl.dtos.request.UrlRequest;
import com.jeremias.shortenerurl.dtos.response.UrlResponse;
import com.jeremias.shortenerurl.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/url")
    public ResponseEntity<UrlResponse> shortUrl(@RequestBody UrlRequest urlRequest) {
        var url = this.urlService.shortUrl(urlRequest.url());
        UrlResponse response = UrlResponse.builder()
                .baseUrl(url.getBaseUrl())
                .shortUrl(url.getShorterUrl())
                .expirationTime(url.getExpirationTime())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public void getBaseUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        response.sendRedirect(this.urlService.getBaseUrl(shortUrl));
    }

    @GetMapping("/qr/{shortUrl}")
    public ResponseEntity<BufferedImage> generateQRCodeImage(@PathVariable String shortUrl) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(this.urlService.generateQRCodeImage(shortUrl));
    }
}
