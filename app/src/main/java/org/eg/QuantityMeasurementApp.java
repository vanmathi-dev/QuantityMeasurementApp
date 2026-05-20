package org.eg;
// QuantityMeasurementApp.java

/**
 * Quantity Measurement Application
 * UC7 - Addition with Explicit Target Unit
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
     * Immutable Value Object representing Length
     */
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 0.0001;

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
         * Convert current object to target unit
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
                        "Units cannot be null"
                );
            }

            // Convert source to feet
            double valueInFeet =
                    value * sourceUnit.getConversionFactor();

            // Convert feet to target
            return valueInFeet /
                    targetUnit.getConversionFactor();
        }

        /**
         * UC6 Method
         * Add and return in first operand unit
         */
        public QuantityLength add(QuantityLength other) {

            return add(other, this.unit);
        }

        /**
         * UC7 Method
         * Add and return in explicitly specified target unit
         */
        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit
        ) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Other length cannot be null"
                );
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            return addInternal(this, other, targetUnit);
        }

        /**
         * Static overloaded add method
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

            return addInternal(first, second, targetUnit);
        }

        /**
         * Private utility method for addition
         * Converts both values to feet,
         * adds them,
         * converts to target unit.
         */
        private static QuantityLength addInternal(
                QuantityLength first,
                QuantityLength second,
                LengthUnit targetUnit
        ) {

            double firstInFeet = first.toFeet();
            double secondInFeet = second.toFeet();

            double sumInFeet =
                    firstInFeet + secondInFeet;

            double result =
                    sumInFeet /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    result,
                    targetUnit
            );
        }

        /**
         * Convert current value to feet
         */
        private double toFeet() {

            return value *
                    unit.getConversionFactor();
        }

        /**
         * Validate numeric value
         */
        private static void validateValue(double value) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite"
                );
            }
        }

        /**
         * Equality comparison
         */
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null ||
                    getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other =
                    (QuantityLength) obj;

            return Math.abs(
                    this.toFeet() - other.toFeet()
            ) < EPSILON;
        }

        /**
         * Human-readable output
         */
        @Override
        public String toString() {

            return "Quantity("
                    + value
                    + ", "
                    + unit
                    + ")";
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }
    }

    /**
     * Demonstrates Addition with explicit target unit
     */
    public static void demonstrateAddition(
            QuantityLength first,
            QuantityLength second,
            LengthUnit targetUnit
    ) {

        QuantityLength result =
                QuantityLength.add(
                        first,
                        second,
                        targetUnit
                );

        System.out.println(
                "Input: add("
                        + first + ", "
                        + second + ", "
                        + targetUnit + ")"
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
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.FEET
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.INCHES
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.YARDS
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                ),
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET
                ),
                LengthUnit.YARDS
        );

        demonstrateAddition(
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                ),
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                ),
                LengthUnit.FEET
        );

        demonstrateAddition(
                new QuantityLength(
                        2.54,
                        LengthUnit.CENTIMETERS
                ),
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.CENTIMETERS
        );

        demonstrateAddition(
                new QuantityLength(
                        5.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        0.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.YARDS
        );

        demonstrateAddition(
                new QuantityLength(
                        5.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        -2.0,
                        LengthUnit.FEET
                ),
                LengthUnit.INCHES
        );
    }
}