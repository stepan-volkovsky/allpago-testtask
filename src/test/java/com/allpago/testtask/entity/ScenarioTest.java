package com.allpago.testtask.entity;

import com.allpago.testtask.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by svolkovskyi on 13.04.17.
 */
@RunWith(Parameterized.class)
public class ScenarioTest {
    private final String baseDir;
    private Map<Graph, List<Scenario>> bundles = new HashMap<>();

    public ScenarioTest(String baseDir) {
        this.baseDir = baseDir;
    }

    @Parameterized.Parameters
    public static String[] parameters() {
        return new String[]{"/tmp/allpago"};
    }

    @Test
    public void calculatedAndGivenCostShouldBeEqual() {
        for (Map.Entry<Graph, List<Scenario>> bundle : bundles.entrySet()) {
            for (Scenario scenario : bundle.getValue()) {
                scenario.calculateCost(bundle.getKey());
                Assert.assertEquals(scenario.cost(), scenario.calculatedCost(), 0d);
            }
        }
    }

    @Before
    public void prepareGraph() throws IOException {
        Main main = new Main();
        List<CSVFile> files = main.findCSV(Paths.get(baseDir));
        for (CSVFile csvFile : files) {
            Graph graph = csvFile.graph();
            List<Scenario> scenarios = csvFile.scenarios();
            graph.traceEstimates();
            bundles.put(graph, scenarios);
        }
    }

}
