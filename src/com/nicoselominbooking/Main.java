package com.nicoselominbooking;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.car.CarDAO;
import com.nicoselominbooking.car.CarService;
import com.nicoselominbooking.carbooking.CarBooking;
import com.nicoselominbooking.carbooking.CarBookingDAO;
import com.nicoselominbooking.carbooking.CarBookingService;
import com.nicoselominbooking.user.User;
import com.nicoselominbooking.user.UserDAO;
import com.nicoselominbooking.user.UserService;
import com.nicoselominbooking.view.ConsoleView;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final UserDAO userDAO = new UserDAO();
    private static final UserService userService = new UserService(userDAO);

    private static final CarDAO carDAO = new CarDAO();
    private static final CarService carService = new CarService(carDAO);

    private static final CarBookingDAO carBookingDAO = new CarBookingDAO();
    private static final CarBookingService carBookingService =
            new CarBookingService(carBookingDAO, carService);

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            ConsoleView.displayMenu();
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> bookCar();
                    case 2 -> getUserBookedCars();
                    case 3 -> getAllBookings();
                    case 4 -> getAvailableCars();
                    case 5 -> getAvailableElectricCars();
                    case 6 -> getAllUsers();
                    case 7 -> {
                        running = false;
                        System.out.println("Thank you for using NicoBooking!\nSee you next time 👋");
                    }
                    default -> System.out.println("Please select a valid option (1–7).");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1–7).");
            }
        }

        scanner.close();
    }

    // USERS

    private static void getAllUsers() {
        System.out.println("--- List of all users ---");
        ConsoleView.printUsers(userService.getAllUsers());
    }

    // CARS

    private static void getAvailableCars() {
        System.out.println("--- Available Cars ---");
        ConsoleView.printCars(carBookingService.getAvailableCars());
    }

    private static void getAvailableElectricCars() {
        System.out.println("--- Available Electric Cars ---");
        ConsoleView.printCars(carService.getAllElectricCars());
    }

    // BOOKINGS

    private static void getAllBookings() {
        System.out.println("--- All Bookings ---");
        ConsoleView.printBookings(carBookingService.getAllBookings());
    }

    private static void getUserBookedCars() {
        System.out.println("--- Find user's bookings ---");

        ConsoleView.printUsers(userService.getAllUsers());

        System.out.print("Enter User ID: ");
        String input = scanner.nextLine();

        User user;
        try {
            user = userService.getUser(UUID.fromString(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format.");
            return;
        }

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Bookings for user: " + user.getName());
        List<CarBooking> bookings =
                carBookingService.findCarBookingByUserId(user.getId());

        ConsoleView.printBookings(bookings);
    }

    private static void bookCar() {
        System.out.println("--- Book a Car ---");

        ConsoleView.printUsers(userService.getAllUsers());

        System.out.print("Enter User ID: ");
        String userInput = scanner.nextLine();

        User user;
        try {
            user = userService.getUser(UUID.fromString(userInput));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user ID.");
            return;
        }

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        List<Car> availableCars = carBookingService.getAvailableCars();
        ConsoleView.printCars(availableCars);

        if (availableCars.isEmpty()) {
            System.out.println("No cars available for booking.");
            return;
        }

        System.out.print("Enter Car Registration Number: ");
        String regNumber = scanner.nextLine();

        Car car = carService.getCar(regNumber);

        if (car == null || !availableCars.contains(car)) {
            System.out.println("Car is not available.");
            return;
        }

        carBookingService.bookCar(user, car);
        System.out.println("Car booked successfully for " + user.getName() + ".");
    }
}
