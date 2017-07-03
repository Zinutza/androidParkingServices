package com.example.zina.parkingandroidapp.gateway;

import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.GatewayUtils.buildServiceURL;

public class RegistrationGateway {

    private HttpClient httpClient;

    private String registerUrl;

    private ObjectMapper objectMapper;

    private static final String SERVICE_NAME = "register";
    private ObjectMapper obectMapper;

    public RegistrationGateway() {
        registerUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
        httpClient = HttpClientBuilder.create().build();
    }

    public User register(RegistrationDetails registrationDetails) {
        HttpPost post = new HttpPost(registerUrl);
        post.setEntity(registrationDetails);
        return null;
    }

    public void setObectMapper(ObjectMapper obectMapper) {
        this.obectMapper = obectMapper;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
