package com.bridgeLabz.quantityMeasurement;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
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

    // ================= CONVERSION =================

    public Quantity<U> convertTo(U targetUnit) {

        validateTargetUnit(targetUnit);

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        convertedValue =
                roundToTwoDecimalPlaces(convertedValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // ================= ADDITION =================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other,
                           U targetUnit) {

        validateQuantity(other);
        validateTargetUnit(targetUnit);

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double totalBase = thisBase + otherBase;

        double converted =
                targetUnit.convertFromBaseUnit(totalBase);

        converted =
                roundToTwoDecimalPlaces(converted);

        return new Quantity<>(converted, targetUnit);
    }

    // ================= SUBTRACTION =================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other,
                                U targetUnit) {

        validateQuantity(other);
        validateTargetUnit(targetUnit);

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double resultBase = thisBase - otherBase;

        double convertedResult =
                targetUnit.convertFromBaseUnit(resultBase);

        convertedResult =
                roundToTwoDecimalPlaces(convertedResult);

        return new Quantity<>(convertedResult, targetUnit);
    }

    // ================= DIVISION =================

    public double divide(Quantity<U> other) {

        validateQuantity(other);

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        if (Double.compare(otherBase, 0.0) == 0) {
            throw new ArithmeticException(
                    "Cannot divide by zero quantity"
            );
        }

        return thisBase / otherBase;
    }

    // ================= VALIDATIONS =================

    private void validateQuantity(Quantity<U> other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException(
                    "Cross-category operations are not allowed"
            );
        }
    }

    private void validateTargetUnit(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }
    }

    // ================= UTILITY =================

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // ================= EQUALS =================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> that)) {
            return false;
        }

        if (this.unit.getClass() != that.unit.getClass()) {
            return false;
        }

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double thatBase =
                that.unit.convertToBaseUnit(that.value);

        return Double.compare(thisBase, thatBase) == 0;
    }

    @Override
    public int hashCode() {

        double baseValue =
                unit.convertToBaseUnit(value);

        return Objects.hash(baseValue, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", "
                + unit.getUnitName() + ")";
    }
}