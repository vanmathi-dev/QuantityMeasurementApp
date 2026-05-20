package com.bridgeLabz.quantityMeasurement;

public enum VolumeUnit implements IMeasurable {

    LITRE(1000.0),
    MILLILITRE(1.0),
    GALLON(3785.41);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}