package com.files.brokenlinks;

import com.files.httpcall.HttpCall;
import com.files.property.Property;
import com.files.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BrokenLinks {
  private final List<String> links;
  private final List<Response> brokenLinks;

  private static final Integer ERROR_CODE = 300;

  public BrokenLinks(List<String> links) {
    this.links = links;
    this.brokenLinks = new ArrayList<>();
  }

  public void findBrokenLinks() throws IOException, InterruptedException, ExecutionException {
    Property property = new Property();

    ExecutorService executorService = Executors.newFixedThreadPool(property.getThreadNumber());

    List<HttpCall> httpCalls = new ArrayList<>();
    for (String link : links) {
      httpCalls.add(new HttpCall(link));
    }

    List<Future<Response>> httpCallsResponse = executorService.invokeAll(httpCalls);
    executorService.shutdown();

    for (Future<Response> httpCallResponse : httpCallsResponse) {
      Response httpResponse = httpCallResponse.get();
      if (httpResponse.getStatusCode() >= ERROR_CODE) {
        brokenLinks.add(httpResponse);
      }
    }
  }

  public List<Response> getBrokenLinks() {
    return this.brokenLinks;
  }
}
