package com.allpago.testtask.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class CSVFile {
    private final Path filePath;
    private Graph graph;
    private final List<Scenario> scenarios = new LinkedList<>();

    public CSVFile(Path path) {
        this.filePath = path;
    }

    public String name(){
        return filePath.getFileName().toString();
    }

    public Graph graph() throws IOException {
        if (graph == null) {
            readFile();
        }
        return graph;
    }

    public List<Scenario> scenarios() throws IOException {
        if (scenarios.isEmpty()) {
            readFile();
        }
        return scenarios;
    }

    private void readFile() throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String[]> graphLines = new LinkedList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            if ("@".equals(parts[0])) {
                this.scenarios.add(new Scenario(parts));
            } else {
                graphLines.add(parts);
            }
        }
        graph = new Graph(graphLines);
    }
}

