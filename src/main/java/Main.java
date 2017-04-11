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

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        List<CSVFile> csvFiles = main.findCSV("/home/KIEV/svolkovskyi/Documents/allpago_interview_test");
        for (CSVFile csv : csvFiles) {
            csv.trace();
        }
    }

    private List<CSVFile> findCSV(String basePath) throws IOException {
        Path base = Paths.get(basePath);
        if (!Files.exists(base) || !Files.isDirectory(base)) {
            throw new IllegalArgumentException("Invalid base path: " + basePath);
        }
        final List<CSVFile> result = new LinkedList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(base, "*.csv")) {
            stream.forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    result.add(new CSVFile(path));
                }
            });
        }
        return result;
    }


}
