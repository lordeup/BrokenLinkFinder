package com.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  String replaceStr(String str) {
    return str.replaceAll("\n", "").replaceAll("\r", "");
  }

  @BeforeEach
  void init() {
    System.setOut(new PrintStream(output));
  }

  @Test
  void mainTest() {
    String[] args = new String[]{"--files", "input.html", "--out", "report.csv"};
    Main.main(args);

    String expectedResult = "Found 7 broken links, for details check file 'report.csv'";
    String actualResult = replaceStr(output.toString());

    assertEquals(expectedResult, actualResult, "Print info new customer");
  }
}