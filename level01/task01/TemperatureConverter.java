package task01;

import java.util.InputMismatchException;
import java.util.Scanner;
public class TemperatureConverter {
    static Scanner scanner = new Scanner(System.in);

    public float celsiusToFahrenheit(float celsius){
        return (celsius * 9/5) + 32;
    }

    public float fahrenheitToCelsius(float fahrenheit){
        return (fahrenheit - 32) * 5/9;
    }

    public static void main(String[] args) {
        TemperatureConverter tc = new TemperatureConverter();
        System.out.println("\nWelcome to Kartik's Temperature Converter!");

        System.out.println("Enter the temperature: ");
        float temperature = scanner.nextFloat();

        int measurementUnit = 0;
        float convertedTemp;
        try {
            System.out.println("Press:" +
                    "\n1. Fahrenheit" +
                    "\n2. Celsius ");
            measurementUnit = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please choose 1 or 2: ");
        }
        if (measurementUnit == 1) {
            convertedTemp = tc.fahrenheitToCelsius(temperature);
            System.out.println("\n" + temperature + "F in celsius is: " + convertedTemp + "C");
        } else {
            convertedTemp = tc.celsiusToFahrenheit(temperature);
            System.out.println("\n" + temperature + "C in fahrenheit is: " + convertedTemp + "F");
        }
        scanner.close();
    }

}
