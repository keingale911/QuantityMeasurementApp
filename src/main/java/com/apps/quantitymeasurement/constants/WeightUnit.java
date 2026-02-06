package main.java.com.apps.quantitymeasurement.constants;

public enum WeightUnit {

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double weight) {
        double gram = weight * getConversionFactor();
        return Math.round(gram * 100.0) / 100.0;
    }

    public static void main(String[] args) {
    }
}
