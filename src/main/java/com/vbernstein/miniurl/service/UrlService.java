package com.vbernstein.miniurl.service;

import java.util.Optional;

import com.vbernstein.miniurl.repository.UrlRepository;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    // 36^8 = 2,821,109,907,456 aka 2 trillion unique URLs
    // 36^6 = 2,176,782,336 aka 2 billion -- good enough?
    private static final int MINI_URL_LENGTH = 6;

    private UrlRepository urlRepository;

    UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String urlToShorten) {
        // TODO: Validation
        return "";
    }
    public String redirectUrl(String miniUrl) throws Exception {
        if (!isValidMini(miniUrl)) {
            throw new RuntimeException("Bad input"); // TODO: Not RE
        }
        miniUrl = miniUrl.toLowerCase(); // Want to handle upper-case letters as lower-case
        Optional<String> fullUrl = urlRepository.getByMini(miniUrl);
        if (fullUrl.isEmpty()) {
            throw new RuntimeException("Nothing stored for mini URL"); // TODO: Throw something
        } else {
            return fullUrl.get();
        }
    }

    private boolean isValidMini(String input) {
        System.out.println("In ivm");
        int length = input.length();
        if (length != MINI_URL_LENGTH) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (!Character.isAlphabetic(c) || !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}