package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.gateway.util.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.util.JsonUtils;
import com.example.zina.parkingandroidapp.model.ParkingLocation;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.Arrays;
import java.util.List;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.util.GatewayUtils.buildServiceURL;


public class ParkingLocationGateway {

    private String parkingLocationsUrl;

    private static final String SERVICE_NAME = "parkinglocations";

    private JsonUtils jsonUtils;
    private HttpUtils httpUtils;

    public ParkingLocationGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        parkingLocationsUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
    }

    public List<ParkingLocation> findParkingNearby(double latitude, double longitude) {
        Log.i("ParkingLocationGateway", "Sending local parking location request");
        String queryUrl = buildQueryUrl(parkingLocationsUrl, latitude, longitude);
        HttpGet httpGet = httpUtils.buildHttpGet(queryUrl);
        HttpResponse response = httpUtils.makeRequest(httpGet);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("ParkingLocationGateway", "Local parking location request complete");
            ParkingLocation[] parkingLocations =  jsonUtils.convertStringToObject(responseBody, ParkingLocation[].class);
            return Arrays.asList(parkingLocations);
        }
        Log.i("ParkingLocationGateway", "Local parking location request failed");
        return null;
    }

    public ParkingLocation create(ParkingLocation parkingLocation) {
        Log.i("ParkingLocationGateway", "Sending create parking location request");
        String requestBody = jsonUtils.convertObjectToString(parkingLocation);
        HttpPost httpPost = httpUtils.buildHttpPost(parkingLocationsUrl, requestBody);
        HttpResponse response = httpUtils.makeRequest(httpPost);
        if(response.getStatusLine().getStatusCode() == 201) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("ParkingLocationGateway", "Create parking location request complete");
            return jsonUtils.convertStringToObject(responseBody, ParkingLocation.class);
        }
        Log.i("ParkingLocationGateway", "Create parking location request failed");
        return null;
    }

    private String buildQueryUrl(String parkingLocationsUrl, double latitude, double longitude) {
        return parkingLocationsUrl + "?latitude=" + latitude + "&longitude=" + longitude;
    }

    public void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }


}
