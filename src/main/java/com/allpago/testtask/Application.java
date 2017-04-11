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

    public void run() throws IOException {
        Graph graph = csvFile.graph();
        graph.traceEstimates();
        List<Scenario> scenarios = csvFile.scenarios();
        for (Scenario scenario : scenarios) {
            scenario.calculateCost(graph);
            System.out.println(scenario);
        }
    }
}