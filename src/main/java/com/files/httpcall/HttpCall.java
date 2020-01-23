package com.files.httpcall;

import com.files.property.Property;
import com.files.response.Response;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpCall implements Callable<Response> {
    private String url;

    public HttpCall(String url) {
        this.url = url;
    }

    @Override
    public Response call() {
        try {
            Property property = new Property();
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setReadTimeout(property.getConnectionTimeout());
            urlConnection.setInstanceFollowRedirects(false);

            int statusCode = urlConnection.getResponseCode();
            String statusMessage = urlConnection.getResponseMessage();

            return new Response(url, statusCode, statusMessage);
        } catch (Exception ex) {
            return new Response(url, 522, "Read timeout");
        }
    }
}
