package com.files.errormessage;

import com.files.parser.ModeOperation;

public class ErrorMessage {
  public static final String ERROR_ARGS = "Invalid arguments. Must be:\n" +
          "BrokenLinkFinder <" + ModeOperation.FILES.getMode() + "> <page1.html> <page2.html> ... <pageN.html> " +
          "<" + ModeOperation.OUT.getMode() + "> <report.csv>\n" +
          "OR\n" +
          "BrokenLinkFinder <" + ModeOperation.LINKS.getMode() + "> <link1.html> <link2.html> ... <linkN.html> " +
          "<" + ModeOperation.OUT.getMode() + "> <report.csv>";

  public static final String ERROR_MODE = "Use only one program mode (" + ModeOperation.FILES.getMode() +
          " OR " + ModeOperation.LINKS.getMode() + ")";

  public static final String ERROR_MANY_OUT_FILE = "Only one output file can be specified";

  public static final String ERROR_NO_OUT_FILE = "Missing option " + "<" + ModeOperation.OUT.getMode() + ">" + " OR output file";

  public static final String ERROR_NO_FILE_OR_LINK = "Please enter at least one link or file";

  public static final String ERROR_OBJECT_DIFFERENT_TYPES = "Object different types";
}
