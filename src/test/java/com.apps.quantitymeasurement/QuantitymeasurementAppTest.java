package test.java.com.apps.quantitymeasurement;

import main.java.com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import main.java.com.apps.quantitymeasurement.constants.WeightUnit;
import main.java.com.apps.quantitymeasurement.service.Length;
import main.java.com.apps.quantitymeasurement.*;
import main.java.com.apps.quantitymeasurement.constants.LengthUnit;
import main.java.com.apps.quantitymeasurement.service.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class QuantitymeasurementAppTest {

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        Weight kg1=new Weight(10.0, WeightUnit.KILOGRAM);
        Weight kg2=new Weight(10.0, WeightUnit.KILOGRAM);

        assertEquals(kg1,kg2);
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        Weight kg1=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kg2=new Weight(10.0, WeightUnit.KILOGRAM);

        assertNotEquals(kg1,kg2);
    }

    @Test
    public void testEquality_KilogramToGram_EquevalentValue(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm=new Weight(1000.0, WeightUnit.GRAM);

        assertEquals(kg,gm);
    }

    @Test
    public void testEquality_GramToKilogram_EquevalentValue(){
        Weight gm=new Weight(1000.0, WeightUnit.GRAM);
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Length ft=new Length(1.0, LengthUnit.FEET);

        assertNotEquals(ft, kg);
        //assertNotSame(ft, kg);
    }

    @Test
    public void testEquality_NullComparison(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(null, kg);
    }

    @Test
    public void testEquality_SameReference(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm = kg;

        assertEquals(gm, kg);
    }

    //Test 8 and 9 have to add here
