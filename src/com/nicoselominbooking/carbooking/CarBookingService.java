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


    public void bookCar(User user, Car car) {

        if (user == null || car == null) {
            throw new IllegalArgumentException("User and Car must not be null");
        }

        boolean alreadyBooked = carBookingDAO.getCarBookings().stream()
                .anyMatch(booking ->
                        !booking.isCanceled()
                                && booking.getCar().equals(car)
                );

        if (alreadyBooked) {
            throw new IllegalStateException("Car is already booked");
        }

        CarBooking newCarBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                LocalDateTime.now()
        );

        carBookingDAO.book(newCarBooking);
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
        List<CarBooking> allBookings = carBookingDAO.getCarBookings();

        return carService.getAllCars().stream()
                .filter(car ->
                        allBookings.stream()
                                .noneMatch(booking ->
                                        !booking.isCanceled() && booking.getCar().equals(car)
                                        )
                        )
                .toList();
    }



}
