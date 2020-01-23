package com.files.links;

import com.files.TestConst;
import com.files.parser.ParserState;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class LinksTest {
    @Test
    void checkFindLinksWithParserStateFiles() throws IOException, URISyntaxException {
        Links links = new Links("input.html", ParserState.FILES);
        links.findLinks();
        assertEquals(TestConst.generateLinksForParseStateFiles(), links.getLinks());
    }

    @Test
    void checkFindLinksWithParserStateLinks() throws IOException, URISyntaxException {
        Links links = new Links("https://ww.ru/", ParserState.LINKS);
        links.findLinks();
        assertEquals(TestConst.generateLinksForParseStateLinks(), links.getLinks());
    }

    @Test
    void checkFindLinksWithParserStateUndefined() throws IOException, URISyntaxException {
        Links links = new Links("https://ww.ru/", ParserState.UNDEFINED);
        assertThrows(FileNotFoundException.class, links::findLinks);
    }
}