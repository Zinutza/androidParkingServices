package com.example.zina.parkingandroidapp.services;

import com.example.zina.parkingandroidapp.gateway.LoginGateway;
import com.example.zina.parkingandroidapp.gateway.Response;
import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.User;


public class LoginService {

    private LoginGateway loginGateway;

    public Response<User> login(LoginDetails loginDetails) {
        return loginGateway.login(loginDetails);
    }

    public void setLoginGateway(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }
}
