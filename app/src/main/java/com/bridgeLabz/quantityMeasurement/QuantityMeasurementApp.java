package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1,
                             Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2 +
                " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(Quantity<U> quantity,
                               U targetUnit) {

        System.out.println(quantity + " converted to "
                + targetUnit.getUnitName()
                + " -> "
                + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(Quantity<U> q1,
                             Quantity<U> q2,
                             U targetUnit) {

        System.out.println(q1 + " + " + q2 +
                " -> " + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

        System.out.println("===== LENGTH OPERATIONS =====");

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

        System.out.println();

        System.out.println("===== WEIGHT OPERATIONS =====");

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

        System.out.println();

        System.out.println("===== CROSS CATEGORY CHECK =====");

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println(
                length.equals(weight)
        );
    }
}