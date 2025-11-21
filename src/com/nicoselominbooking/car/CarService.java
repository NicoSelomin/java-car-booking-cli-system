package com.nicoselominbooking.car;

import com.nicoselominbooking.carbooking.CarBooking;

public class CarService {
    private final CarDao carDao;

    public CarService(){
        this.carDao = new CarFileDataAccessService();
    }

    public Car[] getAllCars(){
        return carDao.getAllCars();
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
        return carDao.findElectricCars();
    }

}
