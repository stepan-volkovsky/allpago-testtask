import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class Node {
    private final String name;
    private final Map<String, Integer> destinations;

    public Node(String[] csvLine) {
        if (csvLine == null || csvLine.length < 2) {
            throw new IllegalArgumentException("Line must contain at least one destination");
        }
        this.name = csvLine[0];
        Map<String, Integer> destinations = new HashMap<>();
        for (int i = 1; i < csvLine.length; i++) {
            String[] destination = csvLine[i].split(":");
            if (destination != null && destination.length == 2) {
                destinations.put(destination[0], Integer.valueOf(destination[1]));
            }
        }
        this.destinations = Collections.unmodifiableMap(destinations);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        for (Map.Entry<String, Integer> destination : destinations.entrySet()) {
            builder.append("\n => ").append(destination.getKey()).append(" : ").append(destination.getValue()).append("HARD");
        }
        return builder.toString();
    }
}
