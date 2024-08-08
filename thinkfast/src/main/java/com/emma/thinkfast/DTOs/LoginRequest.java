package com.emma.thinkfast.dtos;

public class LoginRequest implements RequestDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPw(String password) {
        this.password = password;
    }
    
}
