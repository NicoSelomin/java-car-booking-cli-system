package com.nicoselominbooking.user;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

public class UserFileDataAccessService implements UserDao{

    Path File_Path = Paths.get("src","com", "nicoselominbooking", "user", "users.csv");

    @Override
    public User[] getUsers(){
        int userCount = 0;

        //Using try with resources to read and count lines
        try(Scanner counterScanner = new Scanner(File_Path.toFile())){
            while (counterScanner.hasNextLine()){
                counterScanner.nextLine();
                userCount++;
            }
        }catch (IOException e){
            System.err.println("Error : Cannot read the file"+ e.getMessage());
            return new User[0];
        }

        //if there is no user
        if (userCount == 0){
            return new User[0];
        }

        //Create and complete the array

        User[] users = new User[userCount];
        int index = 0;

        //return back to the file to read again
        try(Scanner dataScanner = new Scanner(File_Path.toFile())) {
            while (dataScanner.hasNextLine() && index < userCount){
                String line = dataScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 2){
                    try{
                        UUID id = UUID.fromString(parts[0].trim());
                        String name = parts[1].trim();
                        users[index++] = new User(id, name);
                    }catch (IllegalArgumentException e){
                        System.err.println("Error on data format");
                    }
                }
            }
            return users;
        }catch (IOException e){
            System.err.println("Error : Impossible to read data" + e.getMessage());
            return new User[0];
        }
    }
}
