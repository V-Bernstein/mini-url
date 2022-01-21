package com.vbernstein.miniurl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vbernstein.miniurl.service.UrlService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlControllerTest {

    private UrlService mockUrlService;

    UrlController urlController;

    @BeforeEach
    void setUp() {
        mockUrlService = mock(UrlService.class);
        urlController = new UrlController(mockUrlService);
    }

    @Test
    void shortenUrl_calls_service() {
        String input = "http://someUrl.com/";
        String expected = "http://short.com/";
        when(mockUrlService.shortenUrl(input)).thenReturn(expected);

        String actual = urlController.shortenUrl(input);
        assertEquals(expected, actual);
        verify(mockUrlService).shortenUrl(input);
    }

    // @Test
    // void redirectUrl_calls_service() {
    //     String input = "http://short.com/";
    //     String expected = "http://someUrl.com/";
    //     when(mockUrlService.redirectUrl(input)).thenReturn(expected);

    //     String actual = urlController.redirectUrl(input);
    //     assertEquals(expected, actual);
    //     verify(mockUrlService).redirectUrl(input);
    // }
}
