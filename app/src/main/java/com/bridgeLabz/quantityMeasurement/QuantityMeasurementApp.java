package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        // Equality
        System.out.println(
                kilogram.equals(gram)
        );

        // Conversion
        System.out.println(
                kilogram.convertTo(WeightUnit.GRAM)
        );

        // Addition
        System.out.println(
                kilogram.add(gram)
        );

        // Addition with target unit
        System.out.println(
                kilogram.add(gram,
                        WeightUnit.POUND)
        );

        // Pound to kilogram conversion
        System.out.println(
                pound.convertTo(WeightUnit.KILOGRAM)
        );
    }
}