package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    public static class Feet{
        private final double value;


        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj){

            if(this==obj){
                return true;
            }
            if(this==null){
                return false;
            }
            if(!(obj instanceof Feet)){
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value)==0;
        }
    }
    public static class Inches{

    }

    public static void main(String[] args) {
        Feet f1=new Feet(2.5);
        Feet f2=new Feet(1.5);
        Feet f3=new Feet(2.5);
        Inches inch=new Inches();

        System.out.println(f1.equals(f3));
        System.out.println(f2.equals(f1));
        System.out.println(f3.equals(f1));
        System.out.println(f3.equals(null));
        System.out.println(f3.equals(f3));
        System.out.println(f3.equals(inch));
    }
}
