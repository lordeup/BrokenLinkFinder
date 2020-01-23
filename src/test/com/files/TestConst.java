package com.files;

import com.files.response.Response;

import java.util.Arrays;
import java.util.List;

public class TestConst {
    private static final List<String> linksForParseStateFiles = Arrays.asList(
            "http://52.136.215.164/broken-links/",
            "http://52.136.215.164/links/locked.php",
            "http://52.136.215.164/links/movedper.php",
            "http://52.136.215.164/links/404not404found404.php",
            "http://52.136.215.164/links/found302.php",
            "http://52.136.215.164/links/return500bad.php",
            "http://52.136.215.164/links/un503availible.php",
            "https://sun1-91.userapi.com/c824504/v824504398/16cb18/hK_NWFewmSg.jpg?ava=1",
            "http://htmlbook.ru/images/logo.gif"
    );

    public static List<String> getLinksForParseStateFiles() {
        return linksForParseStateFiles;
    }

    private static final List<String> linksForParseStateLinks = Arrays.asList(
            "https://ww.ru/theme/frontend/app/build/css/main.css?ver=51",
            "https://ww.ru/favicon.ico",
            "https://maps.googleapis.com/maps/api/js?key=AIzaSyA-GGF4OaFuMEdPsPWsMOwxYIfyMjE81Pc",
            "https://mc.yandex.ru/watch/52349893",
            "https://www.googletagmanager.com/gtag/js?id=UA-134344239-1",
            "https://ww.ru/theme/frontend/app/build/js/main.js?ver=51"
    );

    public static List<String> getLinksForParseStateLinks() {
        return linksForParseStateLinks;
    }

    private static final List<Response> brokenLinks = Arrays.asList(
            new Response("http://52.136.215.164/links/locked.php", 423, "Locked"),
            new Response("http://52.136.215.164/links/movedper.php", 301, "Moved Permanently"),
            new Response("http://52.136.215.164/links/404not404found404.php", 404, "Not Found"),
            new Response("http://52.136.215.164/links/found302.php", 302, "Found"),
            new Response("http://52.136.215.164/links/return500bad.php", 500, "Internal Server Error"),
            new Response("http://52.136.215.164/links/un503availible.php", 503, "Service Unavailable"),
            new Response("http://htmlbook.ru/images/logo.gif", 404, "Not Found")
    );

    public static List<Response> getBrokenLinks() {
        return brokenLinks;
    }

    private static final int connectionTimeout = 10000;

    public static int getConnectionTimeout() {
        return connectionTimeout;
    }

    private static final int threadNumber = 10;

    public static int getThreadNumber() {
        return threadNumber;
    }

    private static final String url = "http://htmlbook.ru/images/logo.gif";

    public static String getUrl() {
        return url;
    }

    private static final int statusCode = 404;

    public static int getStatusCode() {
        return statusCode;
    }

    private static final String statusMessage = "Not Found";

    public static String getStatusMessage() {
        return statusMessage;
    }
}
