package com.files.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
  private List<String> args;

  @BeforeEach
  void init() {
    args = Arrays.asList("--files", "input.html", "--out", "report.csv");
  }

  @Test
  void constructorTest() {
    Parser parser = new Parser(args);

    assertAll("constructor",
            () -> assertEquals(4, parser.getArgs().size(), "Get args"),
            () -> assertTrue(parser.getPages().isEmpty(), "Get pages"),
            () -> assertTrue(parser.getOutputFile().isEmpty(), "Get output file"),
            () -> assertEquals(ParserState.UNDEFINED, parser.getParserState(), "Get parser state")
    );
  }

  @Test
  void getParserStateFiles() {
    Parser parser = new Parser(args);
    parser.parsing();

    assertEquals(ParserState.FILES, parser.getParserState(), "Get parser state files");
  }

  @Test
  void getParserStateLinks() {
    List<String> args = Arrays.asList("--links", "http://httpstat.us/", "--out", "report.csv");
    Parser parser = new Parser(args);
    parser.parsing();

    assertEquals(ParserState.LINKS, parser.getParserState(), "Get parser state links");
  }

  @Test
  void parsingEmptyArgs() {
    List<String> args = Collections.emptyList();
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing with empty args");
  }

  @Test
  void parsingMissingOptionOut() {
    List<String> args = Arrays.asList("--files", "input.html", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing missing --out");
  }

  @Test
  void parsingMissingOutputFile() {
    List<String> args = Arrays.asList("--files", "input.html", "--out");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing missing output file");
  }

  @Test
  void parsingManyOutputFile() {
    List<String> args = Arrays.asList("--files", "input.html", "--out", "report.csv", "test.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing many output file");
  }

/*  @Test
  void parsingManyOutputFile() {
    List<String> args = Arrays.asList("--links", "http://httpstat.us/", "--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing many output file");
  }*/
}