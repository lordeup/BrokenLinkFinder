package com.files;

import com.files.brokenlinks.BrokenLinks;
import com.files.links.Links;
import com.files.parser.Parser;
import com.files.parser.ParserState;
import com.files.report.Report;
import com.files.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
  public static void main(String[] args) {
    try {
      Parser parser = new Parser(Arrays.asList(args));
      parser.parsing();

      List<Response> brokenLinksResponse = getBrokenLinks(parser.getPages(), parser.getParserState());

      String outputFileName = parser.getOutputFile();

      Report report = new Report(outputFileName);
      report.writeInFile(brokenLinksResponse);

      printFinalMessage(brokenLinksResponse.size(), outputFileName);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private static void printFinalMessage(Integer linksSize, String outputFileName) {
    System.out.println(linksSize > 0
            ? "Found " + linksSize + " broken links, for details check file '" + outputFileName + "'"
            : "Not found broken links");
  }

  private static List<Response> getBrokenLinks(List<String> pages, ParserState parserState)
          throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    List<Response> brokenLinksList = new ArrayList<>();
    for (String page : pages) {
      Links links = new Links(page, parserState);
      links.findLinks();
      BrokenLinks brokenLinks = new BrokenLinks(links.getLinks());
      brokenLinks.findBrokenLinks();

      for (Response brokenLink : brokenLinks.getBrokenLinks()) {
        if (!brokenLinksList.contains(brokenLink)) {
          brokenLinksList.add(brokenLink);
        }
      }
    }

    return brokenLinksList;
  }
}
