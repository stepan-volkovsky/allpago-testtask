/**
 * Created by svolkovskyi on 10.04.17.
 */
public class Package {
    private final double width;
    private final double length;
    private final double height;
    private final double weight;

    /**
     * @param dimensions {width(cm)}x{length(cm)}x{height(cm)}x{weight(g)}
     */
    public Package(String dimensions) {
        if (dimensions == null || dimensions.isEmpty()) {
            throw new IllegalArgumentException("Dimensions definition can not be empty");
        }
        String[] parts = dimensions.split("x");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Dimensions are incomplete: " + dimensions);
        }
        this.width = Double.valueOf(parts[0]);
        this.length = Double.valueOf(parts[1]);
        this.height = Double.valueOf(parts[2]);
        this.weight = Double.valueOf(parts[3]);
    }

    /**
     * @return package weight in kg rounded up to the nearest 0,5kg
     */
    public double normalizedWeight() {
        double volumetricWeight = volumetricWeight();
        double realWeight = weight / 1000d;
        return volumetricWeight > realWeight ? volumetricWeight : realWeight;
    }

    private double volumetricWeight(){
        double notRounded = (width * length * height) / 5000d;
        int integer = (int) notRounded;
        double fraction = notRounded - integer;
        return  ((fraction > 0.5d ? 1d : fraction == 0d ? 0d : 0.5d) + integer);
    }


    @Override
    public String toString() {
        return "Package: w=" + width + " l=" + length + " h=" + height + "; " + weight + "g";
    }
}
