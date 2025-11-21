package com.nicoselominbooking.car;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CarFileDataAccessService implements CarDao{

    private final File file = new File("src/com/nicoselominbooking/car/cars.csv");
    @Override
    public Car[] getAllCars(){

        if (!file.exists()){
            return new Car[0];
        }

        //count ligne into the file
        int count = 0;
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()){
                    count++;
                }
            }
        }catch (IOException e){
            System.err.println("Error occur when reading the file : " + e.getMessage());
            return new Car[0];
        }

        if (count == 0){
            return new Car[0];
        }

        //Complete the Array
        Car[] cars = new Car[count];
        int index = 0;

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine() && index < count){
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] fields = line.split(",");

                if (fields.length >= 4){
                    try {
                        String regNumber = fields[0].trim();
                        //conversion String to Brand Enum
                        Brand brand = Brand.valueOf(fields[1].trim().toUpperCase());
                        //String conversion into BigDecimal
                        BigDecimal price = new BigDecimal(fields[2].trim());
                        // Conversion String -> boolean
                        boolean isElectric = Boolean.parseBoolean(fields[3].trim());

                        cars[index++] = new Car(regNumber, brand, price, isElectric);
                    }catch (Exception e){
                        System.err.println("Error when parsing line : " + line);
                    }
                }
            }
        }catch (IOException e){
            System.err.println("Error occur when reading the file : " + e.getMessage());
        }
        return cars;


    }

    public Car[] findElectricCars(){
        Car[] allCars = getAllCars();

        int count = 0;

        for (Car car : allCars) {
            if (car != null && car.isElectric()){
                count++;
            }
        }

        Car[] electricCars = new Car[count];
        int index = 0;

        for (Car car : allCars) {
            if (car != null && car.isElectric()){
                electricCars[index++] = car;
            }
        }
        return electricCars;
    }


}
