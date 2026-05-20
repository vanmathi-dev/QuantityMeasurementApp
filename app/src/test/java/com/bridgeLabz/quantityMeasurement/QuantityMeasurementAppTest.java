package com.bridgeLabz.quantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

        @Test
        void testTemperatureEquality_CelsiusToFahrenheit() {

            Quantity<TemperatureUnit> celsius =
                    new Quantity<>(0.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> fahrenheit =
                    new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

            assertEquals(celsius, fahrenheit);
        }

        @Test
        void testTemperatureEquality_CelsiusToKelvin() {

            Quantity<TemperatureUnit> celsius =
                    new Quantity<>(0.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> kelvin =
                    new Quantity<>(273.15, TemperatureUnit.KELVIN);

            assertEquals(celsius, kelvin);
        }

        @Test
        void testTemperatureConversion_CelsiusToFahrenheit() {

            Quantity<TemperatureUnit> celsius =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> fahrenheit =
                    celsius.convertTo(TemperatureUnit.FAHRENHEIT);

            assertEquals(212.0, fahrenheit.getValue());
        }

        @Test
        void testTemperatureConversion_FahrenheitToCelsius() {

            Quantity<TemperatureUnit> fahrenheit =
                    new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

            Quantity<TemperatureUnit> celsius =
                    fahrenheit.convertTo(TemperatureUnit.CELSIUS);

            assertEquals(0.0, celsius.getValue());
        }

        @Test
        void testTemperatureConversion_CelsiusToKelvin() {

            Quantity<TemperatureUnit> celsius =
                    new Quantity<>(0.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> kelvin =
                    celsius.convertTo(TemperatureUnit.KELVIN);

            assertEquals(273.15, kelvin.getValue());
        }

        @Test
        void testTemperatureConversion_KelvinToCelsius() {

            Quantity<TemperatureUnit> kelvin =
                    new Quantity<>(273.15, TemperatureUnit.KELVIN);

            Quantity<TemperatureUnit> celsius =
                    kelvin.convertTo(TemperatureUnit.CELSIUS);

            assertEquals(0.0, celsius.getValue());
        }

        @Test
        void testTemperatureUnsupportedOperation_Add() {

            Quantity<TemperatureUnit> t1 =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> t2 =
                    new Quantity<>(50.0, TemperatureUnit.CELSIUS);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> t1.add(t2)
            );
        }

        @Test
        void testTemperatureUnsupportedOperation_Subtract() {

            Quantity<TemperatureUnit> t1 =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> t2 =
                    new Quantity<>(50.0, TemperatureUnit.CELSIUS);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> t1.subtract(t2)
            );
        }

        @Test
        void testTemperatureUnsupportedOperation_Divide() {

            Quantity<TemperatureUnit> t1 =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> t2 =
                    new Quantity<>(50.0, TemperatureUnit.CELSIUS);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> t1.divide(t2)
            );
        }

        @Test
        void testTemperatureVsLengthIncompatibility() {

            Quantity<TemperatureUnit> temperature =
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS);

            Quantity<LengthUnit> length =
                    new Quantity<>(100.0, LengthUnit.FEET);

            assertNotEquals(temperature, length);
        }

        @Test
        void testOperationSupportMethods_TemperatureUnit() {

            assertFalse(
                    TemperatureUnit.CELSIUS.supportsArithmetic()
            );
        }

        @Test
        void testOperationSupportMethods_LengthUnit() {

            assertTrue(
                    LengthUnit.FEET.supportsArithmetic()
            );
        }

        @Test
        void testAdditionStillWorksForLength() {

            Quantity<LengthUnit> feet =
                    new Quantity<>(1.0, LengthUnit.FEET);

            Quantity<LengthUnit> inches =
                    new Quantity<>(12.0, LengthUnit.INCHES);

            Quantity<LengthUnit> result =
                    feet.add(inches);

            assertEquals(2.0, result.getValue());
        }

        @Test
        void testSubtractionStillWorksForWeight() {

            Quantity<WeightUnit> kilogram =
                    new Quantity<>(10.0, WeightUnit.KILOGRAM);

            Quantity<WeightUnit> gram =
                    new Quantity<>(5000.0, WeightUnit.GRAM);

            Quantity<WeightUnit> result =
                    kilogram.subtract(gram);

            assertEquals(5.0, result.getValue());
        }

        @Test
        void testDivisionStillWorksForVolume() {

            Quantity<VolumeUnit> litre =
                    new Quantity<>(10.0, VolumeUnit.LITRE);

            Quantity<VolumeUnit> litre2 =
                    new Quantity<>(5.0, VolumeUnit.LITRE);

            double result = litre.divide(litre2);

            assertEquals(2.0, result);
        }
    }