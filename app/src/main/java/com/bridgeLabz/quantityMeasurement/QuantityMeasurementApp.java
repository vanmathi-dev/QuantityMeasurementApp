package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        demonstrateAddition();

        demonstrateSubtraction();

        demonstrateDivision();
    }

    private static void demonstrateAddition() {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(
                length1.add(length2));
    }

    private static void demonstrateSubtraction() {

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println(
                length1.subtract(length2));
    }

    private static void demonstrateDivision() {

        Quantity<LengthUnit> length1 =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> length2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println(
                length1.divide(length2));
    }
}