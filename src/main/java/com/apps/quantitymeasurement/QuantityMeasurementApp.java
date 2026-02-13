package main.java.com.apps.quantitymeasurement;

import main.java.com.apps.quantitymeasurement.constants.LengthUnit;
import main.java.com.apps.quantitymeasurement.service.Length;

public class QuantityMeasurementApp {

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (this == null) return false;
            if (!(obj instanceof Feet)) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static class Inches {

        private final double inch;

        public Inches(double inch) {
            this.inch = inch;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (this == null) return false;
            if (!(obj instanceof Inches)) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.inch, other.inch) == 0;
        }
    }

    public static void demonstrateFeetEqulity() {
        Feet f1 = new Feet(2.5);
        Feet f2 = new Feet(1.5);
        Feet f3 = new Feet(2.5);
        Inches inch = new Inches(24.0);

        System.out.println(f1.equals(f3));
        System.out.println(f2.equals(f1));
        System.out.println(f3.equals(f1));
        System.out.println(f3.equals(null));
        System.out.println(f3.equals(f3));
        System.out.println(f3.equals(inch));
    }

    public static void demonstrateInchesEqulity() {
        Inches f1 = new Inches(24.0);
        Inches f2 = new Inches(11.5);
        Inches f3 = new Inches(12.0);
        Feet ft = new Feet(2.0);

        System.out.println(f1.equals(f3));
        System.out.println(f2.equals(f1));
        System.out.println(f3.equals(f1));
        System.out.println(f3.equals(null));
        System.out.println(f3.equals(f3));
        System.out.println(f3.equals(ft));
    }

    public static void main(String[] args) {
        demonstrateInchesEqulity();
        demonstrateFeetEqulity();
        demonstrateFeetInchesComparison();

        demonstrateLengthComparison(1.0,LengthUnit.FEET,12.0,LengthUnit.INCHES);
        demonstrateLengthComparison(1.0,LengthUnit.YARD,36.0,LengthUnit.INCHES);
        demonstrateLengthComparison(100.0,LengthUnit.CENTIMETER,39.3701,LengthUnit.INCHES);
        demonstrateLengthComparison(3.0,LengthUnit.FEET,1.0,LengthUnit.YARD);
        demonstrateLengthComparison(30.48,LengthUnit.CENTIMETER,1.0,LengthUnit.FEET);

        demonstrateLengthConversion(3.281,LengthUnit.FEET,LengthUnit.CENTIMETER);

        demonstrateLengthAddition(new Length(2.12, LengthUnit.FEET),new Length(12.0, LengthUnit.INCHES));

        demonstrateLengthAddition(new Length(2.0, LengthUnit.FEET),new Length(12.0, LengthUnit.INCHES),LengthUnit.YARD);
    }

    public static Length demonstrateLengthAddition(Length length, Length secondLength, LengthUnit targetUnit) {
        Length convertedAdd = length.addAndConvert(secondLength,targetUnit);
        System.out.println("Addition of 2 length values in target Unit = "+convertedAdd.toString());
        return convertedAdd;
    }

    public static Length demonstrateLengthAddition(Length length, Length that) {

        Length sumLength= length.add(that);
        System.out.println("Addition of 2 length values = "+sumLength.add(that).toString());

        return sumLength;
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit lengthUnitSrc, LengthUnit lengthUnitTrg) {
        Length ft = new Length(value,lengthUnitSrc);
        Length lt=demonstrateLengthConversion(ft, lengthUnitTrg);
        System.out.println(lt.toString());

        return lt;
    }

    public static Length demonstrateLengthConversion(Length ft, LengthUnit lengthUnitTrg) {
        return new Length(ft.lengthUnit.convertFromBaseUnit(ft.value,lengthUnitTrg), lengthUnitTrg);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit lengthUnit1, double value2, LengthUnit lengthUnit2) {
        boolean rs= demonstrateLengthEqulity(new Length(value1,lengthUnit1),new Length(value2,lengthUnit2));
        System.out.println(rs);
        return rs;
    }

    private static void demonstrateFeetInchesComparison() {
        Length ft = new Length(2.0,LengthUnit.FEET);
        Length in = new Length(24.0,LengthUnit.INCHES);

        System.out.println("Feet equals inches = "+ demonstrateLengthEqulity(ft,in));
    }

    public static boolean demonstrateLengthEqulity(Length ft, Length in) {
        return ft.equals(in);
    }
}
