/**
 * Created by svolkovskyi on 10.04.17.
 */
public class RoundedDouble implements Comparable<RoundedDouble> {
    private final double value;

    public RoundedDouble(double value) {
        int integer = (int) value;
        double fraction = value - integer;
        this.value = ((fraction > 0.5d ? 1d : fraction == 0d ? 0d : 0.5d) + integer);
    }

    public double value() {
        return this.value;
    }

    @Override
    public int compareTo(RoundedDouble o) {
        return value > o.value() ? 1 : value == o.value() ? 0 : -1;
    }
}
