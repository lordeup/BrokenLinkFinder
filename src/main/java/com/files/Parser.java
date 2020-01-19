package com.files;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public Parser(String args[]) {
        ParserState state = ParserState.UNDEFINED;
        boolean isThereOutFile = false;
        for (String element : args) {
            if (changeState(element) != ParserState.UNDEFINED) {
                state = changeState(element);
                continue;
            }
            switch (state) {
                case FILES: {
                    this.pages.add(element);
                    this.parserState = ParserState.FILES;
                    continue;
                }
                case LINKS: {
                    this.pages.add(element);
                    this.parserState = ParserState.LINKS;
                    continue;
                }
                case OUT: {
                    this.outputFile = element;
                    isThereOutFile = true;
                    continue;
                }
                case UNDEFINED: {
                    throw new IllegalArgumentException("Wrong input");
                }
            }
        }
        if (!isThereOutFile) {
            throw new IllegalArgumentException("Please, write out file");
        }
    }

    public List<String> getPages() {
        return this.pages;
    }

    public ParserState getParserState() {
        return this.parserState;
    }

    public String getOutputFile() {
        return outputFile;
    }

    private List<String> pages = new ArrayList<String>();

    private String outputFile;

    private ParserState parserState = ParserState.UNDEFINED;

    private ParserState changeState(String element) {
        if (element.equals("--files")) {
            return ParserState.FILES;
        } else if (element.equals("--links")) {
            return ParserState.LINKS;
        } else if (element.equals("--out")) {
            return ParserState.OUT;
        }
        return ParserState.UNDEFINED;
    }
}