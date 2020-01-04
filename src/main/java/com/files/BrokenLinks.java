package com.files;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrokenLinks {
  private static final String HTTP = "http";
  private static final String URL_HOST = HTTP + "://52.136.215.164";
  private static final String URL_ADDRESS = URL_HOST + "/broken-links/";
  private static final String HTTP_REGEX = "<a.*href=\"([^#][^@]*?)\".*>";
  private static final Integer ERROR_CODE = 300;
  private static final Set<String> setLink = new HashSet<>();
  private static Integer countValidLink = 0, countInvalidLink = 0;

  private static void writeLink(final String url, HttpURLConnection urlConnection, FileWriter file) {
    try {
      String str = url + ' ' + urlConnection.getResponseCode() + ' ' + urlConnection.getResponseMessage() + "\n";
      file.write(str);
      file.flush();
    } catch (Exception error) {
      error.printStackTrace();
    }
  }

  private static void validationCheck(FileWriter validLinksFile, FileWriter invalidLinksFile, String url) {
    HttpURLConnection urlConnection = null;
    StringBuilder response = new StringBuilder();

    try {
      urlConnection = (HttpURLConnection) new URL(url).openConnection();
      if (urlConnection.getResponseCode() > ERROR_CODE) {
        writeLink(url, urlConnection, invalidLinksFile);
        ++countInvalidLink;
        return;
      }
      writeLink(url, urlConnection, validLinksFile);
      ++countValidLink;
    } catch (Exception error) {
      error.printStackTrace();
    }

    try {
      InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
      char[] iss = new char[100];
      while (true) {
        int index = streamReader.read(iss, 0, iss.length);
        if (index < 0) {
          break;
        }
        response.append(iss, 0, index);
      }
      streamReader.close();
    } catch (Exception error) {
      error.printStackTrace();
    }

    Matcher matcher = Pattern.compile(HTTP_REGEX).matcher(response.toString());

    while (matcher.find()) {
      String value = matcher.group(1);
      String urlStr = URL_ADDRESS + value;
      if (!setLink.contains(value)) {
        setLink.add(value);
        if (!value.startsWith(HTTP)) {
          validationCheck(validLinksFile, invalidLinksFile, urlStr);
        }
      }
    }
  }

  private static void printInfoLink(FileWriter file, Integer count) {
    try {
      Date date = new Date();
      String str = "\n" + "Count link: " + count.toString() + "\n" + "Date check: " + date.toString();
      file.write(str);
      file.flush();
      file.close();
    } catch (Exception error) {
      error.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      FileWriter validLinksFile = new FileWriter("./testOutput/validLinks.txt");
      FileWriter invalidLinksFile = new FileWriter("./testOutput/invalidLinks.txt");

      validationCheck(validLinksFile, invalidLinksFile, URL_ADDRESS);

      printInfoLink(validLinksFile, countValidLink);
      printInfoLink(invalidLinksFile, countInvalidLink);

    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }
}
