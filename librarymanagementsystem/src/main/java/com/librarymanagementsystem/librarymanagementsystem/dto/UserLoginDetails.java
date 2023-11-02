package com.librarymanagementsystem.librarymanagementsystem.dto;

public class UserLoginDetails {
    String name;
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
