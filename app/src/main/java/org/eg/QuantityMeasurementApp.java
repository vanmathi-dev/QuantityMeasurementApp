package org.eg;


public class QuantityMeasurementApp {

    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }
        public double toFeet() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit + "\")";
        }
    }

    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCH);

        boolean result1 = feet.equals(inches);

        System.out.println("Input: " + feet + " and " + inches);
        System.out.println("Output: Equal (" + result1 + ")");

        QuantityLength inch1 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength inch2 =
                new QuantityLength(1.0, LengthUnit.INCH);

        boolean result2 = inch1.equals(inch2);

        System.out.println("\nInput: " + inch1 + " and " + inch2);
        System.out.println("Output: Equal (" + result2 + ")");
    }
}