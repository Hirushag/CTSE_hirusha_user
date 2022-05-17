package com.ctse.userservice.controller;

import com.ctse.userservice.dto.Login;
import com.ctse.userservice.dto.UserDetails;
import com.ctse.userservice.model.User;
import com.ctse.userservice.response.CommonResponse;
import com.ctse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserService userService;


    //endpoint for register user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    //endpoint get all users
    @GetMapping("/users")
    public List<User> users() {
        return userService.users();
    }

    //endpoint for get single user
    @GetMapping("/user")
    public Optional<User> user(@RequestParam String id) {
        return userService.getSingleUser(id);
    }

    // endpoint for update user
    @PutMapping("/users/{id}")
    public boolean editUser(@PathVariable String id, @RequestBody User user) {
        return userService.editUser(user, id);
    }

    // endpoint for delete user
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    //get user list according to status
    @PostMapping("/list")
    public List<User> userWithStatus(@RequestParam String status[]) {
        return userService.userWithStatus(status);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> userLogin(@RequestBody Login login) {

        try {

            UserDetails object = userService.authenticateUser(login);

            return ResponseEntity.ok(new CommonResponse<Object>(true,null,object));


        }catch (Exception e) {
            return ResponseEntity.ok(new CommonResponse<Object>(false,null,null));
        }

    }


}
