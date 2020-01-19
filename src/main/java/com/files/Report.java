package com.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Report {
  private final String filename;

  public Report(String filename) {
    this.filename = filename;
  }

  public void write(BrokenLinks brokenLinks) throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(new File(filename))) {
      for (Response brokenLink : brokenLinks.getBrokenLinks()) {
        String str = brokenLink.getUrl() + "\t" + brokenLink.getStatusCode() + "\t" + brokenLink.getStatusMessage() + "\n";

        writer.write(str);
        writer.flush();
      }
      System.out.println("Found " + brokenLinks.getBrokenLinksCount() + " broken links, for details check file '" + filename + "'");
    }
  }
}
