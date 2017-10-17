package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.*;
import static com.example.zina.parkingandroidapp.gateway.GatewayUtils.*;


public class LoginGateway {

    private String loginUrl;

    private static final String SERVICE_NAME = "auth";

    private JsonUtils jsonUtils;
    private HttpUtils httpUtils;

    public LoginGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        loginUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
    }

    public Response<User> login(LoginDetails loginDetails) {
        Log.i("LoginGateway", "Sending login request");
        String body = jsonUtils.convertObjectToString(loginDetails);
        HttpPost httpPost = httpUtils.buildHttpPost(loginUrl, body);
        HttpResponse response = httpUtils.makeRequest(httpPost);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("LoginGateway", "Login request completed successfully");
            return new Response<User>(jsonUtils.convertStringToObject(responseBody, User.class));
        } else {
            Log.i("RegistrationGateway", "Registration request failed");
            String responseBody = httpUtils.extractResponseBody(response);
            return new Response<User>(responseBody);
        }
    }

    public void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }
}
