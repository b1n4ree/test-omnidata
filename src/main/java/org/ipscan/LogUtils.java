package org.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class LogUtils {

    private final Path path;

    public LogUtils(Path path) {
        this.path = path;
    }

    public HashSet<String> getIpsFromFile() {

        HashSet<String> ips = new HashSet<>();

        {
            try (BufferedReader bf = Files.newBufferedReader(path)) {

                bf.lines().forEach(line -> {

                    String ip = line.substring(0, line.indexOf(" "));
                    ips.add(ip);
                });

            } catch (IOException ioException) {
                throw new RuntimeException("Not found log file in " + path.getParent());
            }
        }

        return ips;
    }

    public void writeIpsToNewFile(HashSet<String> ips) throws IOException {

        Path newFile = Paths.get(path.getParent() + "/result-" +
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:MM:ss z")));

        Files.write(newFile, ips);
    }

}
