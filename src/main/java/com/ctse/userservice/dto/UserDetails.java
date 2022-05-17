package com.ctse.userservice.dto;

import lombok.Data;


@Data
public class UserDetails {

    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String contactNumber;
    private int isActive;
    private String role;

}