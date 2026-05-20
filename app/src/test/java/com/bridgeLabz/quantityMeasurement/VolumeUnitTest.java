package com.bridgeLabz.quantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VolumeUnitTest {

    private static final double EPSILON = 0.01;

    // ================= EQUALITY =================

    @Test
    void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        assertEquals(litre, milli);
    }

    @Test
    void testEquality_GallonToLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541,
                        VolumeUnit.LITRE);

        assertEquals(gallon, litre);
    }

    @Test
    void testEquality_VolumeVsLength() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertNotEquals(volume, length);
    }

    @Test
    void testEquality_VolumeVsWeight() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(volume, weight);
    }

    // ================= CONVERSION =================

    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                gallon.convertTo(VolumeUnit.LITRE);

        assertEquals(
                3.78541,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testConversion_LitreToGallon() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541,
                        VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.GALLON);

        assertEquals(
                1.0,
                result.getValue(),
                EPSILON
        );
    }

    // ================= ADDITION =================

    @Test
    void testAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(milli);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(milli,
                        VolumeUnit.MILLILITRE);

        assertEquals(
                2000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_GallonPlusLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541,
                        VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                gallon.add(litre,
                        VolumeUnit.GALLON);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    // ================= ENUM TESTS =================

    @Test
    void testVolumeUnit_LitreFactor() {

        assertEquals(
                1.0,
                VolumeUnit.LITRE.getConversionFactor()
        );
    }

    @Test
    void testVolumeUnit_MillilitreFactor() {

        assertEquals(
                0.001,
                VolumeUnit.MILLILITRE.getConversionFactor()
        );
    }

    @Test
    void testVolumeUnit_GallonFactor() {

        assertEquals(
                3.78541,
                VolumeUnit.GALLON.getConversionFactor(),
                EPSILON
        );
    }

    // ================= EDGE CASES =================

    @Test
    void testZeroValueConversion() {

        Quantity<VolumeUnit> zero =
                new Quantity<>(0.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                zero.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                0.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    void testNegativeVolume() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(-1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(-1000.0,
                        VolumeUnit.MILLILITRE);

        assertEquals(litre, milli);
    }

    @Test
    void testLargeVolumeValue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1000.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milli =
                new Quantity<>(1_000_000.0,
                        VolumeUnit.MILLILITRE);

        assertEquals(litre, milli);
    }

    // ================= BACKWARD COMPATIBILITY =================

    @Test
    void testGenericQuantity_VolumeSupport() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertNotNull(volume);
    }

    @Test
    void testScalability_VolumeIntegratedWithoutChanges() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }
}