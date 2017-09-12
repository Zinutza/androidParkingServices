package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.util.Arrays;
import java.util.List;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.GatewayUtils.buildServiceURL;


public class FavouritesGateway {

    private String favouritesUrl;

    private static final String SERVICE_NAME = "favourites";

    private JsonUtils jsonUtils;
    private HttpUtils httpUtils;

    public FavouritesGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        favouritesUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
    }

    public List<ParkingLocation> list(User user) {
        Log.i("FavouritesGateway", "Sending list favourites request");
        String queryUrl = buildListUrl(favouritesUrl, user.getId());
        HttpGet httpGet = httpUtils.buildHttpGet(queryUrl);
        HttpResponse response = httpUtils.makeRequest(httpGet);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("ParkingLocationGateway", "List favourites request request complete");
            ParkingLocation[] parkingLocations =  jsonUtils.convertStringToObject(responseBody, ParkingLocation[].class);
            return Arrays.asList(parkingLocations);
        }
        Log.i("ParkingLocationGateway", "List favourites request failed");
        return null;
    }

    private String buildListUrl(String parkingLocationsUrl, Long userId) {
        return favouritesUrl + "?userId=" + userId;
    }

    public void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }
}
