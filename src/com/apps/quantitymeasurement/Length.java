package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

    private double value;
    private LengthUnit unit;

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

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        double inche = value * unit.getConversionFactor();
        return Math.round(inche * 100.0) / 100.0;
    }

    public boolean compare(Length that) {
        return Double.compare(this.convertToBaseUnit(), that.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Length other = (Length) obj;
        return compare(other);
    }

    @Override
    public int hashCode(){
        return Objects.hash(convertToBaseUnit());
    }

    public Length converTo(LengthUnit targetUnit){
        if(targetUnit==null){
            throw new IllegalArgumentException("Null not allowed, Please enter valid target unit");
        }

        double inches = convertToBaseUnit();
        double convertToTarget = inches/targetUnit.getConversionFactor();
        convertToTarget = Math.round(convertToTarget * 100.00)/100.0;

        return new Length(convertToTarget, targetUnit);
    }

    @Override
    public String toString() {
        return "Length { " +
                "value = " + value +
                "  " + unit +
                '}';
    }

    public static void main(String[] args) {
        Length ft = new Length(1.0, LengthUnit.FEET);
        Length in = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are both(Feet & Inches) equals = " + ft.equals(in));

        Length yd = new Length(1.0, LengthUnit.YARD);
        Length inyd = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are both(Yard & Inches) equals = " + yd.equals(inyd));

        Length cm = new Length(100.0, LengthUnit.CENTIMETER);
        Length incm = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are both(Centimeter & Inches) equals = " + cm.equals(incm));

        System.out.println(ft.converTo(LengthUnit.YARD).toString());
    }
}
