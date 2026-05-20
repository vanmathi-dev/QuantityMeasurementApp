package com.bridgeLabz.quantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =========================================================
    // UC9 - WEIGHT EQUALITY TEST CASES
    // =========================================================

    @Test
    void testEquality_KilogramToKilogram_SameValue() {

        QuantityWeight weight1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertEquals(weight1, weight2);
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {

        QuantityWeight weight1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(2.0,
                        WeightUnit.KILOGRAM);

        assertNotEquals(weight1, weight2);
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertEquals(gram, kilogram);
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        assertEquals(kilogram, pound);
    }

    @Test
    void testEquality_GramToPound_EquivalentValue() {

        QuantityWeight gram =
                new QuantityWeight(453.592,
                        WeightUnit.GRAM);

        QuantityWeight pound =
                new QuantityWeight(1.0,
                        WeightUnit.POUND);

        assertEquals(gram, pound);
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {

        QuantityWeight weight =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityLength length =
                new QuantityLength(1.0,
                        LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    @Test
    void testEquality_NullComparison() {

        QuantityWeight weight =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertNotEquals(null, weight);
    }

    @Test
    void testEquality_SameReference() {

        QuantityWeight weight =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertEquals(weight, weight);
    }

    @Test
    void testEquality_ZeroValue() {

        QuantityWeight kilogram =
                new QuantityWeight(0.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(0.0,
                        WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_NegativeWeight() {

        QuantityWeight kilogram =
                new QuantityWeight(-1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(-1000.0,
                        WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    // =========================================================
    // UC9 - CONVERSION TEST CASES
    // =========================================================

    @Test
    void testConversion_KilogramToGram() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_GramToKilogram() {

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_PoundToKilogram() {

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        QuantityWeight result =
                pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0,
                result.getValue(),
                0.001);
    }

    @Test
    void testConversion_KilogramToPound() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.POUND);

        assertEquals(2.20462,
                result.getValue(),
                0.001);
    }

    @Test
    void testConversion_GramToPound() {

        QuantityWeight gram =
                new QuantityWeight(500.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                gram.convertTo(WeightUnit.POUND);

        assertEquals(1.10231,
                result.getValue(),
                0.001);
    }

    @Test
    void testConversion_SameUnit() {

        QuantityWeight kilogram =
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(
                        WeightUnit.KILOGRAM);

        assertEquals(5.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {

        QuantityWeight kilogram =
                new QuantityWeight(0.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(0.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {

        QuantityWeight kilogram =
                new QuantityWeight(-1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {

        QuantityWeight kilogram =
                new QuantityWeight(1.5,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5,
                result.getValue(),
                EPSILON);
    }

    // =========================================================
    // UC9 - ADDITION TEST CASES
    // =========================================================

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {

        QuantityWeight weight1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(2.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                weight1.add(weight2);

        assertEquals(
                new QuantityWeight(3.0,
                        WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(gram);

        assertEquals(
                new QuantityWeight(2.0,
                        WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                pound.add(kilogram);

        assertEquals(4.40924,
                result.getValue(),
                0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(gram,
                        WeightUnit.GRAM);

        assertEquals(
                new QuantityWeight(2000.0,
                        WeightUnit.GRAM),
                result
        );
    }

    @Test
    void testAddition_Commutativity() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result1 =
                kilogram.add(gram,
                        WeightUnit.KILOGRAM);

        QuantityWeight result2 =
                gram.add(kilogram,
                        WeightUnit.KILOGRAM);

        assertEquals(result1, result2);
    }

    @Test
    void testAddition_WithZero() {

        QuantityWeight kilogram =
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(0.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(gram);

        assertEquals(
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityWeight weight1 =
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(-2000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                weight1.add(weight2);

        assertEquals(
                new QuantityWeight(3.0,
                        WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testAddition_LargeValues() {

        QuantityWeight weight1 =
                new QuantityWeight(1e6,
                        WeightUnit.KILOGRAM);

        QuantityWeight weight2 =
                new QuantityWeight(1e6,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                weight1.add(weight2);

        assertEquals(
                new QuantityWeight(2e6,
                        WeightUnit.KILOGRAM),
                result
        );
    }

    // =========================================================
    // UC9 - VALIDATION TEST CASES
    // =========================================================

    @Test
    void testConstructor_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(
                        1.0,
                        null)
        );
    }

    @Test
    void testConstructor_InvalidNaNValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(
                        Double.NaN,
                        WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testConstructor_InfiniteValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(
                        Double.POSITIVE_INFINITY,
                        WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testConvertTo_NullTargetUnit() {

        QuantityWeight weight =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> weight.convertTo(null)
        );
    }

    @Test
    void testAdd_NullWeight() {

        QuantityWeight weight =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> weight.add(null)
        );
    }
}