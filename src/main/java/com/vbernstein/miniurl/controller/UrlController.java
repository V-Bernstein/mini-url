package com.vbernstein.miniurl.controller;

import com.vbernstein.miniurl.service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private UrlService urlService;

    UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    String shortenUrl(@RequestBody String urlToShorten) {
        return this.urlService.shortenUrl(urlToShorten);
    }

    @GetMapping("/{url}")
    String redirectUrl(@PathVariable("url") String shortenedUrl) {
        try {
            return this.urlService.redirectUrl(shortenedUrl); // TODO: Test errors, and return correct responses
        } catch (Exception e) {
            // TODO
            return "";
        }
    }
}
