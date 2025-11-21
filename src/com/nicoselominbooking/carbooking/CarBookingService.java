package com.nicoselominbooking.carbooking;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.car.CarArrayDataAccessService;
import com.nicoselominbooking.car.CarDao;
import com.nicoselominbooking.car.CarFileDataAccessService;
import com.nicoselominbooking.user.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO = new CarBookingDAO();
    private final CarDao carDAO = new CarFileDataAccessService();


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

    public CarBooking[] findCarBookingByUserId(UUID userID){
        if (userID == null){
            throw new IllegalArgumentException("user id must not be null");
        }
        return carBookingDAO.getBookingByUserId(userID);
    }

    public CarBooking[] findCarBookingForCar(String regNumber){
        if (regNumber == null){
            throw new IllegalArgumentException("Car Registration number must not be null");
        }

        return carBookingDAO.getBookingForCar(regNumber);
    }

    public Car[] getAvailableCars() {
        Car[] allCars = carDAO.getAllCars();

        CarBooking[] allBookings = carBookingDAO.getCarBookings();

        Car[] tempAvailable = new Car[allCars.length];
        int count = 0;

        for (Car car : allCars) {
            boolean isBooked = false;

            for (CarBooking booking : allBookings) {

                if (booking != null && !booking.isCanceled() && booking.getCar().equals(car)) {
                    isBooked = true;
                    break;
                }
            }

            if (!isBooked) {
                tempAvailable[count++] = car;
            }
        }
        return Arrays.copyOf(tempAvailable, count);
    }



}
