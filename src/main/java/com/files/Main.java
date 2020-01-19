package com.files;

import com.files.parser.Parser;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    try {
      Parser parser = new Parser(Arrays.asList(args));
      parser.parsing();

      BrokenLinks brokenLinks = new BrokenLinks(parser.getPages(), parser.getParserState());
      brokenLinks.run();

      Report report = new Report(parser.getOutputFile());
      report.write(brokenLinks);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
