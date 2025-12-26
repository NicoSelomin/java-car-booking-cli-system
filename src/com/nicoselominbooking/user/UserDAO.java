package com.nicoselominbooking.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO implements UserDao{
    private final static List<User> users = new ArrayList<>();

    {
        users.add(new User(UUID.randomUUID(), "Alice Smith"));
        users.add(new User(UUID.randomUUID(), "Bob Johnson"));
        users.add(new User(UUID.randomUUID(), "Charlie Brown"));
        users.add(new User(UUID.randomUUID(), "Jean Barron"));
    };

    @Override
    public List<User> getUsers(){

        return users;
    }


}
