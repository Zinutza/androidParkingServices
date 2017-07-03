package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.StringEntityHC4;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    private HttpUtils httpUtils;
    private JsonUtils jsonUtils;

    public RegistrationGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        registerUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
        httpClient = HttpClientBuilder.create().build();
    }

    public User register(RegistrationDetails registrationDetails) {
        Log.i("RegistrationGateway", "Sending registration request");
        String body = jsonUtils.convertObjectToString(registrationDetails);
        HttpPost post = httpUtils.buildHttpPost(registerUrl, body);
        HttpResponse response = httpUtils.makeRequest(post);
        String responseBody = httpUtils.extractResponseBody(response);
        Log.i("RegistrationGateway", "Registration request complete");
        return jsonUtils.convertStringToObject(responseBody, User.class);
    }

    public void setObectMapper(ObjectMapper obectMapper) {
        this.obectMapper = obectMapper;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }
}
