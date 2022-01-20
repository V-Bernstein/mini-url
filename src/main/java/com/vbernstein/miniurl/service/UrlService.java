package com.vbernstein.miniurl.service;

import com.vbernstein.miniurl.repository.UrlRepository;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private UrlRepository urlRepository;    

    UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String urlToShorten) {
        // TODO: Validation
        return "";
    }

    public String redirectUrl(String shortenedUrl) {
        // TODO: Validation
        return "";
    }
}