package com.files;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Links {
    public Links(String page, ParserState state) throws URISyntaxException, IOException {
        openDocument(page, state);
        Elements hrefLinks = document.select("[href]");
        Elements srcLinks = document.select("[src]");
        String uriPath = document.location().toString();
        if (state == ParserState.FILES) {
            uriPath = "file:" + uriPath.replace("\\", "/");
        }
        URI uri = new URI(uriPath);
        addLinks(hrefLinks, uri, "href");
        addLinks(srcLinks, uri, "src");
    }

    public List<String> getLinks() {
        return links;
    }

    private List<String> links = new ArrayList<>();
    private Document document;

    private void openDocument(String link, ParserState state) throws IOException {
        switch (state) {
            case FILES:
                File file = new File(link);
                document = Jsoup.parse(file, null);
                break;
            case LINKS:
                document = Jsoup.connect(link).get();
                break;
            case UNDEFINED:
                throw new FileNotFoundException();
        }
    }

    private void addLinks(Elements elements, URI uri, String attributeKey) {
        for (Element link : elements) {
            String nextUrl = uri.resolve(link.attr(attributeKey)).toString();
            if (!links.contains(nextUrl)) {
                links.add(nextUrl);
            }
        }
    }
}
