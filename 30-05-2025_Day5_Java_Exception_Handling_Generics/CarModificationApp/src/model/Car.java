package model;

public class Car {
    private Manufacturer manufacturer;
    private Model model;
    private Transmission transmission;
    private FuelType fuelType;
    private Color color;
    private Location location;

    public Car (Manufacturer manufacturer, Model model, Transmission transmission, FuelType fuelType, Color color, Location location){
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.color = color;
        this.location = location;
    }

    @Override
    public String toString() {
        return "\nCustomized Car Configuration:" +
                "\nManufacturer: " + manufacturer +
                "\nModel: " + model +
                "\nTransmission: " + transmission +
                "\nFuel Type: " + fuelType +
                "\nColor: " + color +
                "\nLocation: " + location;
    }
}
