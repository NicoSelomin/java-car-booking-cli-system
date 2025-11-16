package com.nicoselominbooking.carbooking;

import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] carBookings;

    static {
        carBookings = new CarBooking[10];
    }

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

    public void book(CarBooking carBooking) {
        int nextFreeIndex = -1;

        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i] == null) {
                nextFreeIndex = i;
                break;
            }
        }

        if (nextFreeIndex > -1) {
            carBookings[nextFreeIndex] = carBooking;
            return;
        }


        CarBooking[] biggerCarBookings = new CarBooking[carBookings.length + 10];

        for (int i = 0; i < carBookings.length; i++) {
            biggerCarBookings[i] = carBookings[i];
        }

        biggerCarBookings[carBookings.length] = carBooking;

        carBookings = biggerCarBookings;
    }

    public void cancelCarBooking(UUID id) {
        //Safety check (cannot search for a null ID)
        if (id == null) {
            System.out.println("Error: Booking ID cannot be null.");
            return; // Exit the method
        }

        boolean found = false;

        //Loop through the array
        for (CarBooking booking : carBookings) {

            // Check if the slot is not empty AND if the ID matches
            if (booking != null && booking.getBookingId().equals(id)) {

                //FOUND IT! Update its status
                // (This assumes your CarBooking class has a setCanceled method)
                booking.setCanceled(true);

                found = true; // Raise the flag

                break;
            }
        }

        // AFTER the loop, check the flag to give user feedback
        if (found) {
            System.out.println("Booking " + id + " canceled successfully.");
        } else {
            // We only know it's not found AFTER checking the whole array
            System.out.println("Cancel failed: Booking " + id + " not found.");
        }
    }

    public CarBooking[] getBookingByUserId(UUID userID){

        //Create tempArray to store data during the searching

        CarBooking[] temporaryArray = new CarBooking[carBookings.length];
        int count = 0;

        for (CarBooking booking : carBookings) {
            if (booking != null) {
                if (booking.getUser().getId().equals(userID)) {
                    temporaryArray[count] = booking;
                    count++;
                }

            }
        }

        //variable for final results
        CarBooking[] finalResults = new CarBooking[count];
        System.arraycopy(temporaryArray,0,finalResults,0, count);

        //return the results
        return finalResults;
    }


    //This method finds ALL bookings for a specific car, using its registration number.

    public CarBooking[] getBookingForCar(String carRegNumber){
        //safety check
        if (carRegNumber == null || carRegNumber.isEmpty()){
            System.out.println("Cra registration number cannot be null or empty");

            return new CarBooking[0];
        }

        //temp array for storage
        CarBooking[] tempArray = new CarBooking[carBookings.length];
        int counter = 0;

        for (CarBooking booking : carBookings){
            if (booking != null){
                if(booking.getCar().getRegNumber().equals(carRegNumber)){
                    tempArray[counter] = booking;
                    counter ++;
                }
            }
        }

        CarBooking[] finalResults = new CarBooking[counter];
        System.arraycopy(tempArray,0,finalResults,0,counter);

        return finalResults;
    }

}
