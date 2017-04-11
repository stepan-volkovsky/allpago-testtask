import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class CSVFile {
    private final Path filePath;
    private Graph graph;
    private final List<Scenario> scenarios = new LinkedList<>();

    public CSVFile(String path) {
        this.filePath = Paths.get(path);
    }

    public CSVFile(Path path) {
        this.filePath = path;
    }

    private String name() {
        return filePath.getFileName().toString();
    }

    public void trace() throws IOException {
        if (scenarios.isEmpty() || graph == null) {
            readFile();
        }
        System.out.println("\n" + name());
        if (graph != null) {
//            printGraph();
            graph.performBellmanFord();
//            printGraph();
            for (Scenario scenario : scenarios) {
                scenario.calculateCost(graph);
            }
            printScenarios();
        }

    }

    public void print() throws IOException {
        if (scenarios.isEmpty() || graph == null) {
            readFile();
        }
        System.out.println("\n" + name());
        printGraph();
        printScenarios();
    }

    private void printGraph() {
        if (graph != null) {
            graph.print();
        }
    }

    private void printScenarios() {
        System.out.println("\nScenarios: ");
        for (Scenario scenario : scenarios) {
            System.out.print(scenario);
            System.out.println();
        }
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

