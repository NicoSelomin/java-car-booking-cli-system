package com.nicoselominbooking.carbooking;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.car.CarDAO;
import com.nicoselominbooking.car.CarService;
import com.nicoselominbooking.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
    }


    public void bookCar(User user, Car car){
        if (user == null || car == null){
            throw new IllegalArgumentException("User and Car must not null");
        }

        for (CarBooking existingBooking : carBookingDAO.getCarBookings()){
            if (!existingBooking.isCanceled() && existingBooking.getCar().equals(car)) {
                throw new IllegalStateException("Car is already booked");
            }
        }

        CarBooking newCarBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDateTime.now()
        );

        carBookingDAO.book(newCarBooking);

        System.out.println("Car booked successfully !");
    }

    public List<CarBooking> getAllBookings(){
        return carBookingDAO.getCarBookings();
    }

    public void cancelBooking(UUID id){
        if (id == null) {
            throw new IllegalArgumentException("Booking id must not be null");
        }
       carBookingDAO.cancelCarBooking(id);
    }

    public List<CarBooking> findCarBookingByUserId(UUID userID){
        if (userID == null){
            throw new IllegalArgumentException("user id must not be null");
        }
        return carBookingDAO.getBookingByUserId(userID);
    }

    public List<CarBooking> findCarBookingForCar(String regNumber){
        if (regNumber == null){
            throw new IllegalArgumentException("Car Registration number must not be null");
        }

        return carBookingDAO.getBookingForCar(regNumber);
    }

    public List<Car> getAvailableCars() {
        List<Car> allCars = carService.getAllCars();
        List<CarBooking> allBookings = carBookingDAO.getCarBookings();

        List<Car> availableCars = new ArrayList<>();

        for (Car car : allCars) {
            boolean isBooked = false;

            for (CarBooking booking : allBookings) {
                if (!booking.isCanceled() && booking.getCar().equals(car)){
                    isBooked = true;
                    break;
                }
            }

            if (!isBooked) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }



}
