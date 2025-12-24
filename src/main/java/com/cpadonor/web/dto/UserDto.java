package com.cpadonor.web.dto;

public class UserDto {
    public String name;
    public String email;
    public String phone;

    public UserDto() {}

    public UserDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}