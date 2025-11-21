package com.nicoselominbooking.user;

import java.util.UUID;

public class UserService {
    private final  UserDao userDao;

    public UserService(){
        this.userDao = new UserFileDataAccessService();
    }

    public User[] getAllUsers(){
        return userDao.getUsers();
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
