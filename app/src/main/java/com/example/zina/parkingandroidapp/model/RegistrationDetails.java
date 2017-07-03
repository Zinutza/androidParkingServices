package com.example.zina.parkingandroidapp.model;

public class RegistrationDetails {

    private String email;

    private String password;

    private String confirmEmail;

    public RegistrationDetails(String email, String password, String confirmEmail) {
        this.email = email;
        this.password = password;
        this.confirmEmail = confirmEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
}
