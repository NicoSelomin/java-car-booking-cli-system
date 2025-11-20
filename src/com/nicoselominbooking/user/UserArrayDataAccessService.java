package com.nicoselominbooking.user;

import java.util.UUID;

public class UserDAO {
    private final static User[] USERS = {
            new User(UUID.randomUUID(), "Alice Smith"),
            new User(UUID.randomUUID(), "Bob Johnson"),
            new User(UUID.randomUUID(), "Charlie Brown")
    };

    public User[] getUsers(){
        return USERS;
    }


}
