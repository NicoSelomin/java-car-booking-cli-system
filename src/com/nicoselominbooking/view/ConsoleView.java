package com.nicoselominbooking.view;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.carbooking.CarBooking;
import com.nicoselominbooking.user.User;

import java.util.List;

public class ConsoleView {

    // Display main menu
    public static void displayMenu() {
        System.out.println("""
                
                 [1]  Book Car
                 [2]  View All User Booked Cars
                 [3]  View All Bookings
                 [4]  View Available Cars
                 [5]  View Available Electric Cars
                 [6]  View All Users
                 [7]  Exit
                """);
    }

    // Print cars
    public static void printCars(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No car available.");
            return;
        }

        int count = 1;
        for (Car car : cars) {
            System.out.printf(
                    "%d- Reg Number: %s | Brand: %s | Rent: %s/day | Electric: %s%n",
                    count++,
                    car.getRegNumber(),
                    car.getBrand(),
                    car.getRentalPerDay(),
                    car.isElectric() ? "Yes" : "No"
            );
        }
    }

    // Print users
    public static void printUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No user found in the system.");
            return;
        }

        int count = 1;
        for (User user : users) {
            System.out.printf(
                    "%d- ID: %s | Name: %s%n",
                    count++,
                    user.getId(),
                    user.getName()
            );
        }
    }

    // Print bookings
    public static void printBookings(List<CarBooking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("No booking found in the system.");
            return;
        }

        int count = 1;
        for (CarBooking booking : bookings) {
            System.out.printf("""
                    %d- Booking ID: %s
                       Client: %s
                       Car: %s (%s)
                       Status: %s
                       ----------
                    """,
                    count++,
                    booking.getBookingId(),
                    booking.getUser().getName(),
                    booking.getCar().getBrand(),
                    booking.getCar().getRegNumber(),
                    booking.isCanceled() ? "Canceled" : "Active"
            );
        }
    }
}
