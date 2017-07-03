package com.example.zina.parkingandroidapp.util;

import com.example.zina.parkingandroidapp.gateway.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.JsonUtils;
import com.example.zina.parkingandroidapp.gateway.LoginGateway;
import com.example.zina.parkingandroidapp.gateway.RegistrationGateway;
import com.example.zina.parkingandroidapp.services.LoginService;
import com.example.zina.parkingandroidapp.services.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class ApplicationContext {

    private static RegistrationService registrationService;

    public static RegistrationService registrationService() {
        if(registrationService == null) {
            registrationService = new RegistrationService();
            registrationService.setRegistrationGateway(registrationGateway());
        }
        return registrationService;
    }

    private static RegistrationGateway registrationGateway;

    public static RegistrationGateway registrationGateway() {
        if(registrationGateway == null) {
            registrationGateway = new RegistrationGateway();
            registrationGateway.setObectMapper(objectMapper());
            registrationGateway.setHttpClient(httpClient());
            registrationGateway.setHttpUtils(httpUtils());
            registrationGateway.setJsonUtils(jsonUtils());
        }
        return registrationGateway;
    }

    private static LoginService loginService;

    public static LoginService loginService() {
        if(loginService == null) {
            loginService = new LoginService();
            loginService.setLoginGateway(loginGateway());
        }
        return loginService;
    }

    private static LoginGateway loginGateway;

    public static LoginGateway loginGateway() {
        if(loginGateway == null) {
            loginGateway = new LoginGateway();
        }
        return loginGateway;
    }

    private static ObjectMapper objectMapper;

    public static ObjectMapper objectMapper() {
        if(objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    private static HttpClient httpClient;

    public static HttpClient httpClient() {
        if(httpClient == null) {
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }

    private static HttpUtils httpUtils;

    public static HttpUtils httpUtils() {
        if(httpUtils == null) {
            httpUtils = new HttpUtils();
            httpUtils.setHttpClient(httpClient());
        }
        return httpUtils;
    }

    private static JsonUtils jsonUtils;

    public static JsonUtils jsonUtils() {
        if(jsonUtils == null) {
            jsonUtils = new JsonUtils();
            jsonUtils.setObjectMapper(objectMapper());
        }
        return jsonUtils;
    }
}
