package com.example.zina.parkingandroidapp.services;

import com.example.zina.parkingandroidapp.gateway.RegistrationGateway;
import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;


public class RegistrationService {

    private RegistrationGateway registrationGateway;

    public User register(RegistrationDetails registrationDetails) {
        return registrationGateway.register(registrationDetails);
    }

    public void setRegistrationGateway(RegistrationGateway registrationGateway) {
        this.registrationGateway = registrationGateway;
    }
}
