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
    private final Path path;
    private final List<Node> nodes = new LinkedList<>();
    private final List<Scenario> scenarios = new LinkedList<>();

    public CSVFile(String path) {
        this.path = Paths.get(path);
    }

    public CSVFile(Path path) {this.path = path;}

    public List<Node> nodes() throws IOException {
        if (this.nodes.isEmpty()) {
            readCSV();
        }
        return new LinkedList<>(nodes);
    }

    public List<Scenario> scenarios() throws IOException {
        if (this.scenarios.isEmpty()) {
            readCSV();
        }
        return new LinkedList<>(scenarios);
    }

    private void readCSV() throws IOException {
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            String[] parts = line.split(",");
            if ("@".equals(parts[0])) {
                this.scenarios.add(new Scenario(parts));
            } else {
                this.nodes.add(new Node(parts));
            }

        }
    }
}

