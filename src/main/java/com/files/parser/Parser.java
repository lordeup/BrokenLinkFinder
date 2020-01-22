package com.files.parser;

import com.files.errormessage.ErrorMessage;

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
    if (args.isEmpty()) {
      throw new IllegalArgumentException(ErrorMessage.ERROR_ARGS);
    }

    ParserState state = ParserState.UNDEFINED;

    for (String str : args) {
      if (!outputFile.isEmpty()) {
        throw new IllegalArgumentException(ErrorMessage.ERROR_MANY_OUT_FILE);
      }

      ParserState parserState = getState(str);
      if (parserState != ParserState.UNDEFINED) {
        if (state != ParserState.UNDEFINED && parserState != ParserState.OUT) {
          throw new IllegalArgumentException(ErrorMessage.ERROR_MODE);
        }
        state = parserState;
        continue;
      }
      runStateCase(str, state);
    }

    if (outputFile.isEmpty()) {
      throw new IllegalArgumentException(ErrorMessage.ERROR_NO_OUT_FILE);
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
        if (pages.isEmpty()) {
          throw new IllegalArgumentException(ErrorMessage.ERROR_NO_FILE_OR_LINK);
        }
        outputFile = str;
        return;
      }
      case UNDEFINED: {
        throw new IllegalArgumentException(ErrorMessage.ERROR_ARGS);
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