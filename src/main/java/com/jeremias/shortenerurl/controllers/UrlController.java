package com.jeremias.shortenerurl.controllers;

import com.jeremias.shortenerurl.dtos.request.UrlRequest;
import com.jeremias.shortenerurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/url")
    public String shortUrl(@RequestBody UrlRequest urlRequest) {
        return this.urlService.shortURl(urlRequest.baseUrl());
    }

    @GetMapping("/{shortUrl}")
    public String getBaseUrl(@PathVariable String shortUrl) {
        return this.urlService.getBaseUrl(shortUrl);
    }
}
