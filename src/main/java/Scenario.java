import java.text.DecimalFormat;

/**
 * Created by svolkovskyi on 10.04.17.
 */
public class Scenario {
    private final String target;
    private final Package aPackage;
    private final double cost;
    private double calculatedCost;

    public Scenario(String[] csvLine) {
        if (csvLine == null || csvLine.length != 4) {
            throw new IllegalArgumentException("Line must contain full information about scenario");
        }
        this.target = csvLine[1];

        this.aPackage = new Package(csvLine[2]);
        if ("~".equals(csvLine[3])) {
            this.cost = Double.POSITIVE_INFINITY;
        } else {
            this.cost = Double.valueOf(csvLine[3]);
        }
    }

    public void calculateCost(Graph graph) {
        calculatedCost = Math.sqrt(graph.hardEstimate(target)) * aPackage.normalizedWeight();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Ship to " + target + " " + aPackage + " for " + cost + "; calculated cost: " + df.format(calculatedCost);
    }
}
