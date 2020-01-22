package com.files.links;

import com.files.parser.ParserState;
import com.files.property.Property;
import org.jsoup.Connection;
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
import java.util.stream.Collectors;

public class Links {
  private final List<String> links;
  private final ParserState state;
  private final String page;
  private Document document;

  public Links(String page, ParserState state) {
    this.page = page;
    this.state = state;
    this.links = new ArrayList<>();
  }

  public void run() throws IOException, URISyntaxException {
    openDocument();

    Elements hrefLinks = document.select("[href]");
    Elements srcLinks = document.select("[src]");

    String uriPath = document.location();
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

  private void openDocument() throws IOException {
    switch (state) {
      case FILES:
        parse();
        return;
      case LINKS:
        connect();
        return;
      case UNDEFINED:
        throw new FileNotFoundException();
    }
  }

  private void connect() throws IOException {
    Property property = new Property();
    int connectionTimeout = property.getConnectionTimeout();
    document = Jsoup.connect(page).timeout(connectionTimeout).get();
  }

  private void parse() throws IOException {
    document = Jsoup.parse(new File(page), null);
  }

  private void addLinks(Elements elements, URI uri, String attributeKey) {
    for (Element link : elements) {
      String url = uri.resolve(link.attr(attributeKey)).toString();
      if (!links.contains(url)) {
        links.add(url);
      }
    }
  }
}
