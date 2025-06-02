package service;

import model.*;

public interface ICarCustomizationService {
    Car createCar(Manufacturer manufacturer, Model model, Transmission transmission,
                  FuelType fuelType, Color color, Location location);
}
