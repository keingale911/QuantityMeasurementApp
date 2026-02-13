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

    public double convertToBaseUnit(double value) {
        double inche = value * getConversionFactor();
        return Math.round(inche * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double value, LengthUnit l1) {

        double inches = value * this.getConversionFactor();
        double convertToTarget = inches / l1.getConversionFactor();
        return Math.round(convertToTarget * 100.0) / 100.0;
    }
}
