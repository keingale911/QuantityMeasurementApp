package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantitymeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
        Feet f1=new Feet(1.5);
        Feet f2=new Feet(1.5);

        assertEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_DiffValue(){
        Feet f1=new Feet(1.5);
        Feet f2=new Feet(2.5);

        assertNotEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_NullValue(){
        Feet f1=new Feet(1.5);

        assertNotEquals(f1,null);
    }

    @Test
    public void testFeetEquality_DiffObjValue(){
        Feet f1=new Feet(1.5);

        assertNotEquals(f1, new Inches());
    }

    @Test
    public void testFeetEquality_SameRefValue(){
        Feet f1=new Feet(1.5);

        assertEquals(f1,f1);
    }
}
