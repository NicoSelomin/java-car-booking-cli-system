package com.nicoselominbooking.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarDAO{
    private static final List<Car> CARS = new ArrayList<>();

    static {
            CARS.add(new Car("1234", Brand.TESLA, new BigDecimal("89.00"), true));
            CARS.add(new Car("5678", Brand.AUDI, new BigDecimal("50.00"),false));
            CARS.add(new Car("5678", Brand.MERCEDES, new BigDecimal("77.00"), false));
            CARS.add(new Car("2584", Brand.TOYOTA, new BigDecimal("100"), false));
    };


    public List<Car> getAllCars(){

        return CARS;
    }


    public List<Car> findElectricCars() {

        List<Car> electricCar = new ArrayList<>();

        for (Car car : CARS) {

            if (car.isElectric()) {
                electricCar.add(car);
            }
        }

        return electricCar;
    }
}
