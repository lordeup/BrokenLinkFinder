package com.files.httpcall;

import com.files.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpCallTest {
    @Test
    void checkResponseWithValidUrl() {
        String url = "https://www.google.com/";
        Response response = new Response(url, 200, "OK");
        HttpCall httpCall = new HttpCall(url);
        assertEquals(response, httpCall.call());
    }

    @Test
    void checkResponseWithNotValidUrl() {
        String url = "https://www.test.ru/";
        Response response = new Response(url, 522, "Read timeout");
        HttpCall httpCall = new HttpCall(url);
        assertEquals(response, httpCall.call());
    }
}