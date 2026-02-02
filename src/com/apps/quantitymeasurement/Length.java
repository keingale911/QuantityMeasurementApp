package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }

        public double getConversionFactor()
        {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit){
        this.value=value;
        this.unit=unit;
    }

    private double convertToBaseUnit(){
        return value*unit.getConversionFactor();
    }

    public boolean compare(Length that){
        return Double.compare(this.convertToBaseUnit(), that.convertToBaseUnit())==0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        Length other = (Length) obj;
        return compare(other);
    }

    public static void main(String[] args) {
        Length ft=new Length(1.0,LengthUnit.FEET);
        Length in=new Length(12.0,LengthUnit.INCHES);

        System.out.println("Are both equals = "+ft.equals(in));
    }
}
