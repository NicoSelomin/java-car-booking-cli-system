package com.nicoselominbooking.carbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingDAO {
    private static final List<CarBooking> carBookings = new ArrayList<>();

    public List<CarBooking> getCarBookings() {
        return carBookings;
    }

    public void book(CarBooking carBooking) {
        if (carBooking == null){
            throw new IllegalArgumentException("CarBookin must not be null");
        }
        carBookings.add(carBooking);
    }

    public void cancelCarBooking(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }

        for (CarBooking booking : carBookings){
            if (booking.getBookingId().equals(id)){
                booking.setCanceled(true);
                System.out.println("Booking " + id + " canceled successfully.");
                return;
            }
        }
        System.out.println("Cancel failed: Booking " + id + " not found.");
    }

    public List<CarBooking> getBookingByUserId(UUID userID){

        if (userID == null){
            throw new IllegalArgumentException("User ID must not be null");
        }

        List<CarBooking> results = new ArrayList<>();

        for (CarBooking booking : carBookings){
            if (booking.getUser().getId().equals(userID)){
                results.add(booking);
            }
        }
        return results;
    }


    public List<CarBooking> getBookingForCar(String carRegNumber){
        if (carRegNumber == null || carRegNumber.isEmpty()){
            throw new IllegalArgumentException("Car registration number cannot be null or empty");
        }

        List<CarBooking> results = new ArrayList<>();

        for (CarBooking booking : carBookings){
            if (booking.getCar().getRegNumber().equals(carRegNumber)){
                results.add(booking);
            }
        }
        return results;
    }

}
