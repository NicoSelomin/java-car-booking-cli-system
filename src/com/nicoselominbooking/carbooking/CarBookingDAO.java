package com.nicoselominbooking.carbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingDAO {
    private static final List<CarBooking> carBookings = new ArrayList<>();

    public List<CarBooking> getCarBookings() {
        return List.copyOf(carBookings);
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

        carBookings.stream()
                .filter(booking -> id.equals(booking.getBookingId()))
                .findFirst()
                .ifPresentOrElse(
                        booking -> {
                            booking.setCanceled(true);
                            System.out.println("Booking " + id + " canceled successfully.");
                        },
                        () -> System.out.println("Cancel failed: Booking " + id + " not found.")
                );

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

        return carBookings.stream()
                .filter(booking -> booking.getUser() != null)
                .filter(booking -> userID.equals(booking.getUser().getId()))
                .toList();
    }


    public List<CarBooking> getBookingForCar(String carRegNumber){
        if (carRegNumber == null || carRegNumber.isEmpty()){
            throw new IllegalArgumentException("Car registration number cannot be null or empty");
        }

        return carBookings.stream()
                .filter(booking -> carRegNumber.equals(booking.getCar().getRegNumber()))
                .toList();
    }

}
