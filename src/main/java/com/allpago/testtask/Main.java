package com.allpago.testtask;

import com.allpago.testtask.entity.CSVFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class Main {

    public static void main(String[] args) {
        Path csvDir;
        if (args.length == 0) {
            csvDir = Paths.get(".");
        } else {
            csvDir = Paths.get(args[0]);
        }

        Main main = new Main();
        List<CSVFile> csvFiles = main.findCSV(csvDir);
        for (CSVFile csv : csvFiles) {
            new Application(csv).run();
        }
    }

    public List<CSVFile> findCSV(Path basePath) {

        if (!Files.exists(basePath) || !Files.isDirectory(basePath)) {
            throw new IllegalArgumentException("Invalid base path: " + basePath);
        }
        final List<CSVFile> result = new LinkedList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(basePath, "*.csv")) {
            stream.forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    System.out.println("Found " + path);
                    result.add(new CSVFile(path));
                }
            });
        } catch (IOException e) {
            System.err.println("Failded to read path: " + basePath);
        }
        return result;
    }


}
