package com.nicoselominbooking.car;

import java.math.BigDecimal;

public class CarDAO {
    private static final Car[] CARS = {
            new Car("1234", Brand.TESLA, new BigDecimal("89.00"), true),
            new Car("5678", Brand.AUDI, new BigDecimal("50.00"),false),
            new Car("5678", Brand.MERCEDES, new BigDecimal("77.00"), false),
            new Car("2584", Brand.TOYOTA, new BigDecimal("100"), false)
    };

    public Car[] getAllCars(){
        return CARS;
    }
}
