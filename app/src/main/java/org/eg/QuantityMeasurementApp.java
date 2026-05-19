package org.eg;

/**
 * Quantity Measurement Application
 * Supports:
 * - Equality comparison
 * - Unit conversion
 * - Addition of length measurements
 */
public class QuantityMeasurementApp {

    /**
     * Enum representing supported length units.
     * Conversion factors are relative to FEET.
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
     * Immutable Value Object for Length Measurement
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
         * Convert to target unit
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {

            double convertedValue =
                    convert(this.value, this.unit, targetUnit);

            return new QuantityLength(
                    convertedValue,
                    targetUnit
            );
        }

        /**
         * Static conversion method
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

            // Convert to feet
            double valueInFeet =
                    value * sourceUnit.getConversionFactor();

            // Convert to target unit
            return valueInFeet /
                    targetUnit.getConversionFactor();
        }

        /**
         * Add another QuantityLength.
         * Result will be in current object's unit.
         */
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null"
                );
            }

            // Convert both to feet
            double firstInFeet = this.toFeet();
            double secondInFeet = other.toFeet();

            // Add
            double sumInFeet = firstInFeet + secondInFeet;

            // Convert back to current unit
            double result =
                    sumInFeet / this.unit.getConversionFactor();

            return new QuantityLength(result, this.unit);
        }

        /**
         * Overloaded static add method
         */
        public static QuantityLength add(
                QuantityLength first,
                QuantityLength second,
                LengthUnit targetUnit
        ) {

            if (first == null || second == null) {
                throw new IllegalArgumentException(
                        "Operands cannot be null"
                );
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            double firstInFeet = first.toFeet();
            double secondInFeet = second.toFeet();

            double sumInFeet = firstInFeet + secondInFeet;

            double result =
                    sumInFeet /
                            targetUnit.getConversionFactor();

            return new QuantityLength(result, targetUnit);
        }

        /**
         * Helper method for validation
         */
        private static void validateValue(double value) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite"
                );
            }
        }

        /**
         * Convert current value to feet
         */
        private double toFeet() {
            return value * unit.getConversionFactor();
        }

        /**
         * Equality comparison
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
            return "Quantity("
                    + value + ", "
                    + unit + ")";
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }
    }

    /**
     * Demonstrates addition
     */
    public static void demonstrateAddition(
            QuantityLength first,
            QuantityLength second
    ) {

        QuantityLength result = first.add(second);

        System.out.println(
                "Input: add("
                        + first + ", "
                        + second + ")"
        );

        System.out.println(
                "Output: " + result
        );

        System.out.println();
    }

    /**
     * Main Method
     */
    public static void main(String[] args) {

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET)
        );

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );

        demonstrateAddition(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET)
        );

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );

        demonstrateAddition(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS)
        );

        demonstrateAddition(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES)
        );

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES)
        );

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET)
        );
    }
}