package com.files.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
  private final List<String> args;
  private final List<String> pages;
  private String outputFile;
  private ParserState parserState;

  private static final String ERROR_MESSAGE = "Invalid arguments. Must be:\n" +
          "BrokenLinkFinder <" + ModeOperation.FILES.getMode() + "> <page1.html> <page2.html> ... <pageN.html> " +
          "<" + ModeOperation.OUT.getMode() + "> <report.csv>\n" +
          "OR\n" +
          "BrokenLinkFinder <" + ModeOperation.LINKS.getMode() + "> <link1.html> <link2.html> ... <linkN.html> " +
          "<" + ModeOperation.OUT.getMode() + "> <report.csv>";

  private static final String ERROR_MODE = "Invalid arguments. The program mode is already set to ";

  public Parser(List<String> args) {
    this.args = args;
    this.pages = new ArrayList<>();
    this.outputFile = "";
    this.parserState = ParserState.UNDEFINED;
  }

  public void parsing() {
    if (args.isEmpty()) {
      throw new IllegalArgumentException(ERROR_MESSAGE);
    }
    ParserState state = ParserState.UNDEFINED;
    for (String str : args) {
      if (!outputFile.isEmpty()) {
        throw new IllegalArgumentException();
      }
      ParserState parserState = getState(str);
      if (parserState != ParserState.UNDEFINED) {
        state = parserState;
        continue;
      }
      runStateCase(str, state);
    }
    if (outputFile.isEmpty()) {
      throw new IllegalArgumentException("Please, write out file");
    }
  }

  public List<String> getPages() {
    return pages;
  }

  public String getOutputFile() {
    return outputFile;
  }

  public ParserState getParserState() {
    return parserState;
  }

  public void setParserState(ParserState parserState) {
    this.parserState = parserState;
  }

  private void addPage(String page) {
    pages.add(page);
  }

  private void runStateCase(String str, ParserState state) {
    switch (state) {
      case FILES: {
        if (parserState == ParserState.LINKS) {
          throw new IllegalArgumentException(ERROR_MODE + ModeOperation.LINKS.getMode());
        }
        addPage(str);
        setParserState(ParserState.FILES);
        return;
      }
      case LINKS: {
        if (parserState == ParserState.FILES) {
          throw new IllegalArgumentException(ERROR_MODE + ModeOperation.FILES.getMode());
        }
        addPage(str);
        setParserState(ParserState.LINKS);
        return;
      }
      case OUT: {
        if (pages.isEmpty()) {
          throw new IllegalArgumentException("Please enter at least one link or file");
        }
        outputFile = str;
        return;
      }
      case UNDEFINED: {
        throw new IllegalArgumentException(ERROR_MESSAGE);
      }
    }
  }

  private ParserState getState(String str) {
    if (str.equals(ModeOperation.FILES.getMode())) {
      return ParserState.FILES;
    } else if (str.equals(ModeOperation.LINKS.getMode())) {
      return ParserState.LINKS;
    } else if (str.equals(ModeOperation.OUT.getMode())) {
      return ParserState.OUT;
    } else {
      return ParserState.UNDEFINED;
    }
  }
}