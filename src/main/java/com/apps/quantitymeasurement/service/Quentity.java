package main.java.com.apps.quantitymeasurement.service;

import main.java.com.apps.quantitymeasurement.constants.LengthUnit;

import java.util.Objects;

public class Quentity<U extends IMeasurable> {
    private double value;
    private U unit;

    public Quentity(double value, U unit) {
        if(unit == null){
            throw new IllegalArgumentException("Unit can't be null");
        }
        this.unit = unit;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public <U extends IMeasurable> double convertTo(U targetUnit){
        return targetUnit.convertFromBaseUnit(getValue());
    }

    public Quentity<U> add(Quentity<U> that){
        double v = (this.getUnit().convertToBaseUnit(this.getValue())) + (that.getUnit().convertToBaseUnit(that.getValue()));

        return new Quentity<>(this.getUnit().convertFromBaseUnit(v), this.getUnit());
    }

    public Quentity<U> add(Quentity<U> that, U targetUnit){
        Quentity<U> sum= this.add(that);
        sum.value = sum.getUnit().convertToBaseUnit(sum.getValue());
        double convertedSum= sum.convertTo(targetUnit);

        return new Quentity<>(convertedSum,targetUnit);
    }

    @Override
    public String toString() {
        return "Quentity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }

    public static void main(String[] args) {
        Quentity<LengthUnit> ft = new Quentity<>(1.0,LengthUnit.FEET);
        Quentity<LengthUnit> ft1 = new Quentity<>(2.0,LengthUnit.FEET);
        Quentity<LengthUnit> in = new Quentity<>(12.0,LengthUnit.INCHES);

        System.out.println("1 feet in centimeter is = "+ft.convertTo(LengthUnit.CENTIMETER));
        System.out.println("Addition of 2 units = "+ft.add(in));
        System.out.println("Add 2 units and converted = "+ft1.add(in,LengthUnit.YARD));
        System.out.println("Is 2 units are equals = "+ft.equals(in));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Quentity<U> other = (Quentity<U>) obj;
        return compare(other);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUnit().convertToBaseUnit(this.getValue()));
    }

    public boolean compare(Quentity<U> that) {
        return Double.compare(this.getUnit().convertToBaseUnit(this.getValue()),
                that.getUnit().convertToBaseUnit(that.getValue())) == 0;
    }

    public Quentity<U> addAndConvert(Quentity<U> that, U trgUnit) {
        return add(that, trgUnit);
    }
}
