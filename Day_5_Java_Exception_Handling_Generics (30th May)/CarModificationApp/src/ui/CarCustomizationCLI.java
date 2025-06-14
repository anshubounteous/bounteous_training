package ui;

import model.*;
import service.*;

import java.util.Scanner;

public class CarCustomizationCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final ICarCustomizationService service = new CarCustomizationServiceImpl();

    public void run() {
        System.out.println("Welcome to Car Customization Portal!");

        Manufacturer manufacturer = selectManufacturer();
        Model model = manufacturer == Manufacturer.MAHINDRA ? selectModel() : null;
        Transmission transmission = selectTransmission();
        FuelType fuelType = selectFuelType();
        Color color = selectColor();
        Location location = selectLocation();

        Car car = service.createCar(manufacturer, model, transmission, fuelType, color, location);
        System.out.println(car);
    }

    private Manufacturer selectManufacturer() {
        System.out.println("Choose Manufacturer:");
        for (Manufacturer m : Manufacturer.values())
            System.out.println("- " + m);
        return Manufacturer.valueOf(scanner.next().toUpperCase());
    }

    private Model selectModel() {
        System.out.println("Choose Mahindra Model:");
        for (Model m : Model.values())
            System.out.println("- " + m);
        return Model.valueOf(scanner.next().toUpperCase().replace(" ", "_"));
    }

    private Transmission selectTransmission() {
        System.out.println("Choose Transmission:");
        for (Transmission t : Transmission.values())
            System.out.println("- " + t);
        return Transmission.valueOf(scanner.next().toUpperCase());
    }

    private FuelType selectFuelType() {
        System.out.println("Choose Fuel Type:");
        for (FuelType f : FuelType.values())
            System.out.println("- " + f);
        return FuelType.valueOf(scanner.next().toUpperCase());
    }

    private Color selectColor() {
        System.out.println("Choose Color:");
        for (Color c : Color.values())
            System.out.println("- " + c);
        return Color.valueOf(scanner.next().toUpperCase());
    }

    private Location selectLocation() {
        System.out.println("Choose Location:");
        for (Location l : Location.values())
            System.out.println("- " + l);
        return Location.valueOf(scanner.next().toUpperCase());
    }
}
