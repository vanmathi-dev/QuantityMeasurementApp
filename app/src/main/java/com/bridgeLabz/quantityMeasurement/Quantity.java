package com.bridgeLabz.quantityMeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        validateFinite(value);

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /*
     * =========================================================
     *                  ADD OPERATIONS
     * =========================================================
     */

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit);
    }

    /*
     * =========================================================
     *               SUBTRACTION OPERATIONS
     * =========================================================
     */

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit);
    }

    /*
     * =========================================================
     *                 DIVISION OPERATION
     * =========================================================
     */

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE);
    }

    /*
     * =========================================================
     *             CENTRALIZED VALIDATION HELPER
     * =========================================================
     */

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean validateTargetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (other.unit == null) {
            throw new IllegalArgumentException("Other unit cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Cannot perform arithmetic on different measurement categories");
        }

        validateFinite(this.value);
        validateFinite(other.value);

        if (validateTargetUnit && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }
    }

    /*
     * =========================================================
     *             CENTRALIZED ARITHMETIC HELPER
     * =========================================================
     */

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double thisBaseValue =
                unit.convertToBaseUnit(this.value);

        double otherBaseValue =
                other.unit.convertToBaseUnit(other.value);

        return operation.compute(
                thisBaseValue,
                otherBaseValue);
    }

    /*
     * =========================================================
     *                    ROUNDING HELPER
     * =========================================================
     */

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /*
     * =========================================================
     *                 FINITE VALUE VALIDATION
     * =========================================================
     */

    private void validateFinite(double value) {

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite");
        }
    }

    /*
     * =========================================================
     *                ARITHMETIC OPERATION ENUM
     * =========================================================
     */

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (b == 0) {
                throw new ArithmeticException(
                        "Division by zero is not allowed");
            }

            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    /*
     * =========================================================
     *                 EQUALS & HASHCODE
     * =========================================================
     */

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> otherQuantity)) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                otherQuantity.unit()
                        .convertToBaseUnit(otherQuantity.value());

        return Math.abs(thisBase - otherBase) < 0.0001;
    }

    public U unit() {
        return unit;
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}