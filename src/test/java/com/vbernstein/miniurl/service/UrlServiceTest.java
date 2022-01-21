package com.vbernstein.miniurl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.vbernstein.miniurl.repository.UrlRepository;
import com.vbernstein.miniurl.service.UrlService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlServiceTest {

    private UrlRepository mockUrlRepository;
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        mockUrlRepository = mock(UrlRepository.class);
        urlService = new UrlService(mockUrlRepository);
    }

    @Test
    void shortenUrl_calls_huh() {
        String input = "http://someUrl.com/";
        String expected = "";

        String actual = urlService.shortenUrl(input);
        assertEquals(expected, actual);
    }

    @Test
    void redirectUrl_throws_exception_for_short_input() {
        String input = "abcd3";
        assertThrows(RuntimeException.class, () -> urlService.redirectUrl(input));
    }

    @Test
    void redirectUrl_throws_exception_for_invalid_input() {
        String input = "ab#cd3";
        assertThrows(RuntimeException.class, () -> urlService.redirectUrl(input));
    }

    @Test
    void redirectUrl_throws_exception_for_nonexistant_url() {
        String input = "abcde1";
        when(mockUrlRepository.getByMini(input)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> urlService.redirectUrl(input));
        verify(mockUrlRepository).getByMini(input);
    }

    @Test
    void redirectUrl_returns_fullUrl() {
        String input = "abcde1";
        String expected = "www.wee.com";
        when(mockUrlRepository.getByMini(input)).thenReturn(Optional.of(expected));
        try {
            String actual = urlService.redirectUrl(input);
            assertEquals(expected, actual);
            verify(mockUrlRepository).getByMini(input);
        } catch (Exception e) {}
    }
}
