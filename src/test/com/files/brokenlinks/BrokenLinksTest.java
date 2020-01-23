package com.files.brokenlinks;

import com.files.TestConst;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class BrokenLinksTest {
    @Test
    void checkFindBrokenLinks() throws InterruptedException, ExecutionException, IOException {
        BrokenLinks brokenLinks = new BrokenLinks(TestConst.getLinksForParseStateFiles());
        brokenLinks.findBrokenLinks();
        assertEquals(TestConst.getBrokenLinks(), brokenLinks.getBrokenLinks());
    }
}