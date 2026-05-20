package org.eg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(
                QuantityMeasurementApp.LengthUnit.FEET,
                result.getUnit()
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.666666,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                2.0,
                                QuantityMeasurementApp.LengthUnit.YARDS
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                3.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                2.0,
                                QuantityMeasurementApp.LengthUnit.YARDS
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                3.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(9.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityMeasurementApp.QuantityLength result1 =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength result2 =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                result1.getValue(),
                result2.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                5.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                0.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                1.666666,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                5.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                -2.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        null
                )
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1000.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                500.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                18000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.666666,
                result.getValue(),
                EPSILON
        );
    }
}