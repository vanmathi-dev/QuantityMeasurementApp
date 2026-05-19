package org.eg;

/**
 * Quantity Measurement Application
 * Supports length comparison and unit conversion.
 */
public class QuantityMeasurementApp {

    /**
     * Enum representing supported length units.
     * Conversion factors are relative to FEET (base unit).
     */
    enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Immutable Value Object representing a Length Quantity.
     */
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        /**
         * Constructor
         */
        public QuantityLength(double value, LengthUnit unit) {

            validateValue(value);

            if (unit == null) {
                throw new IllegalArgumentException(
                        "Unit cannot be null"
                );
            }

            this.value = value;
            this.unit = unit;
        }

        /**
         * Converts current quantity to target unit.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {

            double convertedValue =
                    convert(this.value, this.unit, targetUnit);

            return new QuantityLength(convertedValue, targetUnit);
        }

        /**
         * Static conversion API
         */
        public static double convert(
                double value,
                LengthUnit sourceUnit,
                LengthUnit targetUnit
        ) {

            validateValue(value);

            if (sourceUnit == null || targetUnit == null) {
                throw new IllegalArgumentException(
                        "Source and Target units cannot be null"
                );
            }

            double valueInFeet =
                    value * sourceUnit.getConversionFactor();

            return valueInFeet / targetUnit.getConversionFactor();
        }

        /**
         * Helper method for validation
         */
        private static void validateValue(double value) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be a finite number"
                );
            }
        }

        /**
         * Convert object value to feet.
         */
        private double toFeet() {
            return value * unit.getConversionFactor();
        }

        /**
         * Equality based on converted values.
         */
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.toFeet(),
                    other.toFeet()
            ) == 0;
        }

        /**
         * Human-readable representation
         */
        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }
    }

    /**
     * Demonstrates conversion using raw values.
     */
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit
    ) {

        double result =
                QuantityLength.convert(
                        value,
                        fromUnit,
                        toUnit
                );

        System.out.println(
                "Input: convert("
                        + value + ", "
                        + fromUnit + ", "
                        + toUnit + ")"
        );

        System.out.println("Output: " + result);
        System.out.println();
    }

    /**
     * Overloaded method using QuantityLength object.
     */
    public static void demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit
    ) {

        QuantityLength converted =
                quantity.convertTo(targetUnit);

        System.out.println(
                "Input: " + quantity
        );

        System.out.println(
                "Converted To: "
                        + converted
        );

        System.out.println();
    }

    /**
     * Demonstrates equality.
     */
    public static void demonstrateLengthEquality(
            QuantityLength q1,
            QuantityLength q2
    ) {

        System.out.println("Input: " + q1 + " and " + q2);

        System.out.println(
                "Output: Equal (" + q1.equals(q2) + ")"
        );

        System.out.println();
    }

    /**
     * Main Method
     */
    public static void main(String[] args) {

        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                36.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                );

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );

        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET
                );

        QuantityLength inches =
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                );

        demonstrateLengthEquality(yard, feet);
        demonstrateLengthEquality(feet, inches);
    }
}