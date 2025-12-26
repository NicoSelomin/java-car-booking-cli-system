package com.nicoselominbooking.user;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserFileDataAccessService implements UserDao {

    private static final Path FILE_PATH =
            Paths.get("src", "com", "nicoselominbooking", "user", "users.csv");

    @Override
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try (Scanner scanner = new Scanner(FILE_PATH.toFile())) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 2) {
                    try {
                        UUID id = UUID.fromString(parts[0].trim());
                        String name = parts[1].trim();
                        users.add(new User(id, name));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid UUID format: " + parts[0]);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error: Cannot read the file - " + e.getMessage());
        }

        return users;
    }
}
