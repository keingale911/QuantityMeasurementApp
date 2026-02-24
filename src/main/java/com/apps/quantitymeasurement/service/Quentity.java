package main.java.com.apps.quantitymeasurement.service;

import main.java.com.apps.quantitymeasurement.constants.LengthUnit;
import main.java.com.apps.quantitymeasurement.constants.VolumeUnit;

import java.util.Objects;

/**
  This class stores a value along with its unit (like Length, Weight, etc.).
  It can convert the value from one unit to another.
  It can add two quantities that have compatible units.
  It compares quantities by converting them into a common base unit.
  Useful for doing maths with measurements safely.
 */
public class Quentity<U extends IMeasurable> {
    private double value;
    private final U unit;

    /*
      Quantity constructor with a value and its unit.
      @param value the numeric amount
      @param unit  the unit for this value and unit must not be null
      @throws IllegalArgumentException if unit is null
     */
    public Quentity(double value, U unit) {
        if(unit == null){
            throw new IllegalArgumentException("Unit can't be null");
        }
        this.unit = unit;
        this.value = value;
    }

    //Get the numeric value.
    //@Return the stored this numeric value
    public double getValue() {
        return this.value;
    }

    //Get the unit of this quantity.
    //@Return the unit of this quantity
    public U getUnit() {
        return this.unit;
    }

    //Convert this quantity value to a target unit.
    //Typical pattern is base->target
    //@param targetUnit the unit to convert this value into
    //@param any unit type that implements IMeasurable
    //@Return the converted value expressed in the target uni
    public <U extends IMeasurable> double convertTo(U targetUnit){
        return targetUnit.convertFromBaseUnit(getValue());
    }


    //Add another quantity of the same kind and get the sum in this object unit.
    //@param that the other quantity to add
    //@Return a new quantity whose value is the sum in first unit
    public Quentity<U> add(Quentity<U> that){
        double v = (this.getUnit().convertToBaseUnit(this.getValue())) + (that.getUnit().convertToBaseUnit(that.getValue()));

        return new Quentity<>(this.getUnit().convertFromBaseUnit(v), this.getUnit());
    }

    //Add another quantity and return the sum in a target unit.
    //@param that the other quantity to add
    //@param targetUnit the unit for the result
    //@return a new quantity representing the sum, converted to the target unit
    public Quentity<U> add(Quentity<U> that, U targetUnit){
        Quentity<U> sum= this.add(that);
        sum.value = sum.getUnit().convertToBaseUnit(sum.getValue());
        double convertedSum= sum.convertTo(targetUnit);

        return new Quentity<>(convertedSum,targetUnit);
    }

    //@return text like: Quentity{value=..., unit=...}
    @Override
    public String toString() {
        return "Quentity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }

    //main method to test methods locally
    public static void main(String[] args) {
        Quentity<LengthUnit> ft = new Quentity<>(1.0,LengthUnit.FEET);
        Quentity<LengthUnit> ft1 = new Quentity<>(2.0,LengthUnit.FEET);
        Quentity<LengthUnit> in = new Quentity<>(12.0,LengthUnit.INCHES);

        System.out.println("1 feet in centimeter is = "+ft.convertTo(LengthUnit.CENTIMETER));
        System.out.println("Addition of 2 units = "+ft.add(in));
        System.out.println("Add 2 units and converted = "+ft1.add(in,LengthUnit.YARD));
        System.out.println("Is 2 units are equals = "+ft.equals(in));

        Quentity<VolumeUnit> lr = new Quentity<>(1.0,VolumeUnit.LITRE);
        Quentity<VolumeUnit> ml = new Quentity<>(1000.0,VolumeUnit.MILLILITRE);
        System.out.println("Is 2 valume units are equals = "+lr.add(ml));
    }

    //Check if two quantities are equal by comparing their base-unit values.
    //@param obj the other object to compare
    //@return true if equal in base-unit value, otherwise false
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Quentity<U> other = (Quentity<U>) obj;
        return compare(other);
    }

    //Compute hash based on base-unit value
    //@Return hash code of this quantity
    @Override
    public int hashCode(){
        return Objects.hash(getUnit().convertToBaseUnit(this.getValue()));
    }

    //Compare two quantities by converting both to base unit
    //@param that the other quantity to compare with
    //@return true if both represent the same base-unit value, otherwise false
    public boolean compare(Quentity<U> that) {
        return Double.compare(this.getUnit().convertToBaseUnit(this.getValue()),
                that.getUnit().convertToBaseUnit(that.getValue())) == 0;
    }

    //Convenience method to add and convert in target unit.
    //@param that the other quantity to add
    //@param trgUnit the unit for the resulting sum
    //@return a new quantity representing the sum in the target unit
    public Quentity<U> addAndConvert(Quentity<U> that, U trgUnit) {
        return add(that, trgUnit);
    }
}
