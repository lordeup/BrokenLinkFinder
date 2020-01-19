package com.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Report {
    public Report(String filename) throws FileNotFoundException {
        this.writer = new PrintWriter(new File(filename));
        this.filename = filename;
    }

    public void write(BrokenLinks brokenLinks) {
        if (writer == null) {
            return;
        }
        for (Response brokenLink : brokenLinks.getBrokenLinks()) {
            String string = brokenLink.getUrl() + ";" +
                            brokenLink.getStatusCode() + ";" +
                            brokenLink.getStatusMessage() + "\n";
            writer.write(string);
            writer.flush();
        }
        System.out.println("Found " + brokenLinks.getBrokenLinksCount() +
                           " broken links, for details check file '" + filename + "'");
    }

    private PrintWriter writer = null;
    private String filename;
}
