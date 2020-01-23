package com.files.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    private Response response;
    private final String testUrl = "http://htmlbook.ru/images/logo.gif";
    private final String testStatusMessage = "Not Found";
    private final int testStatusCode = 404;

    @BeforeEach
    void init() {
        response = new Response(testUrl, testStatusCode, testStatusMessage);
    }

    @Test
    void checkGetUrl() {
        String actual = response.getUrl();
        assertEquals(testUrl, actual);
    }

    @Test
    void checkGetStatusCode() {
        int actual = response.getStatusCode();
        assertEquals(testStatusCode, actual);
    }

    @Test
    void checkGetStatusMessage() {
        String actual = response.getStatusMessage();
        assertEquals(testStatusMessage, actual);
    }

    @Test
    void checkEqualsWithEqualParameters() {
        Response testResponse = new Response(testUrl, testStatusCode, testStatusMessage);
        Boolean expected = response.equals(testResponse);
        assertEquals(true, expected);
    }

    @Test
    void checkEqualsWithNotEqualParameters() {
        assertThrows(IllegalArgumentException.class, () -> response.equals(4));
    }
}