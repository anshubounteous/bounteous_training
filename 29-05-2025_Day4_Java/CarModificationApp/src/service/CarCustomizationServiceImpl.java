package service;

import model.*;

public class CarCustomizationServiceImpl implements ICarCustomizationService {

    @Override
    public Car createCar(Manufacturer manufacturer, Model model, Transmission transmission,
                         FuelType fuelType, Color color, Location location) {
        return new Car(manufacturer, model, transmission, fuelType, color, location);
    }
}
