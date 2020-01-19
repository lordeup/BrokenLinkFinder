package com.files;

public class Response {
    public Response(String url, Integer statusCode, String statusMessage) {
        this.url = url;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getUrl() {
        return url;
    }


    private String url;
    private String statusMessage;
    private Integer statusCode;
}
