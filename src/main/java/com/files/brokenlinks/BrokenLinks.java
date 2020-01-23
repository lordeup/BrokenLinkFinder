package com.files.brokenlinks;

import com.files.httpcall.HttpCall;
import com.files.links.Links;
import com.files.parser.ParserState;
import com.files.property.Property;
import com.files.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

  public void run() throws IOException, URISyntaxException, ExecutionException, InterruptedException {
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

  private List<Response> getBrokenLinksOfPage(String page) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
    Property property = new Property();

    Links links = new Links(page, state);
    links.run();

    ExecutorService executorService = Executors.newFixedThreadPool(property.getThreadNumber());

    List<HttpCall> httpCalls = new ArrayList<>();
    for (String link : links.getLinks()) {
      httpCalls.add(new HttpCall(link));
    }

    List<Future<Response>> httpCallsResponse = executorService.invokeAll(httpCalls);
    executorService.shutdown();

    return findBrokenLinks(httpCallsResponse);
  }

  private List<Response> findBrokenLinks(List<Future<Response>> httpCallsResponse) throws ExecutionException, InterruptedException {
    List<Response> brokenLinks = new ArrayList<>();

    for (Future<Response> httpCallResponse : httpCallsResponse) {
      Response httpResponse = httpCallResponse.get();
      if (httpResponse.getStatusCode() >= ERROR_CODE) {
        brokenLinks.add(httpResponse);
      }
    }

    return brokenLinks;
  }
}
