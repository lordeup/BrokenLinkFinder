package com.files.brokenlinks;

import com.files.links.Links;
import com.files.parser.ParserState;
import com.files.property.Property;
import com.files.response.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinks {
  private final List<String> pages;
  private final List<Response> brokenLinks;
  private final ParserState state;

  private static final Integer ERROR_CODE = 300;

  public BrokenLinks(List<String> pages, ParserState state) {
    this.pages = pages;
    this.state = state;
    this.brokenLinks = new ArrayList<>();
  }

  public void run() throws IOException, URISyntaxException {
    for (String page : pages) {
      for (Response brokenLink : getBrokenLinksOfPage(page)) {
        if (!brokenLinks.contains(brokenLink)) {
          addBrokenLink(brokenLink);
        }
      }
    }
  }

  public List<Response> getBrokenLinks() {
    return brokenLinks;
  }

  public Integer getBrokenLinksCount() {
    return brokenLinks.size();
  }

  private void addBrokenLink(Response brokenLink) {
    brokenLinks.add(brokenLink);
  }

  // TODO подумать как упростить
  private List<Response> getBrokenLinksOfPage(String page) throws URISyntaxException, IOException {
    List<Response> result = new ArrayList<>();

    Links links = new Links(page, state);
    links.run();

    Property property = new Property();

    for (String link : links.getLinks()) {
      try {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(link).openConnection();
        urlConnection.setReadTimeout(property.getConnectionTimeout());
        urlConnection.setInstanceFollowRedirects(false);

        if (urlConnection.getResponseCode() >= ERROR_CODE) {
          int statusCode = urlConnection.getResponseCode();
          String statusMessage = urlConnection.getResponseMessage();
          Response response = new Response(link, statusCode, statusMessage);
          result.add(response);
        }
      } catch (Exception ex) {
        Response response = new Response(link, 522, "Read timeout");
        result.add(response);
      }
    }
    return result;
  }
}
