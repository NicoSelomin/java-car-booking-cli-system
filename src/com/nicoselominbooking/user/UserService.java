package com.nicoselominbooking.user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final  UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUser(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User id must not be null");
        }

        return getAllUsers().stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                String.format("User with id %s not found", id)
                        )
                );
    }

}
