package test.java.com.apps.quantitymeasurement;

import main.java.com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import main.java.com.apps.quantitymeasurement.constants.WeightUnit;
import main.java.com.apps.quantitymeasurement.*;
import main.java.com.apps.quantitymeasurement.constants.LengthUnit;
import main.java.com.apps.quantitymeasurement.service.Quentity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class QuantitymeasurementAppTest {

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        Quentity<WeightUnit> kg1=new Quentity<WeightUnit>(10.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kg2=new Quentity<WeightUnit>(10.0, WeightUnit.KILOGRAM);

        assertEquals(kg1,kg2);
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        Quentity<WeightUnit> kg1=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kg2=new Quentity<WeightUnit>(10.0, WeightUnit.KILOGRAM);

        assertNotEquals(kg1,kg2);
    }

    @Test
    public void testEquality_KilogramToGram_EquevalentValue(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(1000.0, WeightUnit.GRAM);

        assertEquals(kg,gm);
    }

    @Test
    public void testEquality_GramToKilogram_EquevalentValue(){
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(1000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<LengthUnit> ft=new Quentity<LengthUnit>(1.0, LengthUnit.FEET);

        assertNotEquals(ft, kg);
        //assertNotSame(ft, kg);
    }

    @Test
    public void testEquality_NullComparison(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(null, kg);
    }

    @Test
    public void testEquality_SameReference(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm = kg;

        assertEquals(gm, kg);
    }

    @Test
    public void testEquality_reflexiveSymmetricTransitiveProperty() {
        Quentity<WeightUnit> feet = new Quentity<WeightUnit>(1.0,WeightUnit.KILOGRAM);
        Quentity<WeightUnit> inches = new Quentity<WeightUnit>(1000.0,WeightUnit.GRAM);
        Quentity<WeightUnit> centimeter = new Quentity<WeightUnit>(2.20462,WeightUnit.POUND);
        Quentity<WeightUnit> yard = new Quentity<WeightUnit>(0.001,WeightUnit.TONNE);

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

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, ()-> new Quentity<WeightUnit>(1.0,null));
    }

        @Test
    public void testEquality_ZeroValue(){
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(0.0, WeightUnit.GRAM);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(0.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_NegativeValue(){
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(-1000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(-1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_LargeWeightValue(){
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(100.0, WeightUnit.TONNE);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1_00_000.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testEquality_SmallWeightValue(){
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(0.001, WeightUnit.TONNE);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        assertEquals(gm,kg);
    }

    @Test
    public void testConversion_PoundToKilogram(){
        Quentity<WeightUnit> pd=new Quentity<WeightUnit>(2.20462, WeightUnit.POUND);
        Quentity<WeightUnit> pdIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(pd,WeightUnit.KILOGRAM);
        Quentity<WeightUnit> expectedKg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);

        assertEquals(pdIntoKg,expectedKg);
    }

    @Test
    public void testConversion_KilogramToPound(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kgIntoPd = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.POUND);
        Quentity<WeightUnit> expectedPd=new Quentity<WeightUnit>(2.20462, WeightUnit.POUND);

        assertEquals(kgIntoPd,expectedPd);
    }

    @Test
    public void testConversion_SameUnit(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(5.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kgIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.KILOGRAM);
        Quentity<WeightUnit> expectedKg=new Quentity<WeightUnit>(5.0, WeightUnit.KILOGRAM);

        assertEquals(kgIntoKg,expectedKg);
    }

    @Test
    public void testConversion_ZeroValue(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(0.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kgIntoGm = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.GRAM);
        Quentity<WeightUnit> expectedGm=new Quentity<WeightUnit>(0.0, WeightUnit.GRAM);

        assertEquals(kgIntoGm,expectedGm);
    }

    @Test
    public void testConversion_NegativeValue(){
        Quentity<WeightUnit> ngKg=new Quentity<WeightUnit>(-1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> ngKgIntoNgGm = QuantityMeasurementApp.demonstrateWeightConversion(ngKg,WeightUnit.GRAM);
        Quentity<WeightUnit> expectedNgGm=new Quentity<WeightUnit>(-1000.0, WeightUnit.GRAM);

        assertEquals(ngKgIntoNgGm,expectedNgGm);
    }

    @Test
    public void testConversion_RoundTrip(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.5, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kgIntoGm = QuantityMeasurementApp.demonstrateWeightConversion(kg,WeightUnit.GRAM);
        Quentity<WeightUnit> gmIntoKg = QuantityMeasurementApp.demonstrateWeightConversion(kgIntoGm,WeightUnit.KILOGRAM);
        Quentity<WeightUnit> expectedKm=new Quentity<WeightUnit>(1.5, WeightUnit.KILOGRAM);

        assertEquals(gmIntoKg,expectedKm);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(2.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kg1=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kgAddition = QuantityMeasurementApp.demonstrateWeightAddition(kg,kg1);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(3.0, WeightUnit.KILOGRAM);

        assertEquals(kgAddition,expectedAddition);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusGram(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.5, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(1500.0, WeightUnit.GRAM);
        Quentity<WeightUnit> kgnGmAddition = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(3.0, WeightUnit.KILOGRAM);

        assertEquals(kgnGmAddition,expectedAddition);
    }

    //UC9 Test case 22 not clear

    @Test
    public void testAddition_CrossUnit_PoundPlusGKilogram(){
        Quentity<WeightUnit> pd=new Quentity<WeightUnit>(2.20462, WeightUnit.POUND);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> additionInPound = QuantityMeasurementApp.demonstrateWeightAddition(pd,kg);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(4.40924, WeightUnit.POUND);

        assertEquals(additionInPound,expectedAddition);
    }

    @Test
    public void testAddition_ExplicitTargateUnit_Kilogram(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(2.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(1000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> additionAndConvert = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm, WeightUnit.GRAM);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(3000.0, WeightUnit.GRAM);

        assertEquals(additionAndConvert,expectedAddition);
    }

    @Test
    public void testAddition_Commutativity(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(1000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> addition1 = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm, WeightUnit.GRAM);
        Quentity<WeightUnit> tn=new Quentity<WeightUnit>(1.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm1=new Quentity<WeightUnit>(1000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> addition2 = QuantityMeasurementApp.demonstrateWeightAddition(tn,gm1, WeightUnit.KILOGRAM);

        assertEquals(addition1,addition2);
    }

    @Test
    public void testAddition_WithZero(){
        Quentity<WeightUnit> pd=new Quentity<WeightUnit>(5.0, WeightUnit.TONNE);
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(0.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> additionInPoundWithZero = QuantityMeasurementApp.demonstrateWeightAddition(pd,kg);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(5.0, WeightUnit.TONNE);

        assertEquals(additionInPoundWithZero,expectedAddition);
    }

    @Test
    public void testAddition_NegativeValue(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(5.0, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> gm=new Quentity<WeightUnit>(-2000.0, WeightUnit.GRAM);
        Quentity<WeightUnit> additionInPoundWithZero = QuantityMeasurementApp.demonstrateWeightAddition(kg,gm);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(3.0, WeightUnit.KILOGRAM);

        assertEquals(additionInPoundWithZero,expectedAddition);
    }

    @Test
    public void testAddition_LargeValue(){
        Quentity<WeightUnit> kg=new Quentity<WeightUnit>(2e6, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> kg1=new Quentity<WeightUnit>(1e6, WeightUnit.KILOGRAM);
        Quentity<WeightUnit> additionLargeUnitValue = QuantityMeasurementApp.demonstrateWeightAddition(kg,kg1);
        Quentity<WeightUnit> expectedAddition=new Quentity<WeightUnit>(3e6, WeightUnit.KILOGRAM);

        assertEquals(additionLargeUnitValue,expectedAddition);
    }

    //Test case for Quentity<LengthUnit>
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

    //Test cases for Quentity<LengthUnit> UC3
    @Test
    public void testFeetEquality() {
        Quentity<LengthUnit> f1 = new Quentity<LengthUnit>(1.0, LengthUnit.FEET);
        Quentity<LengthUnit> f2 = new Quentity<LengthUnit>(1.0,LengthUnit.FEET);

        assertEquals(f1, f2);
    }

    @Test
    public void testInchesEquality() {
        Quentity<LengthUnit> in1 = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);
        Quentity<LengthUnit> in2 = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);

        assertEquals(in1, in2);
    }

    @Test
    public void testFeetInchesComparison() {
        Quentity<LengthUnit> ft = new Quentity<LengthUnit>(1.0,LengthUnit.FEET);
        Quentity<LengthUnit> in = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);

        assertEquals(ft, in);
    }

    @Test
    public void testFeetInequality() {
        Quentity<LengthUnit> ft1 = new Quentity<LengthUnit>(1.0,LengthUnit.FEET);
        Quentity<LengthUnit> ft2 = new Quentity<LengthUnit>(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testInchesInequality() {
        Quentity<LengthUnit> ft1 = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);
        Quentity<LengthUnit> ft2 = new Quentity<LengthUnit>(24.0,LengthUnit.INCHES);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testCrossUnitInequality() {
        Quentity<LengthUnit> ft1 = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);
        Quentity<LengthUnit> ft2 = new Quentity<LengthUnit>(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    @Test
    public void testMultiFeetComparison() {
        Quentity<LengthUnit> ft1 = new Quentity<LengthUnit>(3.0,LengthUnit.FEET);
        Quentity<LengthUnit> ft2 = new Quentity<LengthUnit>(2.0,LengthUnit.FEET);

        assertNotEquals(ft1, ft2);
    }

    //Test cases for Yard and Centimeter UC4
    @Test
    public void yardEquals36Inches() {
        Quentity<LengthUnit> yd = new Quentity<LengthUnit>(1.0,LengthUnit.YARD);
        Quentity<LengthUnit> in = new Quentity<LengthUnit>(36.0,LengthUnit.INCHES);

        assertEquals(yd, in);
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Quentity<LengthUnit> in = new Quentity<LengthUnit>(39.3701,LengthUnit.INCHES);
        Quentity<LengthUnit> cm = new Quentity<LengthUnit>(100.0,LengthUnit.CENTIMETER);

        assertEquals(in, cm);
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Quentity<LengthUnit> ft = new Quentity<LengthUnit>(3.0,LengthUnit.FEET);
        Quentity<LengthUnit> yd = new Quentity<LengthUnit>(1.0,LengthUnit.YARD);

        assertEquals(ft, yd);
    }

    @Test
    public void thrityPoint48CmEqualsOneFeet() {
        Quentity<LengthUnit> cm = new Quentity<LengthUnit>(30.48,LengthUnit.CENTIMETER);
        Quentity<LengthUnit> ft = new Quentity<LengthUnit>(1.0,LengthUnit.FEET);

        assertEquals(cm, ft);
    }

    @Test
    public void yardNotEqualsToInches() {
        Quentity<LengthUnit> yd = new Quentity<LengthUnit>(1.0,LengthUnit.YARD);
        Quentity<LengthUnit> in = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);

        assertNotEquals(yd, in);
    }

    @ParameterizedTest
    @EnumSource(LengthUnit.class)
    public void referenceEqualitySameObject(LengthUnit unit) {
        Quentity<LengthUnit> length = new Quentity<LengthUnit>(1.0,unit);

        assertEquals(length, length);
    }

    @ParameterizedTest
    @EnumSource(LengthUnit.class)
    public void equalsReturnsFalseForNull(LengthUnit unit) {
        Quentity<LengthUnit> length = new Quentity<LengthUnit>(1.0, unit);

        assertNotEquals(length, null);
    }

    @Test
    public void refexivSymmetricTransitiveProperty() {
        Quentity<LengthUnit> feet = new Quentity<LengthUnit>(1.0,LengthUnit.FEET);
        Quentity<LengthUnit> inches = new Quentity<LengthUnit>(12.0,LengthUnit.INCHES);
        Quentity<LengthUnit> centimeter = new Quentity<LengthUnit>(30.48,LengthUnit.CENTIMETER);
        Quentity<LengthUnit> yard = new Quentity<LengthUnit>(0.3333,LengthUnit.YARD);

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
        Quentity<LengthUnit> length1 = new Quentity<LengthUnit>(12.0,unit);
        Quentity<LengthUnit> length2 = new Quentity<LengthUnit>(1.0,unit);

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
        Quentity<LengthUnit> inInches = QuantityMeasurementApp.demonstrateLengthConversion(2.0,LengthUnit.FEET, LengthUnit.INCHES);
        Quentity<LengthUnit> expectedLength = new Quentity<LengthUnit>(24.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches, expectedLength));
    }

    @Test
    public void convertYardToInchesUsingOverloadedMethod(){
        Quentity<LengthUnit> inYard = new Quentity<LengthUnit>(2.0, LengthUnit.YARD);
        Quentity<LengthUnit> inInches = QuantityMeasurementApp.demonstrateLengthConversion(inYard, LengthUnit.INCHES);
        Quentity<LengthUnit> expectedLength = new Quentity<LengthUnit>(72.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(inInches,expectedLength));
    }

    //Test case for 2 Quentity<LengthUnit> value addition UC6
    @Test
    public void addFeetAndInches(){
        Quentity<LengthUnit> l1Feet = new Quentity<LengthUnit>(2.0, LengthUnit.FEET);
        Quentity<LengthUnit> l2Inches = new Quentity<LengthUnit>(12.0, LengthUnit.INCHES);
        Quentity<LengthUnit> addedLength= QuantityMeasurementApp.demonstrateLengthAddition(l1Feet,l2Inches);
        Quentity<LengthUnit> expectedLength = new Quentity<LengthUnit>(36.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(addedLength,expectedLength));
    }

    //Test case to add 2 Quentity<LengthUnit> values and convert it into target unit UC7
    @Test
    public void addFeetAndInchesWithTargetUnitInches(){
        Quentity<LengthUnit> l1Feet = new Quentity<LengthUnit>(1.0, LengthUnit.FEET);
        Quentity<LengthUnit> l2Inches = new Quentity<LengthUnit>(12.0, LengthUnit.INCHES);
        Quentity<LengthUnit> addedLength= QuantityMeasurementApp.demonstrateLengthAddition(l1Feet,l2Inches, LengthUnit.INCHES);
        Quentity<LengthUnit> expectedLength = new Quentity<LengthUnit>(24.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEqulity(addedLength,expectedLength));
        assertEquals(addedLength,expectedLength);
    }
}