package org.eg;

// QuantityLength.java

import java.util.Objects;

/**
 * QuantityLength represents an immutable length measurement.
 * Supports:
 * - Equality comparison
 * - Unit conversion
 * - Addition
 */
public class QuantityLength {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        validateValue(value);

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Convert current quantity to target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Static conversion utility method
     */
    public static double convert(double value,
                                 LengthUnit sourceUnit,
                                 LengthUnit targetUnit) {

        validateValue(value);

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue =
                sourceUnit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * Add two lengths.
     * Result unit = first operand unit
     */
    public QuantityLength add(QuantityLength other) {

        return add(other, this.unit);
    }

    /**
     * Add two lengths with explicit target unit
     */
    public QuantityLength add(QuantityLength other,
                              LengthUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double sumBase = thisBase + otherBase;

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    /**
     * Equality based on physical measurement
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QuantityLength)) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {

        double normalized =
                unit.convertToBaseUnit(value);

        return Objects.hash(
                Math.round(normalized / EPSILON)
        );
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    /**
     * Validate finite numeric values
     */
    private static void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }
    }
}