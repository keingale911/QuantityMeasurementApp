package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class QuantitymeasurementAppTest {

    //Test cases for feet
    @Test
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.5);
        Feet f2 = new Feet(1.5);

        assertEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_DiffValue() {
        Feet f1 = new Feet(1.5);
        Feet f2 = new Feet(2.5);

        assertNotEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_NullValue() {
        Feet f1 = new Feet(1.5);

        assertNotEquals(f1, null);
    }

    @Test
    public void testFeetEquality_DiffObjValue() {
        Feet f1 = new Feet(1.5);

        assertNotEquals(f1, new Inches(24.0));
    }

    @Test
    public void testFeetEquality_SameRefValue() {
        Feet f1 = new Feet(1.5);

        assertEquals(f1, f1);
    }

    //Test cases for Inches
    @Test
    public void testInchesEquality_SameValue() {
        Inches in1 = new Inches(12.0);
        Inches in2 = new Inches(12.0);

        assertEquals(in1, in2);
    }

    @Test
    public void testInchesEquality_DiffValue() {
        Inches in1 = new Inches(12.0);
        Inches in2 = new Inches(24.0);

        assertNotEquals(in1, in2);
    }

    @Test
    public void testInchesEquality_NullValue() {
        Inches in1 = new Inches(12.0);

        assertNotEquals(in1, null);
    }

    @Test
    public void testInchesEquality_DiffObjValue() {
        Inches in1 = new Inches(12.0);

        assertNotEquals(in1, new Feet(2.0));
    }

    @Test
    public void testInchesEquality_SameRefValue() {
        Inches in1 = new Inches(12.0);

        assertEquals(in1, in1);
    }

    //Test cases for Length
    @Test
    public void testFeetEquality() {
        Length f1 = new Length(1.0,Length.LengthUnit.FEET);
        Length f2 = new Length(1.0,Length.LengthUnit.FEET);

        assertEquals(f1, f2);
    }

    @Test
    public void testInchesEquality() {
        Length in1 = new Length(12.0,Length.LengthUnit.INCHES);
        Length in2 = new Length(12.0,Length.LengthUnit.INCHES);

        assertEquals(in1, in2);
    }

    @Test
    public void testFeetInchesComparison() {
        Length ft = new Length(2.0,Length.LengthUnit.FEET);
        Length in = new Length(24.0,Length.LengthUnit.INCHES);

        assertEquals(ft, in);
    }

    @Test
    public void testFeetInequality() {
        Length ft1 = new Length(1.0,Length.LengthUnit.FEET);
        Length ft2 = new Length(2.0,Length.LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testInchesInequality() {
        Length ft1 = new Length(12.0,Length.LengthUnit.INCHES);
        Length ft2 = new Length(24.0,Length.LengthUnit.INCHES);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testCrossUnitInequality() {
        Length ft1 = new Length(12.0,Length.LengthUnit.INCHES);
        Length ft2 = new Length(2.0,Length.LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testMultiFeetComparison() {
        Length ft1 = new Length(3.0,Length.LengthUnit.FEET);
        Length ft2 = new Length(2.0,Length.LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    //Test cases for Yard and Centimeter
    @Test
    public void yardEquals36Inches() {
        Length yd = new Length(1.0,Length.LengthUnit.YARD);
        Length in = new Length(36.0,Length.LengthUnit.INCHES);

        assertEquals(yd, in);
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length in = new Length(39.3701,Length.LengthUnit.INCHES);
        Length cm = new Length(100.0,Length.LengthUnit.CENTIMETER);

        assertEquals(cm, in);
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length ft = new Length(3.0,Length.LengthUnit.FEET);
        Length yd = new Length(1.0,Length.LengthUnit.YARD);

        assertEquals(ft, yd);
    }

    @Test
    public void thrityPoint48CmEqualsOneFeet() {
        Length cm = new Length(30.48,Length.LengthUnit.CENTIMETER);
        Length ft = new Length(1.0,Length.LengthUnit.FEET);

        assertEquals(cm, ft);
    }

    @Test
    public void yardNotEqualsToInches() {
        Length yd = new Length(1.0,Length.LengthUnit.YARD);
        Length in = new Length(12.0,Length.LengthUnit.INCHES);

        assertNotEquals(yd, in);
    }

    @ParameterizedTest
    @EnumSource(Length.LengthUnit.class)
    public void referenceEqualitySameObject(Length.LengthUnit unit) {
        Length length = new Length(1.0,unit);

        assertEquals(length, length);
    }

    @ParameterizedTest
    @EnumSource(Length.LengthUnit.class)
    public void equalsReturnsFalseForNull(Length.LengthUnit unit) {
        Length length = new Length(1.0, unit);

        assertNotEquals(length, null);
    }

    @Test
    public void refexivSymmetricTransitiveProperty() {
        Length feet = new Length(1.0,Length.LengthUnit.FEET);
        Length inches = new Length(12.0,Length.LengthUnit.INCHES);
        Length centimeter = new Length(30.48,Length.LengthUnit.CENTIMETER);
        Length yard = new Length(0.3333,Length.LengthUnit.YARD);

        //Reflexive
        assertEquals(centimeter, centimeter);

        //Symmetric
        assertEquals(feet, inches);
        assertEquals(inches, feet);

        //Transitive
        assertEquals(feet, inches);
        assertEquals(inches, centimeter);
        assertEquals(centimeter, yard);
    }

    @ParameterizedTest
    @EnumSource(Length.LengthUnit.class)
    public void differentValuesSameUnitNotEquals(Length.LengthUnit unit) {
        Length length1 = new Length(12.0,unit);
        Length length2 = new Length(1.0,unit);

        assertNotEquals(length1, length2);
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {

        assertAll(()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,Length.LengthUnit.FEET,12.0,Length.LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,Length.LengthUnit.YARD,36.0,Length.LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(100.0,Length.LengthUnit.CENTIMETER,39.3701,Length.LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(3.0,Length.LengthUnit.FEET,1.0,Length.LengthUnit.YARD)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(30.48,Length.LengthUnit.CENTIMETER,1.0,Length.LengthUnit.FEET)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,Length.LengthUnit.YARD,91.44,Length.LengthUnit.CENTIMETER)));
    }

    //Test cases for Unit conversion
    @Test
    public void convertFeetToInches(){
        Length inInches = QuantityMeasurementApp.demonstrateLengthConversion(2.0,Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(24.0, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches, expectedLength));
    }

    @Test
    public void convertYardToInchesUsingOverloadedMethod(){
        Length inYard = new Length(2.0, Length.LengthUnit.YARD);
        Length inInches = QuantityMeasurementApp.demonstrateLengthConversion(inYard, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches,expectedLength));
    }

    //Test case for 2 Length value addition
    @Test
    public void addFeetAndInches(){
        Length l1Feet = new Length(2.0, Length.LengthUnit.FEET);
        Length l2Inches = new Length(12.0, Length.LengthUnit.INCHES);
        Length addedLength= QuantityMeasurementApp.demonstrateLengthAddition(l1Feet,l2Inches);
        Length expectedLength = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(addedLength,expectedLength));
    }
}