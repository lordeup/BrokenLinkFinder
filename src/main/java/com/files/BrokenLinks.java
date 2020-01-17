package com.files;

import java.io.*;
import java.net.HttpURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrokenLinks {
  private static final String URL_ADDRESS = "http://52.136.215.164/broken-links/";
  private static final Integer ERROR_CODE = 300;
  private static final Set<String> setLink = new HashSet<>();

  private static boolean isLinkAppropriate(String link) {
    List<String> forbidden_prefixes = Arrays.asList("#", "tel://", "mailto:");
    for (String prefix: forbidden_prefixes) {
      if (link.startsWith(prefix)) {
        return false;
      }
    }
    return true;
  }

  private static void getNewLinks(FileWriter invalidLinksFile, String url) {
    Document doc;
    HttpURLConnection urlConnection = null;
    try {
      doc = Jsoup.connect(url).get();
      Elements links = doc.select("a[href]");
      for (Element link : links) {
        URI uri = new URI(url);
        URI nextUri = uri.resolve(link.attr("href"));
        String nextUrl = nextUri.toString();
        if (setLink.contains(nextUrl) || !isLinkAppropriate(link.attr("href"))) {
          continue;
        }
        setLink.add(nextUrl);
        urlConnection = (HttpURLConnection) new URL(nextUrl).openConnection();
        int statusCode = urlConnection.getResponseCode();
        if (statusCode >= ERROR_CODE) {
          //запись в csv
          invalidLinksFile.write(nextUrl + " - " + statusCode + "\n");
          invalidLinksFile.flush();
          //
        }
        else if (uri.getHost() == nextUri.getHost()) {
            getNewLinks(invalidLinksFile, nextUrl);
        }
      }
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      FileWriter invalidLinksFile = new FileWriter("./testOutput/invalidLinks.txt");
      getNewLinks(invalidLinksFile, URL_ADDRESS);
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }
}
