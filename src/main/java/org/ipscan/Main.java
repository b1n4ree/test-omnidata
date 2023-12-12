package org.tests;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new RuntimeException("Not found args requirement");
        }

        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        LogUtils logUtils = new LogUtils(Paths.get(currentDir + "/" + args[0]));

        try {

            logUtils.writeIpsToNewFile(logUtils.getIpsFromFile());

        } catch (IOException ioException) {
            throw new RuntimeException("Error creating output file...");
        }

        System.out.println("Current directory save file: " + currentDir);
    }
}
