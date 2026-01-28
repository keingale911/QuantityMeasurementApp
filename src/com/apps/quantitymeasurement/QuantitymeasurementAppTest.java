package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import org.junit.jupiter.api.Test;

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
}
