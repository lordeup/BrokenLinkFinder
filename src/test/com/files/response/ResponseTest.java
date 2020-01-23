package com.files.response;

import com.files.TestConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    private Response response;

    @BeforeEach
    void init() {
        response = new Response(TestConst.getUrl(), TestConst.getStatusCode(), TestConst.getStatusMessage());
    }

    @Test
    void checkGetUrl() {
        String actual = response.getUrl();
        assertEquals(TestConst.getUrl(), actual);
    }

    @Test
    void checkGetStatusCode() {
        int actual = response.getStatusCode();
        assertEquals(TestConst.getStatusCode(), actual);
    }

    @Test
    void checkGetStatusMessage() {
        String actual = response.getStatusMessage();
        assertEquals(TestConst.getStatusMessage(), actual);
    }

    @Test
    void checkEqualsWithEqualParameters() {
        Response testResponse = new Response(TestConst.getUrl(), TestConst.getStatusCode(), TestConst.getStatusMessage());
        Boolean expected = response.equals(testResponse);
        assertEquals(true, expected);
    }

    @Test
    void checkEqualsWithNotEqualParameters() {
        assertThrows(IllegalArgumentException.class, () -> response.equals(4));
    }
}