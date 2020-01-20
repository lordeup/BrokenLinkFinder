package com.files;

public class Response {
  private final String url;
  private final Integer statusCode;
  private final String statusMessage;

  public Response(String url, Integer statusCode, String statusMessage) {
    this.url = url;
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
  }

  public String getUrl() {
    return url;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  @Override
  public boolean equals(Object arg) {
    if (arg instanceof Response) {
      Response result = (Response) arg;
      return url.equals(result.getUrl()) && statusCode.equals(result.getStatusCode()) && statusMessage.equals(result.getStatusMessage());
    } else {
      throw new IllegalArgumentException("Object not equals");
    }
  }
}
