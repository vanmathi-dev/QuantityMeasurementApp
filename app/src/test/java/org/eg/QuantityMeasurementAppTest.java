package org.eg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // =========================
    // UC1 / UC2 / UC3 / UC4
    // Equality Tests
    // =========================

    @Test
    void testEquality_FeetToFeet_SameValue() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_FeetToInches_EquivalentValue() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    @Test
    void testEquality_YardsToFeet_EquivalentValue() {

        QuantityLength yards =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(yards, feet);
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {

        QuantityLength cm =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(1.0, LengthUnit.INCHES);

        assertEquals(cm, inch);
    }

    @Test
    void testEquality_DifferentValue() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertNotEquals(q1, q2);
    }

    @Test
    void testEquality_NullComparison() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(q1, null);
    }

    // =========================
    // UC5 Conversion Tests
    // =========================

    @Test
    void testConversion_FeetToInches() {

        double result =
                QuantityLength.convert(
                        1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {

        double result =
                QuantityLength.convert(
                        24.0,
                        LengthUnit.INCHES,
                        LengthUnit.FEET
                );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {

        double result =
                QuantityLength.convert(
                        1.0,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES
                );

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {

        double result =
                QuantityLength.convert(
                        2.54,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES
                );

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        1.0,
                        null,
                        LengthUnit.FEET
                )
        );
    }

    // =========================
    // UC6 Addition Tests
    // =========================

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                feet.add(inches);

        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength q1 =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2);

        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                result
        );
    }

    // =========================
    // UC7 Explicit Target Unit
    // =========================

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                feet.add(inches, LengthUnit.YARDS);

        assertEquals(
                0.6667,
                result.getValue(),
                0.01
        );

        assertEquals(
                LengthUnit.YARDS,
                result.getUnit()
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                feet.add(inches, LengthUnit.INCHES);

        assertEquals(
                24.0,
                result.getValue(),
                EPSILON
        );
    }

    // =========================
    // UC8 Enum Conversion Tests
    // =========================

    @Test
    void testConvertToBaseUnit_InchesToFeet() {

        double result =
                LengthUnit.INCHES
                        .convertToBaseUnit(12);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {

        double result =
                LengthUnit.INCHES
                        .convertFromBaseUnit(1);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {

        double result =
                LengthUnit.YARDS
                        .convertToBaseUnit(1);

        assertEquals(3.0, result, EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {

        double result =
                LengthUnit.CENTIMETERS
                        .convertFromBaseUnit(1);

        assertEquals(30.48, result, EPSILON);
    }

    @Test
    void testQuantityLength_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testQuantityLength_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        1.0,
                        null
                )
        );
    }
}