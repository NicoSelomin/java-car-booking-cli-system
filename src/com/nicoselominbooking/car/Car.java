package com.nicoselominbooking.car;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private String regNumber;
    private Brand brand;
    private BigDecimal rentalPerDay;
    private boolean isElectric;

    public Car() {
    }

    public Car(String  regNumber, Brand brand, BigDecimal rentalPerDay, boolean isElectrial) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.rentalPerDay = rentalPerDay;
        this.isElectric = isElectrial;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getRentalPerDay() {
        return rentalPerDay;
    }

    public void setRentalPerDay(BigDecimal rentalPerDay) {
        this.rentalPerDay = rentalPerDay;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectrial(boolean electrial) {
        isElectric = electrial;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber=" + regNumber +
                ", brand='" + brand + '\'' +
                ", rentalPerDay=" + rentalPerDay +
                ", isElectrial=" + isElectric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return regNumber == car.regNumber && isElectric == car.isElectric && Objects.equals(brand, car.brand) && Objects.equals(rentalPerDay, car.rentalPerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, brand, rentalPerDay, isElectric);
    }
}
