package com.allpago.testtask;

import com.allpago.testtask.entity.CSVFile;
import com.allpago.testtask.entity.Graph;
import com.allpago.testtask.entity.Scenario;

import java.io.IOException;
import java.util.List;

/**
 * Created by svolkovskyi on 11.04.17.
 */
public class Application {
    private final CSVFile csvFile;

    public Application(CSVFile csvFile) {
        this.csvFile = csvFile;
    }

    public void run() {
        try {
            Graph graph = csvFile.graph();
            List<Scenario> scenarios = csvFile.scenarios();

            graph.traceEstimates();
            System.out.println("\n" + csvFile.name() + ":");
            for (Scenario scenario : scenarios) {
                scenario.calculateCost(graph);
                System.out.println(scenario);
            }
        } catch (IOException e) {
            System.err.println("Failed to read a file");
            e.printStackTrace();
        }
    }
}