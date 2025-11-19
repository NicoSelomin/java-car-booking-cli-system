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
        ConsoleView.PrintUsers(userService.getAllUsers());
    }
    // Get Cars
    private static void getAvailableCars(){
        System.out.println("Cars List");
        ConsoleView.PrintCars(carService.getAllCars());
    }
    //Get Electric Cars
    private static void getAvailableElectricCar(){
        System.out.println("Our Electric Cars");
        ConsoleView.PrintCars(carService.getAllElectricCars());
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
        ConsoleView.PrintUsers(userService.getAllUsers());

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
        System.out.println("Book a car");

        System.out.println("Users available in the system");
        ConsoleView.PrintUsers(userService.getAllUsers());

        System.out.println("Enter the user ID : ");

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

        System.out.println("Our Cars");
        ConsoleView.PrintCars(carService.getAllCars());

        System.out.println("Enter the car Reg Number : ");
        String regNumber = scanner.nextLine();

        //Find car
        Car car = carService.getCar(regNumber);

        if (car == null){
            System.out.println("Car not found");
        }

        try {
            carBookingService.bookCar(user,car);
            System.out.println("Car booked successfully");
        }catch (Exception e){
            System.out.println("Error during the booking");
        }

    }

}
