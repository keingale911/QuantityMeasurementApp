package main.java.com.apps.quantitymeasurement.constants;

import main.java.com.apps.quantitymeasurement.service.IMeasurable;

public enum WeightUnit implements IMeasurable {

    MILLIGRAM(0.000001),
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592),
    TONNE(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double weight) {
        return Math.round((weight * getConversionFactor()) * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double value) {
        return Math.round((value / getConversionFactor()) * 100.0) / 100.0;
    }

    /**
     * @return
     */
    @Override
    public String getUnitName() {
        return "";
    }
}
