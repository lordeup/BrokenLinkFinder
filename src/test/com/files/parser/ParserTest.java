package com.files.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

  @Test
  void constructorTest() {
    List<String> args = Arrays.asList("--files", "input.html", "--out", "report.csv");
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
    List<String> args = Arrays.asList("--files", "input.html", "--out", "report.csv");
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
  void parsingNonexistentMode() {
    List<String> args = Arrays.asList("--test", "input.html", "--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing nonexistent mode");
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
  void parsingMissingOptionOutAndOutputFile() {
    List<String> args = Arrays.asList("--files", "input.html");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing missing --out and output file");
  }

  @Test
  void parsingMissingFilesOrLinks() {
    List<String> args = Arrays.asList("--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing missing files or links");
  }

  @Test
  void parsingManyOutputFile() {
    List<String> args = Arrays.asList("--files", "input.html", "--out", "report.csv", "test.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing many output file");
  }

  @Test
  void parsingModeStateLinksAndFiles() {
    List<String> args = Arrays.asList("--links", "http://httpstat.us/", "--files", "input.html", "--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing mode state links and files");
  }

  @Test
  void parsingModeStateFilesAndLinks() {
    List<String> args = Arrays.asList("--files", "input.html", "--links", "http://httpstat.us/", "--out", "report.csv", "test.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing mode state files and links");
  }

  @Test
  void parsingNoFiles() {
    List<String> args = Arrays.asList("--files", "--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing no files");
  }

  @Test
  void parsingNoLinks() {
    List<String> args = Arrays.asList("--links", "--out", "report.csv");
    Parser parser = new Parser(args);

    assertThrows(IllegalArgumentException.class, parser::parsing, "Parsing no links");
  }
}