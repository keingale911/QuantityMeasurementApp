package com.apps.quantitymeasurement;

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
    }

    private static void demonstrateFeetInchesComparison() {
        Length ft = new Length(2.0,Length.LengthUnit.FEET);
        Length in = new Length(24.0,Length.LengthUnit.INCHES);

        System.out.println("Feet equals inches = "+ demonstrateLengthEqulity(ft,in));
    }

    private static boolean demonstrateLengthEqulity(Length ft, Length in) {
        return ft.equals(in);
    }
}
