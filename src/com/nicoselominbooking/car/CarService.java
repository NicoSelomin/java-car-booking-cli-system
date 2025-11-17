package com.nicoselominbooking.car;

import java.util.Arrays;

public class CarService {
    private final CarDAO carDAO = new CarDAO();

    public Car[] getAllCars(){
        return carDAO.getAllCars();
    }

    public Car getCar(String regNumber){
        for (Car car : getAllCars()){
            if (regNumber.equals(car.getRegNumber())){
                return car;
            }
        }
        throw new IllegalStateException(String.format("Car with reg %s not found", regNumber));
    }

    public  Car[] getAllElectricCars(){
        return carDAO.findElectricCars();
    }

}
