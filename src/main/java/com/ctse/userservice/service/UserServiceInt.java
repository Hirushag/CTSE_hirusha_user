package com.ctse.userservice.service;


import com.ctse.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInt {

    //an interface create for use userService class and have all method signatures.
    void createUser(User user);

    public List<User> users();

    public Optional<User> getSingleUser(String id);

    public boolean editUser(User user, String id);

    public boolean deleteUser(String id);

    public List<User> userWithStatus(String status[]);

}
