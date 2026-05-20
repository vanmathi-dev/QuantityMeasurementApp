package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateSubtraction(Quantity<U> q1,
                                Quantity<U> q2,
                                U targetUnit) {

        System.out.println(
                q1 + " - " + q2
                        + " = "
                        + q1.subtract(q2, targetUnit)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateDivision(Quantity<U> q1,
                             Quantity<U> q2) {

        System.out.println(
                q1 + " / " + q2
                        + " = "
                        + q1.divide(q2)
        );
    }

    public static void main(String[] args) {

        // ================= LENGTH =================

        Quantity<LengthUnit> tenFeet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> sixInches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(
                tenFeet,
                sixInches,
                LengthUnit.FEET
        );

        demonstrateSubtraction(
                tenFeet,
                sixInches,
                LengthUnit.INCHES
        );

        demonstrateDivision(
                new Quantity<>(24.0,
                        LengthUnit.INCHES),

                new Quantity<>(2.0,
                        LengthUnit.FEET)
        );

        // ================= WEIGHT =================

        Quantity<WeightUnit> tenKg =
                new Quantity<>(10.0,
                        WeightUnit.KILOGRAM);

        Quantity<WeightUnit> fiveKg =
                new Quantity<>(5000.0,
                        WeightUnit.GRAM);

        demonstrateSubtraction(
                tenKg,
                fiveKg,
                WeightUnit.KILOGRAM
        );

        demonstrateDivision(
                tenKg,
                fiveKg
        );

        // ================= VOLUME =================

        Quantity<VolumeUnit> fiveLitre =
                new Quantity<>(5.0,
                        VolumeUnit.LITRE);

        Quantity<VolumeUnit> halfLitre =
                new Quantity<>(500.0,
                        VolumeUnit.MILLILITRE);

        demonstrateSubtraction(
                fiveLitre,
                halfLitre,
                VolumeUnit.LITRE
        );

        demonstrateDivision(
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE),

                new Quantity<>(1.0,
                        VolumeUnit.LITRE)
        );
    }
}