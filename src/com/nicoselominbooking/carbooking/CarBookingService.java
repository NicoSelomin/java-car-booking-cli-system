package com.nicoselominbooking.carbooking;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO = new CarBookingDAO();


    public void bookCar(User user, Car car){
        //Verify if the arguments are not empty
        if (user == null || car == null){
            throw new IllegalArgumentException("User and Car must not null");
        }

        for (CarBooking existingBooking : carBookingDAO.getCarBookings()){
            if (existingBooking != null && existingBooking.getCar().equals(car)){
                System.out.println("Sorry, this car is already booked");
                return;
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

    public CarBooking[] getAllBookings(){
        return carBookingDAO.getCarBookings();
    }

    public void cancelBooking(UUID id){
        if (id == null) {
            throw new IllegalArgumentException("Booking id must not be null");
        }
       carBookingDAO.cancelCarBooking(id);
    }



}
