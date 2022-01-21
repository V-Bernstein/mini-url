package com.vbernstein.miniurl.service;

import java.util.Optional;

import com.vbernstein.miniurl.entity.UrlEntity;
import com.vbernstein.miniurl.repository.UrlRepository;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    // 36^6 = 2,176,782,336 aka 2 billion
    private static final int MINI_URL_LENGTH = 6;
    private static final String FIRST_MINI_URL = "aaaaaa";

    private UrlRepository urlRepository;

    UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String urlToShorten) {
        Optional<UrlEntity> mostRecentOpt = urlRepository.getLatestRecord();
        UrlEntity toSave = new UrlEntity();
        toSave.setLongUrl(urlToShorten);
        if (mostRecentOpt.isEmpty()) { // Table is empty
            toSave.setMiniUrl(FIRST_MINI_URL);
        } else {
            UrlEntity mostRecent  = mostRecentOpt.get();
            String newMiniUrl = incrementMini(mostRecent.getMiniUrl());
            Optional<UrlEntity> miniPreexists = urlRepository.getByMini(newMiniUrl);
            if (miniPreexists.isPresent()) {
                // If it already exists we want to update instead of delete & insert
                toSave.setId(miniPreexists.get().getId());
            }
            toSave.setMiniUrl(newMiniUrl);            
        }
        UrlEntity savedEntity = urlRepository.save(toSave); // TODO: Handle exceptions
        return savedEntity.getMiniUrl();
    }

    public String redirectUrl(String miniUrl) throws Exception {
        if (!isValidMini(miniUrl)) {
            throw new RuntimeException("Bad input"); // TODO: Not RE
        }
        miniUrl = miniUrl.toLowerCase(); // Want to handle upper-case letters as lower-case
        Optional<UrlEntity> fullUrl = urlRepository.getByMini(miniUrl);
        if (fullUrl.isEmpty()) {
            throw new RuntimeException("Nothing stored for mini URL"); // TODO: Throw something
        } else {
            return fullUrl.get().getLongUrl();
        }
    }

    private String incrementMini(String mini) {
        boolean shouldIncrement = true;
        char[] newMini = new char[MINI_URL_LENGTH];
        for (int i = mini.length() - 1; i >= 0; i--) {
            char c = mini.charAt(i);
            if (!shouldIncrement) {
                newMini[i] = c;
                continue;
            }
            if ((c >= 'a' && c < 'z') || (c >= '0' && c < '9')) { // Just increment
                newMini[i] = c++;
                shouldIncrement = false;
            } else if (c == 'z') {
                newMini[i] = '0';
                shouldIncrement = false;
            } else if (c == '9') { // We want to wrap to 'a', and increment the next char also
                newMini[i] = 'a';
            } else {
                // All minis are created by the application, so values *should* be within the expected bounds...
                System.out.println("DEBUG: mini value out of bounds"); // Would be a log in a professional app
            }
        }
        return new String(newMini);
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