package org.eg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                first.add(second);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(
                QuantityMeasurementApp.LengthUnit.FEET,
                result.getUnit()
        );
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                first.add(second);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                feet.add(inches);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(
                QuantityMeasurementApp.LengthUnit.FEET,
                result.getUnit()
        );
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                inches.add(feet);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(
                QuantityMeasurementApp.LengthUnit.INCHES,
                result.getUnit()
        );
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                yard.add(feet);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {

        QuantityMeasurementApp.QuantityLength cm =
                new QuantityMeasurementApp.QuantityLength(
                        2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                cm.add(inch);

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result1 =
                feet.add(inches);

        QuantityMeasurementApp.QuantityLength result2 =
                QuantityMeasurementApp.QuantityLength.add(
                        inches,
                        feet,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(
                result1.getValue(),
                result2.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp.QuantityLength(
                        0.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                feet.add(zero);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(
                        -2.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                first.add(second);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> first.add(null)
        );
    }

    @Test
    void testAddition_LargeValues() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        1e6,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(
                        1e6,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                first.add(second);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {

        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(
                        0.001,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(
                        0.002,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                first.add(second);

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}