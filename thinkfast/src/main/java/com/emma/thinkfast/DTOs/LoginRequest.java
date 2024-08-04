package com.emma.thinkfast.DTOs;

public class LoginRequest implements RequestDTO {

    private String userName;
    private String pw;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
}
