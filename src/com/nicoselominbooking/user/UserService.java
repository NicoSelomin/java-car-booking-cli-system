package com.nicoselominbooking.user;

import java.util.UUID;

public class UserService {
    private final  UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User[] getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUser(UUID id){
        for (User user : getAllUsers()){
            if (user.getId().equals(id)){
                return user;
            }
        }
        throw new IllegalStateException(String.format("User with id %s not found", id));
    }
}
