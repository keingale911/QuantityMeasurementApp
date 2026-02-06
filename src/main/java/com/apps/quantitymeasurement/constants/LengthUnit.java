package main.java.com.apps.quantitymeasurement.constants;

public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARD(36.0),
    CENTIMETER(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