//    @Test
//    public void testEquality_NullUnit(){
//        Weight nkg=new Weight(1.0, null);
//        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
//
//        assertThrows(IllegalArgumentException.class,()->{new Weight(1.0, null).equals(kg);});
//    }

    @Test
    public void testEquality_ZeroValue(){
        Weight gm=new Weight(0.0, WeightUnit.GRAM);
        Weight kg=new Weight(0.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_NegativeValue(){
        Weight gm=new Weight(-1000.0, WeightUnit.GRAM);
        Weight kg=new Weight(-1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_LargeWeightValue(){
        Weight gm=new Weight(100.0, WeightUnit.TONNE);
        Weight kg=new Weight(1_00_000.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_SmallWeightValue(){
        Weight gm=new Weight(0.001, WeightUnit.TONNE);
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testConversion_PoundToKilogram(){
        Weight pd=new Weight(2.20462, WeightUnit.POUND);
        Weight pdIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(pd,WeightUnit.KILOGRAM);
        Weight expectedKg=new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(pdIntoKg,expectedKg);
    }

    @Test
    public void testConversion_KilogramToPound(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kgIntoPd = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.POUND);
        Weight expectedPd=new Weight(2.20462, WeightUnit.POUND);

        assertEquals(kgIntoPd,expectedPd);
    }

    @Test
    public void testConversion_SameUnit(){
        Weight kg=new Weight(5.0, WeightUnit.KILOGRAM);
        Weight kgIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.KILOGRAM);
        Weight expectedKg=new Weight(5.0, WeightUnit.KILOGRAM);

        assertEquals(kgIntoKg,expectedKg);
    }

    @Test
    public void testConversion_ZeroValue(){
        Weight kg=new Weight(0.0, WeightUnit.KILOGRAM);
        Weight kgIntoGm = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.GRAM);
        Weight expectedGm=new Weight(0.0, WeightUnit.GRAM);

        assertEquals(kgIntoGm,expectedGm);
    }

    @Test
    public void testConversion_NegativeValue(){
        Weight ngKg=new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight ngKgIntoNgGm = QuantityMeasurementApp.demonstrateWeightConversion(ngKg,WeightUnit.GRAM);
        Weight expectedNgGm=new Weight(-1000.0, WeightUnit.GRAM);

        assertEquals(ngKgIntoNgGm,expectedNgGm);
    }

    @Test
    public void testConversion_RoundTrip(){
        Weight kg=new Weight(1.5, WeightUnit.KILOGRAM);
        Weight kgIntoGm = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.GRAM);
        Weight gmIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(kgIntoGm,WeightUnit.KILOGRAM);
        Weight expectedKm=new Weight(1.5, WeightUnit.KILOGRAM);

        assertEquals(gmIntoKg,expectedKm);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram(){
        Weight kg=new Weight(2.0, WeightUnit.KILOGRAM);
        Weight kg1=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kgAddition = QuantityMeasurementApp.demonstrateWeightAddition(kg,kg1);
        Weight expectedAddition=new Weight(3.0, WeightUnit.KILOGRAM);

        assertEquals(kgAddition,expectedAddition);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusGram(){
        Weight kg=new Weight(1.5, WeightUnit.KILOGRAM);
        Weight gm=new Weight(1500.0, WeightUnit.GRAM);
        Weight kgnGmAddition = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm);
        Weight expectedAddition=new Weight(3.0, WeightUnit.KILOGRAM);

        assertEquals(kgnGmAddition,expectedAddition);
    }

    //Test case 22 not clear

    @Test
    public void testAddition_CrossUnit_PoundPlusGKilogram(){
        Weight pd=new Weight(2.20462, WeightUnit.POUND);
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight additionInPound = QuantityMeasurementApp.demonstrateWeightAddition(pd,kg);
        Weight expectedAddition=new Weight(4.40924, WeightUnit.POUND);

        assertEquals(additionInPound,expectedAddition);
    }

    @Test
    public void testAddition_ExplicitTargateUnit_Kilogram(){
        Weight kg=new Weight(2.0, WeightUnit.KILOGRAM);
        Weight gm=new Weight(1000.0, WeightUnit.GRAM);
        Weight additionAndConvert = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm, WeightUnit.GRAM);
        Weight expectedAddition=new Weight(3000.0, WeightUnit.GRAM);

        assertEquals(additionAndConvert,expectedAddition);
    }

    @Test
    public void testAddition_Commutativity(){
        Weight kg=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm=new Weight(1000.0, WeightUnit.GRAM);
        Weight addition1 = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm, WeightUnit.GRAM);
        Weight tn=new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm1=new Weight(1000.0, WeightUnit.GRAM);
        Weight addition2 = QuantityMeasurementApp.demonstrateWeightAddition(tn,gm1, WeightUnit.KILOGRAM);

        assertEquals(addition1,addition2);
    }

    @Test
    public void testAddition_WithZero(){
        Weight pd=new Weight(5.0, WeightUnit.TONNE);
        Weight kg=new Weight(0.0, WeightUnit.KILOGRAM);
        Weight additionInPoundWithZero = QuantityMeasurementApp.demonstrateWeightAddition(pd,kg);
        Weight expectedAddition=new Weight(5.0, WeightUnit.TONNE);

        assertEquals(additionInPoundWithZero,expectedAddition);
    }

    @Test
    public void testAddition_NegativeValue(){
        Weight kg=new Weight(5.0, WeightUnit.KILOGRAM);
        Weight gm=new Weight(-2000.0, WeightUnit.GRAM);
        Weight additionInPoundWithZero = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm);
        Weight expectedAddition=new Weight(3.0, WeightUnit.KILOGRAM);

        assertEquals(additionInPoundWithZero,expectedAddition);
    }

    @Test
    public void testAddition_LargeValue(){
        Weight kg=new Weight(2e6, WeightUnit.KILOGRAM);
        Weight kg1=new Weight(1e6, WeightUnit.KILOGRAM);
        Weight additionLargeUnitValue = QuantityMeasurementApp.demonstrateWeightAddition(kg,kg1);
        Weight expectedAddition=new Weight(3e6, WeightUnit.KILOGRAM);

        assertEquals(additionLargeUnitValue,expectedAddition);
    }

    //Test case for Length
    //Test cases for feet UC1
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

        assertNotEquals(null, f1);
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

    //Test cases for Inches UC2
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

    //Test cases for Length UC3
    @Test
    public void testFeetEquality() {
        Length f1 = new Length(1.0, LengthUnit.FEET);
        Length f2 = new Length(1.0,LengthUnit.FEET);

        assertEquals(f1, f2);
    }

    @Test
    public void testInchesEquality() {
        Length in1 = new Length(12.0,LengthUnit.INCHES);
        Length in2 = new Length(12.0,LengthUnit.INCHES);

        assertEquals(in1, in2);
    }

    @Test
    public void testFeetInchesComparison() {
        Length ft = new Length(1.0,LengthUnit.FEET);
        Length in = new Length(12.0,LengthUnit.INCHES);

        assertEquals(ft, in);
    }

    @Test
    public void testFeetInequality() {
        Length ft1 = new Length(1.0,LengthUnit.FEET);
        Length ft2 = new Length(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testInchesInequality() {
        Length ft1 = new Length(12.0,LengthUnit.INCHES);
        Length ft2 = new Length(24.0,LengthUnit.INCHES);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testCrossUnitInequality() {
        Length ft1 = new Length(12.0,LengthUnit.INCHES);
        Length ft2 = new Length(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testMultiFeetComparison() {
        Length ft1 = new Length(3.0,LengthUnit.FEET);
        Length ft2 = new Length(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    //Test cases for Yard and Centimeter UC4
    @Test
    public void yardEquals36Inches() {
        Length yd = new Length(1.0,LengthUnit.YARD);
        Length in = new Length(36.0,LengthUnit.INCHES);

        assertEquals(yd, in);
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length in = new Length(39.3701,LengthUnit.INCHES);
        Length cm = new Length(100.0,LengthUnit.CENTIMETER);

        assertEquals(in, cm);
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length ft = new Length(3.0,LengthUnit.FEET);
        Length yd = new Length(1.0,LengthUnit.YARD);

        assertEquals(ft, yd);
    }

    @Test
    public void thrityPoint48CmEqualsOneFeet() {
        Length cm = new Length(30.48,LengthUnit.CENTIMETER);
        Length ft = new Length(1.0,LengthUnit.FEET);

        assertEquals(cm, ft);
    }

    @Test
    public void yardNotEqualsToInches() {
        Length yd = new Length(1.0,LengthUnit.YARD);
        Length in = new Length(12.0,LengthUnit.INCHES);

        assertNotEquals(yd, in);
    }

    @ParameterizedTest
    @EnumSource(LengthUnit.class)
    public void referenceEqualitySameObject(LengthUnit unit) {
        Length length = new Length(1.0,unit);

        assertEquals(length, length);
    }

    @ParameterizedTest
    @EnumSource(LengthUnit.class)
    public void equalsReturnsFalseForNull(LengthUnit unit) {
        Length length = new Length(1.0, unit);

        assertNotEquals(length, null);
    }

    @Test
    public void refexivSymmetricTransitiveProperty() {
        Length feet = new Length(1.0,LengthUnit.FEET);
        Length inches = new Length(12.0,LengthUnit.INCHES);
        Length centimeter = new Length(30.48,LengthUnit.CENTIMETER);
        Length yard = new Length(0.3333,LengthUnit.YARD);

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
    @EnumSource(LengthUnit.class)
    public void differentValuesSameUnitNotEquals(LengthUnit unit) {
        Length length1 = new Length(12.0,unit);
        Length length2 = new Length(1.0,unit);

        assertNotEquals(length1, length2);
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {

        assertAll(()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,LengthUnit.FEET,12.0,LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,LengthUnit.YARD,36.0,LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(100.0,LengthUnit.CENTIMETER,39.3701,LengthUnit.INCHES)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(3.0,LengthUnit.FEET,1.0,LengthUnit.YARD)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(30.48,LengthUnit.CENTIMETER,1.0,LengthUnit.FEET)),
                ()->assertTrue( QuantityMeasurementApp.demonstrateLengthComparison(1.0,LengthUnit.YARD,91.44,LengthUnit.CENTIMETER)));
    }

    //Test cases for Unit conversion UC5
    @Test
    public void convertFeetToInches(){
        Length inInches = QuantityMeasurementApp.demonstrateLengthConversion(2.0,LengthUnit.FEET, LengthUnit.INCHES);
        Length expectedLength = new Length(24.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches, expectedLength));
    }

    @Test
    public void convertYardToInchesUsingOverloadedMethod(){
        Length inYard = new Length(2.0, LengthUnit.YARD);
        Length inInches = QuantityMeasurementApp.demonstrateLengthConversion(inYard, LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches,expectedLength));
    }

    //Test case for 2 Length value addition UC6
    @Test
    public void addFeetAndInches(){
        Length l1Feet = new Length(2.0, LengthUnit.FEET);
        Length l2Inches = new Length(12.0, LengthUnit.INCHES);
        Length addedLength= QuantityMeasurementApp.demonstrateLengthAddition(l1Feet,l2Inches);
        Length expectedLength = new Length(3.0, LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(addedLength,expectedLength));
    }

    //Test case to add 2 Length values and convert it into target unit UC7
    @Test
    public void addFeetAndInchesWithTargetUnitInches(){
        Length l1Feet = new Length(1.0, LengthUnit.FEET);
        Length l2Inches = new Length(12.0, LengthUnit.INCHES);
        Length addedLength= QuantityMeasurementApp.demonstrateLengthAddition(l1Feet,l2Inches, LengthUnit.INCHES);
        Length expectedLength = new Length(24.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(addedLength,expectedLength));
        assertEquals(addedLength,expectedLength);
    }
}