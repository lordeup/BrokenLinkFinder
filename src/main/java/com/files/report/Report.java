package com.files.report;

import com.files.response.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Report {
  private final String filename;

  public Report(String filename) {
    this.filename = filename;
  }

  public void writeInFile(List<Response> responses) throws IOException {
    try (FileWriter fileWriter = new FileWriter(filename)) {
      for (Response response : responses) {
        String str = response.getUrl() + ", " + response.getStatusCode() + ", " + response.getStatusMessage() + "\n";

        fileWriter.write(str);
        fileWriter.flush();
      }
    }
  }
}
