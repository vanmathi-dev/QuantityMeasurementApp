package org.eg;
// QuantityMeasurementApp.java

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // =========================
        // UC1 / UC2 / UC3 / UC4
        // Equality
        // =========================

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(
                feet.equals(inches)
        );

        // =========================
        // UC5
        // Conversion
        // =========================

        double converted =
                QuantityLength.convert(
                        1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        System.out.println(
                "1 FEET = " + converted + " INCHES"
        );

        // =========================
        // UC6
        // Addition
        // Result in first operand unit
        // =========================

        QuantityLength result1 =
                feet.add(inches);

        System.out.println(result1);

        // =========================
        // UC7
        // Addition with target unit
        // =========================

        QuantityLength result2 =
                feet.add(
                        inches,
                        LengthUnit.YARDS
                );

        System.out.println(result2);

        // =========================
        // UC8
        // Standalone enum conversion
        // =========================

        double baseValue =
                LengthUnit.INCHES.convertToBaseUnit(12);

        System.out.println(
                "12 INCHES in FEET = " + baseValue
        );
    }
}