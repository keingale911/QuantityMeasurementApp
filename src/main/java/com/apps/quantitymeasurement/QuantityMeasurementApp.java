package main.java.com.apps.quantitymeasurement;

import main.java.com.apps.quantitymeasurement.constants.LengthUnit;
import main.java.com.apps.quantitymeasurement.constants.WeightUnit;
import main.java.com.apps.quantitymeasurement.service.Quentity;

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
            if (!(obj instanceof Feet other)) return false;

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

    public static void main(String[] args) {

        //Quentity<LengthUnit> operations
        demonstrateInchesEqulity();
        demonstrateFeetEqulity();
        demonstrateFeetInchesComparison();

        demonstrateLengthComparison(1.0,LengthUnit.FEET,12.0,LengthUnit.INCHES);
        demonstrateLengthComparison(1.0,LengthUnit.YARD,36.0,LengthUnit.INCHES);
        demonstrateLengthComparison(100.0,LengthUnit.CENTIMETER,39.3701,LengthUnit.INCHES);
        demonstrateLengthComparison(3.0,LengthUnit.FEET,1.0,LengthUnit.YARD);
        demonstrateLengthComparison(30.48,LengthUnit.CENTIMETER,1.0,LengthUnit.FEET);

        demonstrateLengthConversion(3.281,LengthUnit.FEET,LengthUnit.CENTIMETER);

        demonstrateLengthAddition(new Quentity<>(1.0, LengthUnit.FEET),new Quentity<>(12.0, LengthUnit.INCHES));

        demonstrateLengthAddition(new Quentity<>(2.0, LengthUnit.FEET),new Quentity<>(12.0, LengthUnit.INCHES),LengthUnit.YARD);

        //Quentity<WeightUnit> Operations UC9
        Quentity<WeightUnit> kg = new Quentity<>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm = new Quentity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Is 1 kg == 1000 gm generic check ..............= "+demonstrateWeightEqulity(kg, gm));
        demonstrateWeightComparison(1.0, WeightUnit.TONNE, 1000.0, WeightUnit.KILOGRAM);
        demonstrateWeightConversion(10.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);

        demonstrateWeightAddition(new Quentity<>(2.0, WeightUnit.POUND), new Quentity<>(1.0, WeightUnit.TONNE));
        demonstrateWeightAddition(new Quentity<>(2000.0, WeightUnit.GRAM), new Quentity<>(1.0, WeightUnit.TONNE), WeightUnit.KILOGRAM);

    }

    /*Addition of two weigth and default converter into first Quentity<WeightUnit> unit
    @Param -Quentity<WeightUnit> first Quentity<WeightUnit>
    @Param -weight second weight1
    @Param -WeightUnit e.i result of addition in which unit converting into
     */
    public static Quentity<WeightUnit> demonstrateWeightAddition(Quentity<WeightUnit> weight, Quentity<WeightUnit> weight1, WeightUnit weightUnitTrg) {
        Quentity<WeightUnit> convertedAdd = weight.addAndConvert(weight1, weightUnitTrg);
        System.out.println("Addition of 2 weight and converted in Generic = "+convertedAdd.toString());
        return convertedAdd;
    }

    /*Addition of two weight and default converter into first Quentity<WeightUnit> unit
    @Param -Quentity<WeightUnit> first Quentity<WeightUnit> in which we are getting result
    @Param -weight second weight
     */
    public static Quentity<WeightUnit> demonstrateWeightAddition(Quentity<WeightUnit> weight1, Quentity<WeightUnit> weight2) {
        Quentity<WeightUnit> add = weight1.add(weight2);
        System.out.println("addition with Generic = "+add.toString());
        return add;
    }

    /*Here coversion will happen from one unit into another
    @Param -waight value
    @Param -weight unit
    @Param -conversion target unit
     */
    private static void demonstrateWeightConversion(double v, WeightUnit weightUnit, WeightUnit trgWeightUnit) {
        Quentity<WeightUnit> convertedWeight = demonstrateWeightConversion(new Quentity<>(v, weightUnit), trgWeightUnit);
        System.out.println("Converted weight = "+convertedWeight.toString());
    }

    /*this method is calling to unit conversion
    @Param -weight to pass current weight value
    @Param -WeightUnit as target unit
    @Return -Quentity<WeightUnit> returning converted weight
     */
    public static Quentity<WeightUnit> demonstrateWeightConversion(Quentity<WeightUnit> weight, WeightUnit trgWeightUnit) {
        return new Quentity<WeightUnit>(trgWeightUnit.convertFromBaseUnit(weight.getUnit().convertToBaseUnit(weight.getValue())), trgWeightUnit);
    }

    /* here we are checking 2 weight
    @Param -first weight value
    @Param -first weight unit
    @Param -second weight value
    @Param -second weight unit
     */
    private static void demonstrateWeightComparison(double v, WeightUnit weightUnit, double v1, WeightUnit weightUnit1) {
        System.out.println("Here we are checking the two weights are equals or not = "+demonstrateWeightEqulity(new Quentity<WeightUnit>(v, weightUnit),new Quentity<WeightUnit>(v1, weightUnit1)));
    }

    /* check two weight units and return boolean
    @Param -first weight unit
    @Param -Second weight unit
    @Return -true if both are equals otherwise false
     */
    private static boolean demonstrateWeightEqulity(Quentity<WeightUnit> kg, Quentity<WeightUnit> gm) {
        return kg.equals(gm);
    }

    //functions for Quentity<LengthUnit> operations
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

    public static Quentity<LengthUnit> demonstrateLengthAddition(Quentity<LengthUnit> length, Quentity<LengthUnit> secondLength, LengthUnit targetUnit) {
        Quentity<LengthUnit> convertedAdd = length.addAndConvert(secondLength,targetUnit);
        System.out.println("Addition of 2 length values in target Unit = "+convertedAdd.toString());
        return convertedAdd;
    }

    public static Quentity<LengthUnit> demonstrateLengthAddition(Quentity<LengthUnit> length, Quentity<LengthUnit> that) {

        Quentity<LengthUnit> sumLength= length.add(that);
        System.out.println("Addition of 2 length values............ = "+sumLength.add(that).toString());

        return sumLength;
    }

    public static Quentity<LengthUnit> demonstrateLengthConversion(double value, LengthUnit lengthUnitSrc, LengthUnit lengthUnitTrg) {
        Quentity<LengthUnit> ft = new Quentity<>(value,lengthUnitSrc);
        Quentity<LengthUnit> lt=demonstrateLengthConversion(ft, lengthUnitTrg);
        System.out.println(lt.toString());

        return lt;
    }

    public static Quentity<LengthUnit> demonstrateLengthConversion(Quentity<LengthUnit> ft, LengthUnit lengthUnitTrg) {
        return new Quentity<>(lengthUnitTrg.convertFromBaseUnit(ft.getUnit().convertToBaseUnit(ft.getValue())), lengthUnitTrg);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit lengthUnit1, double value2, LengthUnit lengthUnit2) {
        boolean rs= demonstrateLengthEqulity(new Quentity<>(value1,lengthUnit1),new Quentity<>(value2,lengthUnit2));
        System.out.println(rs);
        return rs;
    }

    private static void demonstrateFeetInchesComparison() {
        Quentity<LengthUnit> ft = new Quentity<>(2.0,LengthUnit.FEET);
        Quentity<LengthUnit> in = new Quentity<>(24.0,LengthUnit.INCHES);

        System.out.println("Feet equals inches = "+ demonstrateLengthEqulity(ft,in));
    }

    public static boolean demonstrateLengthEqulity(Quentity<LengthUnit> ft, Quentity<LengthUnit> in) {
        return ft.equals(in);
    }
}
