package main.java.com.apps.quantitymeasurement.service;

import main.java.com.apps.quantitymeasurement.constants.LengthUnit;

import java.util.Objects;

public class Length {

    public final double value;
    public final LengthUnit lengthUnit;

    //Class para constructor
    //@Param --> double
    //@Param --> Enum Unit
    public Length(double value, LengthUnit lengthUnit) {
        this.value = value;
        this.lengthUnit = lengthUnit;
    }

    //refactored and moved to enum LengthUnit
    //This method will convert value into base unit value and round off 2 decimal
    //@Return <-- double
//    private double convertToBaseUnit() {
//        double inche = value * unit.getConversionFactor();
//        return Math.round(inche * 100.0) / 100.0;
//    }

    //Here we are checking 2 length objects
    //@Param --> LengthObj1
    //@Return <-- boolean
    public boolean compare(Length that) {
        return Double.compare(this.lengthUnit.convertToBaseUnit(this.value),
                that.lengthUnit.convertToBaseUnit(that.value)) == 0;
    }

    //Addition of 2 length values and return converted in 1st object unit
    //@Param --> LengthObj1
    //@Return <-- LengthObj
    public Length add(Length thatLength){
        if(thatLength == null){
            throw new IllegalArgumentException("null is not allowed, please enter valid number");
        }
        double inchesThis =  this.lengthUnit.convertToBaseUnit(this.value);
        double inchesThat = thatLength.lengthUnit.convertToBaseUnit(thatLength.value);
        double sumInches = inchesThis + inchesThat;

        double sumInThisUnit = sumInches / this.lengthUnit.getConversionFactor();
        sumInThisUnit = Math.round(sumInThisUnit * 100.0) / 100.0;
        return new Length(sumInThisUnit, this.lengthUnit);
    }

    //This method will do both operations(Addition and conversion into target unit) and return length
    //@Param --> LengthObj1
    //@Param --> Enum Unit
    //@Return <-- LengthObj
    public Length addAndConvert(Length secondLength, LengthUnit lengthUnit) {
        return this.add(secondLength, lengthUnit);
    }

    // made changes as per lengthUnit method call and return type
    //Addition and conversion into target unit and return Length obj
    //@Param --> LengthObj1
    //@Param --> Enum Unit
    //@Return <-- LengthObj
    private Length add(Length secondLength, LengthUnit targethUnit) {
        Length commonLength = this.add(secondLength);

        return new Length(commonLength.lengthUnit.convertFromBaseUnit(commonLength.value,targethUnit), targethUnit);
    }

    //refactored and moved to enum LengthUnit
    //Convert value into 1st obj unit
    //@Param -- LengthObj1
    //@Param -- LengthObj2
    //@Return -- LengthObj
//    private Length convertFromBaseToTargetUnit(Length length, Length that) {
//        return that.converTo(this.lengthUnit);
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Length other = (Length) obj;
        return compare(other);
    }

    @Override
    public int hashCode(){
        return Objects.hash(lengthUnit.convertToBaseUnit(this.value));
    }

    //refactored and moved to enum LengthUnit
    //Convert value from one unit into another (Inches -> Feet)
//    public Length converTo(LengthUnit targetUnit){
//        if(targetUnit==null){
//            throw new IllegalArgumentException("Null not allowed, Please enter valid target unit");
//        }
//
//        double inches = lengthUnit.convertToBaseUnit(this.value);
//        double convertToTarget = inches/targetUnit.getConversionFactor();
//        convertToTarget = Math.round(convertToTarget * 100.00)/100.0;
//
//        return new Length(convertToTarget, targetUnit);
//    }

    @Override
    public String toString() {
        return "Length { " +
                "value = " + value +
                "  " + lengthUnit +
                '}';
    }

    public static void main(String[] args) {
        Length ft = new Length(2.0, LengthUnit.FEET);
        Length in = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are both(Feet & Inches) equals = " + ft.equals(in));

        Length yd = new Length(1.0, LengthUnit.YARD);
        Length inyd = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are both(Yard & Inches) equals = " + yd.equals(inyd));

        Length cm = new Length(100.0, LengthUnit.CENTIMETER);
        Length incm = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are both(Centimeter & Inches) equals = " + cm.equals(incm));

        System.out.println(ft.lengthUnit.convertFromBaseUnit(ft.value,LengthUnit.INCHES));

        System.out.println("This is converted into centimeter = "+LengthUnit.YARD.convertToBaseUnit(1.0));

        System.out.println("This is converted into centimeter = "+LengthUnit.YARD.convertFromBaseUnit(1.0, LengthUnit.FEET));

        System.out.println("addition of 2 length value = "+ft.add(in).toString());

        System.out.println("addition of 2 length value and converted = "+ft.addAndConvert(in,LengthUnit.YARD).toString());
    }
}
