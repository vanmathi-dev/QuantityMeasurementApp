package com.bridgeLabz.quantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    // ================= SUBTRACTION =================

    @Test
    void testSubtraction_SameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q1.subtract(q2);

        assertEquals(
                new Quantity<>(5.0,
                        LengthUnit.FEET),
                result
        );
    }

    @Test
    void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0,
                        LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.subtract(inches);

        assertEquals(
                9.5,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testSubtraction_ExplicitTargetUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0,
                        LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.subtract(inches,
                        LengthUnit.INCHES);

        assertEquals(
                114.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testSubtraction_NegativeResult() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q1.subtract(q2);

        assertEquals(
                -5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testSubtraction_ResultingInZero() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(120.0,
                        LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.subtract(inches);

        assertEquals(
                0.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testSubtraction_NullOperand() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.subtract(null)
        );
    }

    @Test
    void testSubtraction_NullTargetUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0,
                        LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.subtract(q2, null)
        );
    }

    // ================= DIVISION =================

    @Test
    void testDivision_SameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0,
                        LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testDivision_CrossUnit() {

        Quantity<LengthUnit> inches =
                new Quantity<>(24.0,
                        LengthUnit.INCHES);

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0,
                        LengthUnit.FEET);

        double result =
                inches.divide(feet);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testDivision_RatioLessThanOne() {

        Quantity<LengthUnit> small =
                new Quantity<>(5.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> large =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        double result =
                small.divide(large);

        assertEquals(0.5, result, EPSILON);
    }

    @Test
    void testDivision_ByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> zero =
                new Quantity<>(0.0,
                        LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> q1.divide(zero)
        );
    }

    @Test
    void testDivision_NullOperand() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.divide(null)
        );
    }

    // ================= CROSS CATEGORY =================

    @Test
    void testCrossCategory_Subtraction() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity rawWeight =
                new Quantity<>(5.0,
                        WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract(rawWeight)
        );
    }

    @Test
    void testCrossCategory_Division() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity rawWeight =
                new Quantity<>(5.0,
                        WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide(rawWeight)
        );
    }

    // ================= IMMUTABILITY =================

    @Test
    void testSubtraction_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> other =
                new Quantity<>(5.0,
                        LengthUnit.FEET);

        original.subtract(other);

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }

    @Test
    void testDivision_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> other =
                new Quantity<>(2.0,
                        LengthUnit.FEET);

        original.divide(other);

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }

    // ================= INTEGRATION =================

    @Test
    void testSubtractionAddition_Inverse() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> added =
                original.add(
                        new Quantity<>(5.0,
                                LengthUnit.FEET)
                );

        Quantity<LengthUnit> result =
                added.subtract(
                        new Quantity<>(5.0,
                                LengthUnit.FEET)
                );

        assertEquals(original, result);
    }

    @Test
    void testSubtraction_ChainedOperations() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.0,
                        LengthUnit.FEET)

                        .subtract(
                                new Quantity<>(2.0,
                                        LengthUnit.FEET)
                        )

                        .subtract(
                                new Quantity<>(1.0,
                                        LengthUnit.FEET)
                        );

        assertEquals(
                7.0,
                result.getValue(),
                EPSILON
        );
    }
}