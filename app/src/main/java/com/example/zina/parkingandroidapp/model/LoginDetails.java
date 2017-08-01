package com.example.zina.parkingandroidapp.model;

public class LoginDetails {

    private String email;

    private String passwordClearText;

    public LoginDetails(String email, String password) {
        this.email = email;
        this.passwordClearText = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordClearText() {
        return passwordClearText;
    }

    public void setPasswordClearText(String passwordClearText) {
        this.passwordClearText = passwordClearText;
    }

}
