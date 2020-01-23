package com.files.property;

import com.files.TestConst;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    @Test
    void checkGetConnectionTimeout() throws IOException {
        Property property = new Property();
        int expected = TestConst.getConnectionTimeout();
        int actual = property.getConnectionTimeout();
        assertEquals(expected, actual);
    }

    @Test
    void checkGetThreadNumber() throws IOException {
        Property property = new Property();
        int expected = TestConst.getThreadNumber();
        int actual = property.getThreadNumber();
        assertEquals(expected, actual);
    }
}