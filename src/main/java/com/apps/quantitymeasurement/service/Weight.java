package main.java.com.apps.quantitymeasurement.service;

import main.java.com.apps.quantitymeasurement.constants.WeightUnit;

import java.util.Objects;

public class Weight {
    public final double value;
    public final WeightUnit weightUnit;


    public Weight(double value, WeightUnit weightUnit) {
        this.value = value;
        this.weightUnit = weightUnit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Weight other = (Weight) obj;
        return compare(other);
    }

    @Override
    public int hashCode(){
        return Objects.hash(weightUnit.convertToBaseUnit(this.value));
    }

    public boolean compare(Weight that) {
        return Double.compare(this.weightUnit.convertToBaseUnit(this.value),
                that.weightUnit.convertToBaseUnit(that.value)) == 0;
    }

    @Override
    public String toString() {
        return "Length { " +
                "value = " + value +
                "  " + weightUnit +
                '}';
    }

    public static void main(String[] args) {
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm=new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("is Weight equals = "+ kg.equals(gm));

        System.out.println("4000 Kg in tonne = "+WeightUnit.KILOGRAM.convertFromBaseUnit(4000.0,WeightUnit.TONNE));

        Weight kgm=new Weight(1000.0, WeightUnit.KILOGRAM);
        Weight pn=new Weight(100.0, WeightUnit.POUND);
        System.out.println("kg + Pound = "+ kgm.add(pn).toString());


        Weight klg=new Weight(10000.0, WeightUnit.KILOGRAM);
        Weight grm=new Weight(1000000.0, WeightUnit.GRAM);
        System.out.println("Sum of Kg and Gm converted into Tonne ="+ klg.addAndConvert(grm, WeightUnit.TONNE).toString());

    }

    private Weight addAndConvert(Weight weight2, WeightUnit weightTrgUnit) {
        return add(weight2, weightTrgUnit);
    }

    private Weight add(Weight weight2, WeightUnit weightTrgUnit) {
        Weight sumInBase = add(weight2);

        return new Weight(sumInBase.weightUnit.convertFromBaseUnit(sumInBase.value, weightTrgUnit), weightTrgUnit);
    }

    private Weight add(Weight gm) {

        if (gm == null){
            throw new IllegalArgumentException("Please enter valid weight");
        }

        double thisGram = this.weightUnit.convertToBaseUnit(this.value);
        double thatGram = gm.weightUnit.convertToBaseUnit(gm.value);
        double sumInGram = thisGram + thatGram;

        double sumInThisUnit = sumInGram / this.weightUnit.getConversionFactor();
        sumInThisUnit = Math.round(sumInThisUnit * 100.0) / 100.0;
        return new Weight(sumInThisUnit, this.weightUnit);
    }
}
