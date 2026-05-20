package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1,
                             Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2
                + " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(Quantity<U> quantity,
                               U targetUnit) {

        System.out.println(quantity
                + " converted to "
                + targetUnit.getUnitName()
                + " -> "
                + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(Quantity<U> q1,
                             Quantity<U> q2,
                             U targetUnit) {

        System.out.println(q1 + " + " + q2
                + " -> "
                + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

        // ================= LENGTH =================

        System.out.println("===== LENGTH =====");

        Quantity<LengthUnit> oneFoot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> twelveInches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(oneFoot, twelveInches);

        demonstrateConversion(oneFoot,
                LengthUnit.INCHES);

        demonstrateAddition(oneFoot,
                twelveInches,
                LengthUnit.FEET);

        // ================= WEIGHT =================

        System.out.println("\n===== WEIGHT =====");

        Quantity<WeightUnit> oneKg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> thousandGram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(oneKg, thousandGram);

        demonstrateConversion(oneKg,
                WeightUnit.GRAM);

        demonstrateAddition(oneKg,
                thousandGram,
                WeightUnit.KILOGRAM);

        // ================= VOLUME =================

        System.out.println("\n===== VOLUME =====");

        Quantity<VolumeUnit> oneLitre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> thousandMilli =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> oneGallon =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        // Equality
        demonstrateEquality(oneLitre,
                thousandMilli);

        // Conversion
        demonstrateConversion(oneLitre,
                VolumeUnit.MILLILITRE);

        demonstrateConversion(oneGallon,
                VolumeUnit.LITRE);

        // Addition
        demonstrateAddition(oneLitre,
                thousandMilli,
                VolumeUnit.LITRE);

        demonstrateAddition(oneLitre,
                oneGallon,
                VolumeUnit.MILLILITRE);

        // ================= CATEGORY CHECK =================

        System.out.println("\n===== CATEGORY CHECK =====");

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println(
                oneLitre.equals(length)
        );

        System.out.println(
                oneLitre.equals(weight)
        );
    }
}