package com.nicoselominbooking;

import com.nicoselominbooking.car.Car;
import com.nicoselominbooking.car.CarService;
import com.nicoselominbooking.carbooking.CarBooking;
import com.nicoselominbooking.carbooking.CarBookingService;
import com.nicoselominbooking.user.User;
import com.nicoselominbooking.user.UserService;
import com.nicoselominbooking.view.ConsoleView;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final UserService userService = new UserService();
    private static final CarService carService = new CarService();
    private static final CarBookingService carBookingService = new CarBookingService();
    private static Scanner scanner = new Scanner(System.in);

    static void main(String[] args) {
        boolean runing = true;
        while (runing){
            try{
                ConsoleView.displayMenu();
                String choice = scanner.nextLine();
                switch (Integer.parseInt(choice)){
                    case 1:
                        bookCar();
                        break;
                    case 2:
                        getUserBookedCars();
                        break;
                    case 3:
                        getAllBookings();
                        break;
                    case 4:
                        getAvailableCars();
                        break;
                    case 5:
                        getAvailableElectricCar();
                        break;
                    case 6:
                        getAllUsers();
                        break;
                    case 7:
                        runing = false;
                        System.out.println("Thank you for using NicoBooking ! \n See you next time");
                        break;

                    default:
                        System.out.println("You must select an option within out menu");
                }
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }

    }


    //Get All Users
    private static void getAllUsers() {
        System.out.println("List of all users");
        ConsoleView.printUsers(userService.getAllUsers());
    }
    // Get Cars
    private static void getAvailableCars(){
        System.out.println("Cars List");
        ConsoleView.printCars(carService.getAllCars());
    }
    //Get Electric Cars
    private static void getAvailableElectricCar(){
        System.out.println("Our Electric Cars");
        ConsoleView.printCars(carService.getAllElectricCars());
    }
    //Get All Booking
    private static void getAllBookings(){
        System.out.println("Bookings List");
        ConsoleView.printBookings(carBookingService.getAllBookings());
    }
    //Get user booked cars
    private static void getUserBookedCars(){
        System.out.println("Find user's bookings");

        System.out.println("Users");
        ConsoleView.printUsers(userService.getAllUsers());

        System.out.println("Enter User's ID :");
        String userId = scanner.nextLine();
        User user = null;
        try{
            user = userService.getUser(UUID.fromString(userId));
        }catch (Exception e){
            System.out.println("Invalid user id");
        }

        if (user == null) {
            System.out.println("User not found");
            return;
        }

        System.out.println("Booking for user : "+ user.getName());

        CarBooking[] bookings = carBookingService.findCarBookingByUserId(user.getId());
        ConsoleView.printBookings(bookings);

    }
    //Book car
    private static void bookCar(){
        System.out.println("--- Book a car ---");

        System.out.println("Users available in the system:");
        ConsoleView.printUsers(userService.getAllUsers());

        System.out.print("Enter the user ID: ");
        String userIdInput = scanner.nextLine();
        User user = null;
        try{
            user = userService.getUser(UUID.fromString(userIdInput));
        }catch (Exception e){
            System.out.println("Invalid user ID.");
            return;
        }

        if (user == null) {
            System.out.println("User not found. Returning to menu.");
            return;
        }

        // --- LOGIC: Fetch available cars instead of all cars ---
        Car[] availableCars = carBookingService.getAvailableCars();
        System.out.println("Available Cars:");
        ConsoleView.printCars(availableCars);

        if (availableCars.length == 0) {
            System.out.println("No cars are currently available for booking.");
            return;
        }

        System.out.print("Enter the car Reg Number: ");
        String regNumber = scanner.nextLine();

        // 1. Find car object using the service (ensures the car exists)
        Car car = carService.getCar(regNumber);

        if (car == null) {
            System.out.println("Car not found in the fleet.");
            return;
        }

        // 2. LOGIC: Check if the selected car is actually in the 'availableCars' list
        boolean isAvailable = false;
        for (Car availableCar : availableCars) {
            if (availableCar.getRegNumber().equals(regNumber)) {
                isAvailable = true;
                break;
            }
        }

        if (!isAvailable) {
            System.out.println("Error: This car is currently booked or unavailable.");
            return;
        }

        // --- FINAL BOOKING ---
        try {
            carBookingService.bookCar(user, car);
            System.out.println("Car booked successfully for " + user.getName() + ".");
        }catch (Exception e){
            System.out.println("Error during the booking: " + e.getMessage());
        }
    }

}
