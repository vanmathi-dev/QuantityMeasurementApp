package com.bridgeLabz.quantityMeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.01;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value.");
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

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(
                roundToTwoDecimals(convertedValue),
                targetUnit
        );
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit);

        double baseResult = performBaseArithmetic(
                other,
                ArithmeticOperation.ADD
        );

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit
        );
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit);

        double baseResult = performBaseArithmetic(
                other,
                ArithmeticOperation.SUBTRACT
        );

        double convertedResult =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(convertedResult),
                targetUnit
        );
    }

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, this.unit);

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Other quantity cannot be null."
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null."
            );
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Cross-category arithmetic is not allowed."
            );
        }

        if (!unit.supportsArithmetic()) {
            unit.validateOperationSupport("arithmetic");
        }

        if (Double.isNaN(other.value)
                || Double.isInfinite(other.value)) {

            throw new IllegalArgumentException(
                    "Invalid numeric value."
            );
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return operation.compute(thisBase, otherBase);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                roundToTwoDecimals(
                        unit.convertToBaseUnit(value)
                )
        );
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}