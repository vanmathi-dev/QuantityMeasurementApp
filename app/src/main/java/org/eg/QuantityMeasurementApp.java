package org.eg;

public class QuantityMeasurementApp {
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
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: " + yard + " and " + feet);
        System.out.println("Output: Equal (" + yard.equals(feet) + ")");

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        System.out.println("\nInput: " + yard + " and " + inches);
        System.out.println("Output: Equal (" + yard.equals(inches) + ")");

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println("\nInput: " + cm + " and " + inch);
        System.out.println("Output: Equal (" + cm.equals(inch) + ")");
    }
}