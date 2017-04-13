package com.allpago.testtask.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        Double doubleCost = Math.sqrt(graph.hardEstimate(target)) * aPackage.normalizedWeight();
        if (doubleCost.isInfinite() || doubleCost.isNaN()) {
            this.calculatedCost = Double.POSITIVE_INFINITY;
        } else {
            BigDecimal calculatedCost = new BigDecimal(doubleCost);
            this.calculatedCost = calculatedCost.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
    }

    public double cost(){
        return this.cost;
    }

    public double calculatedCost(){
        return this.calculatedCost;
    }

    @Override
    public String toString() {
        return "Ship to " + target + " " + aPackage + " for " + cost + "; calculated cost: " + calculatedCost;
    }
}
