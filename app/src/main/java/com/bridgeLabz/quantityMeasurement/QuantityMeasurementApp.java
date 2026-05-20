package com.bridgeLabz.quantityMeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        demonstrateTemperatureEquality();

        demonstrateTemperatureConversion();

        demonstrateUnsupportedTemperatureOperations();
    }

    private static void demonstrateTemperatureEquality() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println(
                "0°C equals 32°F : "
                        + celsius.equals(fahrenheit)
        );
    }

    private static void demonstrateTemperatureConversion() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        System.out.println(
                "100°C in Fahrenheit : "
                        + fahrenheit
        );
    }

    private static void demonstrateUnsupportedTemperatureOperations() {

        try {

            Quantity<TemperatureUnit> t1 =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> t2 =
                    new Quantity<>(50.0, TemperatureUnit.CELSIUS);

            t1.add(t2);

        } catch (UnsupportedOperationException ex) {

            System.out.println(ex.getMessage());
        }
    }
}