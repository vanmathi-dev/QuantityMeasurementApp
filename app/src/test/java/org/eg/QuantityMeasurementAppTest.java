package org.eg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue() {

        assertTrue(
                QuantityMeasurementApp.checkFeetEquality(1.0, 1.0)
        );
    }

    @Test
    void testFeetEquality_DifferentValue() {

        assertFalse(
                QuantityMeasurementApp.checkFeetEquality(1.0, 2.0)
        );
    }

    @Test
    void testFeetEquality_SameReference() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet));
    }

    @Test
    void testFeetEquality_NullComparison() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null));
    }

    @Test
    void testFeetEquality_NonNumericInput() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        String input = "abc";

        assertFalse(feet.equals(input));
    }


    @Test
    void testInchesEquality_SameValue() {

        assertTrue(
                QuantityMeasurementApp.checkInchesEquality(1.0, 1.0)
        );
    }

    @Test
    void testInchesEquality_DifferentValue() {

        assertFalse(
                QuantityMeasurementApp.checkInchesEquality(1.0, 2.0)
        );
    }

    @Test
    void testInchesEquality_SameReference() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertTrue(inches.equals(inches));
    }

    @Test
    void testInchesEquality_NullComparison() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(inches.equals(null));
    }

    @Test
    void testInchesEquality_NonNumericInput() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        String input = "abc";

        assertFalse(inches.equals(input));
    }
}