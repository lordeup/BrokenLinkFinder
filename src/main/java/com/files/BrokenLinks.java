package com.files;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinks {
    private List<Response> brokenLinks = new ArrayList<>();
    private static final int ERROR_CODE = 300;

    public BrokenLinks(List<String> pages, ParserState state) throws URISyntaxException, IOException {
        for (String page : pages) {
            List<Response> brokenLinksOfPage = getBrokenLinksOfPage(page, state);
            for (Response brokenLink: brokenLinksOfPage) {
                if (!brokenLinks.contains(brokenLink)){
                    brokenLinks.add(brokenLink);
                    brokenLinksCount++;
                }
            }
        }
    }

    public List<Response> getBrokenLinks() {
        return brokenLinks;
    }

    public int getBrokenLinksCount() {
        return brokenLinksCount;
    }

    private int brokenLinksCount = 0;

    private List<Response> getBrokenLinksOfPage(String page, ParserState state) throws URISyntaxException, IOException {
        List<Response> result = new ArrayList<>();
        List<String> links = new Links(page, state).getLinks();
        for(String link: links) {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(link).openConnection();
            if (urlConnection.getResponseCode() >= ERROR_CODE) {
                int statusCode = urlConnection.getResponseCode();
                String statusMessage = urlConnection.getResponseMessage();
                result.add(new Response(link, statusCode, statusMessage));
            }
        }
        return result;
    }
}
