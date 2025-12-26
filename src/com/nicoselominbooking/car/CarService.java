package com.nicoselominbooking.car;

import com.nicoselominbooking.carbooking.CarBooking;

import java.util.List;

public class CarService {
    private CarDAO carDao;

    public CarService(CarDAO carDao) {
        this.carDao = carDao;
    }


    public List<Car> getAllCars(){
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

    public  List<Car> getAllElectricCars(){
        return carDao.findElectricCars();
    }

}
