package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.gateway.util.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.util.JsonUtils;
import com.example.zina.parkingandroidapp.gateway.util.Response;
import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.util.GatewayUtils.buildServiceURL;

public class RegistrationGateway {

    private HttpClient httpClient;

    private String registerUrl;

    private ObjectMapper objectMapper;

    private static final String SERVICE_NAME = "register";
    private ObjectMapper obectMapper;
    private HttpUtils httpUtils;
    private JsonUtils jsonUtils;

    private String error;

    public RegistrationGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        registerUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
        httpClient = HttpClientBuilder.create().build();
    }

    public Response<User> register(RegistrationDetails registrationDetails) {
        Log.i("RegistrationGateway", "Sending registration request");
        String body = jsonUtils.convertObjectToString(registrationDetails);
        HttpPost post = httpUtils.buildHttpPost(registerUrl, body);
        HttpResponse response = httpUtils.makeRequest(post);
        String responseBody = httpUtils.extractResponseBody(response);
        if(response.getStatusLine().getStatusCode() == 200) {
            Log.i("RegistrationGateway", "Registration request complete");
            return new Response<User>(jsonUtils.convertStringToObject(responseBody, User.class));
        } else {
            Log.i("RegistrationGateway", "Registration request failed");
            return new Response<User>(responseBody);
        }
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
