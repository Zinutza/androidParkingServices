package com.example.zina.parkingandroidapp.gateway;

import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.User;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.*;
import static com.example.zina.parkingandroidapp.gateway.GatewayUtils.*;


public class LoginGateway {

    private String loginUrl;

    private static final String SERVICE_NAME = "login";

    public LoginGateway() {
        loginUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
    }



    public User login(LoginDetails loginDetails) {



        return null;
    }

}
