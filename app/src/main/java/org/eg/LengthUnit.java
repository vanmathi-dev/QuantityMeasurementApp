package org.eg;

// LengthUnit.java

/**
 * Standalone enum responsible for all length unit conversions.
 * Base Unit = FEET
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactorToFeet;

    LengthUnit(double conversionFactorToFeet) {
        this.conversionFactorToFeet = conversionFactorToFeet;
    }

    public double getConversionFactor() {
        return conversionFactorToFeet;
    }

    /**
     * Converts value from current unit to base unit (FEET)
     */
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToFeet;
    }

    /**
     * Converts value from base unit (FEET) to current unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToFeet;
    }
}
