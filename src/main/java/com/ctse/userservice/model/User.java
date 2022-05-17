package com.ctse.userservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String contactNumber;
    private int isActive;
    private String role;

}
