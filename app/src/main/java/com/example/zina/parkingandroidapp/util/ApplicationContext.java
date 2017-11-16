package com.example.zina.parkingandroidapp.util;

import com.example.zina.parkingandroidapp.gateway.FavouritesGateway;
import com.example.zina.parkingandroidapp.gateway.RatingsGateway;
import com.example.zina.parkingandroidapp.gateway.util.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.util.JsonUtils;
import com.example.zina.parkingandroidapp.gateway.LoginGateway;
import com.example.zina.parkingandroidapp.gateway.ParkingLocationGateway;
import com.example.zina.parkingandroidapp.gateway.RegistrationGateway;
import com.example.zina.parkingandroidapp.services.FavouritesService;
import com.example.zina.parkingandroidapp.services.LoginService;
import com.example.zina.parkingandroidapp.services.ParkingLocationServices;
import com.example.zina.parkingandroidapp.services.RatingsService;
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
            loginGateway.setJsonUtils(jsonUtils());
            loginGateway.setHttpUtils(httpUtils());
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

    private static ParkingLocationServices parkingLocationServices;

    public static ParkingLocationServices parkingLocationServices() {
        if(parkingLocationServices == null) {
            parkingLocationServices = new ParkingLocationServices();
            parkingLocationServices.setParkingLocationGateway(parkingLocationGateway());
        }
        return parkingLocationServices;
    }

    private static ParkingLocationGateway parkingLocationGateway;

    public static ParkingLocationGateway parkingLocationGateway() {
        if(parkingLocationGateway == null) {
            parkingLocationGateway = new ParkingLocationGateway();
            parkingLocationGateway.setJsonUtils(jsonUtils());
            parkingLocationGateway.setHttpUtils(httpUtils());
        }
        return parkingLocationGateway;
    }

    private static FavouritesService favouritesService;

    public static FavouritesService favouritesService() {
        if(favouritesService == null) {
            favouritesService = new FavouritesService();
            favouritesService.setFavouritesGateway(favouritesGateway());
        }
        return favouritesService;
    }

    private static FavouritesGateway favouritesGateway;

    public static FavouritesGateway favouritesGateway() {
        if(favouritesGateway == null) {
            favouritesGateway = new FavouritesGateway();
            favouritesGateway.setJsonUtils(jsonUtils());
            favouritesGateway.setHttpUtils(httpUtils());
        }
        return favouritesGateway;
    }

    private static RatingsService ratingsService;

    public static RatingsService ratingsService() {
        if(ratingsService == null) {
            ratingsService = new RatingsService();
            ratingsService.setRatingsGateway(ratingsGateway());
        }
        return ratingsService;

    }

    private static RatingsGateway ratingsGateway;

    private static RatingsGateway ratingsGateway() {
        if(ratingsGateway == null) {
            ratingsGateway = new RatingsGateway();
            ratingsGateway.setJsonUtils(jsonUtils());
            ratingsGateway.setHttpUtils(httpUtils());
        }
        return ratingsGateway;
    }
}
