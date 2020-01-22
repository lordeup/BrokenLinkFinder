package com.files.parser;

public enum ModeOperation {
  FILES("--files"),
  LINKS("--links"),
  OUT("--out");

  private final String mode;

  ModeOperation(String mode) {
    this.mode = mode;
  }

  public String getMode() {
    return mode;
  }
}
