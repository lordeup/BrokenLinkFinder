package com.files;

import java.io.FileWriter;
import java.io.IOException;

public class Report {
  private final String filename;

  public Report(String filename) {
    this.filename = filename;
  }

  public void write(BrokenLinks brokenLinks) throws IOException {
    try (FileWriter fileWriter = new FileWriter(filename)) {
      for (Response brokenLink : brokenLinks.getBrokenLinks()) {
        String str = brokenLink.getUrl() + ", " + brokenLink.getStatusCode() + ", " + brokenLink.getStatusMessage() + "\n";

        fileWriter.write(str);
        fileWriter.flush();
      }
      Integer brokenLinksCount = brokenLinks.getBrokenLinksCount();
      System.out.println(brokenLinksCount > 0
              ? "Found " + brokenLinksCount + " broken links, for details check file '" + filename + "'"
              : "Not found broken links");
    }
  }
}
