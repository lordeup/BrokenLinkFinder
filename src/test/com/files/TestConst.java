package com.files;

import com.files.response.Response;

import java.util.ArrayList;
import java.util.List;

public class TestConst {
    private static final List<String> linksForParseStateFiles = new ArrayList<>();
    private static final List<String> linksForParseStateLinks = new ArrayList<>();
    private static final List<Response> brokenLinks = new ArrayList<>();

    public static List<String> generateLinksForParseStateFiles() {
        linksForParseStateFiles.add("http://52.136.215.164/broken-links/");
        linksForParseStateFiles.add("http://52.136.215.164/links/locked.php");
        linksForParseStateFiles.add("http://52.136.215.164/links/movedper.php");
        linksForParseStateFiles.add("http://52.136.215.164/links/404not404found404.php");
        linksForParseStateFiles.add("http://52.136.215.164/links/found302.php");
        linksForParseStateFiles.add("http://52.136.215.164/links/return500bad.php");
        linksForParseStateFiles.add("http://52.136.215.164/links/un503availible.php");
        linksForParseStateFiles.add("https://sun1-91.userapi.com/c824504/v824504398/16cb18/hK_NWFewmSg.jpg?ava=1");
        linksForParseStateFiles.add("http://htmlbook.ru/images/logo.gif");
        return linksForParseStateFiles;
    }

    public static List<String> generateLinksForParseStateLinks() {
        linksForParseStateLinks.add("https://ww.ru/theme/frontend/app/build/css/main.css?ver=51");
        linksForParseStateLinks.add("https://ww.ru/favicon.ico");
        linksForParseStateLinks.add("https://maps.googleapis.com/maps/api/js?key=AIzaSyA-GGF4OaFuMEdPsPWsMOwxYIfyMjE81Pc");
        linksForParseStateLinks.add("https://mc.yandex.ru/watch/52349893");
        linksForParseStateLinks.add("https://www.googletagmanager.com/gtag/js?id=UA-134344239-1");
        linksForParseStateLinks.add("https://ww.ru/theme/frontend/app/build/js/main.js?ver=51");
        return linksForParseStateLinks;
    }

    public static List<Response> generateBrokenLinks() {
        brokenLinks.add(new Response("http://52.136.215.164/links/locked.php", 423, "Locked"));
        brokenLinks.add(new Response("http://52.136.215.164/links/movedper.php", 301, "Moved Permanently"));
        brokenLinks.add(new Response("http://52.136.215.164/links/404not404found404.php", 404, "Not Found"));
        brokenLinks.add(new Response("http://52.136.215.164/links/found302.php", 302, "Found"));
        brokenLinks.add(new Response("http://52.136.215.164/links/return500bad.php", 500, "Internal Server Error"));
        brokenLinks.add(new Response("http://52.136.215.164/links/un503availible.php", 503, "Service Unavailable"));
        brokenLinks.add(new Response("http://htmlbook.ru/images/logo.gif", 404, "Not Found"));
        return brokenLinks;
    }

}
