import java.util.*;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class Vertex {
    private final String name;
    private final Map<String, Integer> destinations;

    private String predecessor;
    private double shortestPathEstimate = Double.POSITIVE_INFINITY;

    public Vertex(String name) {
        this.name = name;
        this.destinations = Collections.unmodifiableMap(new HashMap<String, Integer>());
    }

    public Vertex(String[] csvLine) {
        if (csvLine == null || csvLine.length < 2) {
            throw new IllegalArgumentException("Line must contain at least one destination");
        }
        this.name = csvLine[0];
        if ("ME".equals(name)) {
            shortestPathEstimate = 0;
        }
        Map<String, Integer> destinations = new HashMap<>();
        for (int i = 1; i < csvLine.length; i++) {
            String[] destination = csvLine[i].split(":");
            if (destination != null && destination.length == 2) {
                destinations.put(destination[0], Integer.valueOf(destination[1]));
            }
        }
        this.destinations = Collections.unmodifiableMap(destinations);
    }

    public String name() {
        return name;
    }

    public int hard(String name){
        return destinations.get(name);
    }

    public double pathEstimate(){
        return shortestPathEstimate;
    }

    public List<String> siblings() {
        return new LinkedList<>(destinations.keySet());
    }

    public void relax(String predecessor, double predecessorsEstimate, double edgeWeight) {
        if (shortestPathEstimate > edgeWeight + predecessorsEstimate) {
            shortestPathEstimate = edgeWeight + predecessorsEstimate;
            this.predecessor = predecessor;
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        builder.append(" predecessor: ").append(predecessor).append(", estimatedPath: ").append(shortestPathEstimate);
        for (Map.Entry<String, Integer> destination : destinations.entrySet()) {
            builder.append(" -> ").append(destination.getKey()).append(" : ").append(destination.getValue()).append("HARD");
        }
        return builder.toString();
    }
}
