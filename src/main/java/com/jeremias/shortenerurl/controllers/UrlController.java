package com.jeremias.shortenerurl.controllers;

import com.jeremias.shortenerurl.dtos.request.UrlRequest;
import com.jeremias.shortenerurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/url")
    public ResponseEntity<String> shortUrl(@RequestBody UrlRequest urlRequest) {
        return new ResponseEntity<>(this.urlService.shortUrl(urlRequest.url()), HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getBaseUrl(@PathVariable String shortUrl) {
        return new ResponseEntity<>(this.urlService.getBaseUrl(shortUrl), HttpStatus.OK);
    }

    @GetMapping("/qr/{shortUrl}")
    public ResponseEntity<BufferedImage> generateQRCodeImage(@PathVariable String shortUrl) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(this.urlService.generateQRCodeImage(shortUrl));
    }
}
