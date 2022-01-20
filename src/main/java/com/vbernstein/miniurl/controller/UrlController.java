package com.vbernstein.miniurl.controller;

import com.vbernstein.miniurl.service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private UrlService urlService;

    UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/shorten/{url}")
    String shortenUrl(@PathVariable("url") String urlToShorten) {
        return this.urlService.shortenUrl(urlToShorten);
    }

    @GetMapping("/redirect/{url}")
    String redirectUrl(@PathVariable("url") String shortenedUrl) {
        return this.urlService.redirectUrl(shortenedUrl);
    }
}
