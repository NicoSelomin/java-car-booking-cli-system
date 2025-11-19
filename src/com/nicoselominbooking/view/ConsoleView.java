package com.nicoselominbooking.view;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.carbooking.CarBooking;
import com.nicoselominbooking.user.User;

public class ConsoleView {
    //Display menu
    public static void displayMenu() {
        System.out.println("""
                \n
                 [1]  Book Car
                 [2]  View All User Booked Cars
                 [3]  View All Bookings
                 [4]  View Available Cars
                 [5]  View Available Electric Cars
                 [6]  View All Users
                 [7]  Exit
                """);
    }

    //Print Car helper
    public static void printCars(Car[] cars) {
        if (cars.length == 0) {
            System.out.println("No car available");
        }
        int count = 0;
        for (Car car : cars) {
            count++;
            System.out.printf(count + "- Reg Number : %s , Brand : %s, Rent : %s/day, Electric : %s\n",
                    car.getRegNumber(),
                    car.getBrand(),
                    car.getRentalPerDay(),
                    car.isElectric() ? "Yes" : "No"
            );
        }
    }

    //User print helper
    public static void printUsers(User[] users) {
        if (users.length == 0) {
            System.out.println("No user find in the System");
        }
        int count = 0;
        for (User user : users) {
            count++;
            if (user == null) continue;
            System.out.printf(count + "- ID : %s, Name : %s\n", user.getId(), user.getName());
        }
    }

    public static void printBookings(CarBooking[] bookings) {
        int count = 0;

        for (CarBooking booking : bookings) {
            if (booking != null) {
                count++;
                System.out.printf("  -> [Booking ID: %s]\n     Client: %s\n     Car: %s (%s)\n     Status: %s\n",
                        booking.getBookingId(),
                        booking.getUser().getName(),
                        booking.getCar().getBrand(),
                        booking.getCar().getRegNumber(),
                        booking.isCanceled() ? "Canceled" : "Active");
                System.out.println("     ----------");
            }
        }
        if (count == 0){
            System.out.printf("No Booking found in the system");
        }
    }
}
