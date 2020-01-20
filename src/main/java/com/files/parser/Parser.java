package com.files.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
  private final List<String> args;
  private final List<String> pages;
  private String outputFile;
  private ParserState parserState;

  public Parser(List<String> args) {
    this.args = args;
    this.pages = new ArrayList<>();
    this.outputFile = "";
    this.parserState = ParserState.UNDEFINED;
  }

  public void parsing() {
    ParserState state = ParserState.UNDEFINED;
    for (String str : args) {
      ParserState parserState = setState(str);
      if (parserState != ParserState.UNDEFINED) {
        state = parserState;
        continue;
      }
      checkState(str, state);
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

  private void checkState(String str, ParserState state) {
    switch (state) {
      case FILES: {
        addPage(str);
        setParserState(ParserState.FILES);
        return;
      }
      case LINKS: {
        addPage(str);
        setParserState(ParserState.LINKS);
        return;
      }
      case OUT: {
        outputFile = str;
        return;
      }
      case UNDEFINED: {
        throw new IllegalArgumentException("Input is incorrectly specified. Try again");
      }
    }
  }

  private ParserState setState(String str) {
    switch (str) {
      case "--files":
        return ParserState.FILES;
      case "--links":
        return ParserState.LINKS;
      case "--out":
        return ParserState.OUT;
      default:
        return ParserState.UNDEFINED;
    }
  }
}