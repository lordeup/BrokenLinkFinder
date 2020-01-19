package com.files;

public class Main {
    public static void main(String[] args)
    {
        try {
            Parser parser = new Parser(args);
            BrokenLinks brokenLinks = new BrokenLinks(parser.getPages(), parser.getParserState());
            Report report = new Report(parser.getOutputFile());
            report.write(brokenLinks);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
