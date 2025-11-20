package com.nicoselominbooking.user;

import java.util.UUID;

public class UserArrayDataAccessService implements UserDao{
    private final static User[] users = {
            new User(UUID.randomUUID(), "Alice Smith"),
            new User(UUID.randomUUID(), "Bob Johnson"),
            new User(UUID.randomUUID(), "Charlie Brown"),
            new User(UUID.randomUUID(), "Jean Barron"),
    };

    @Override
    public User[] getUsers(){
        return users;
    }


}
