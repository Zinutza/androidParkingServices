package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.gateway.util.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.util.JsonUtils;
import com.example.zina.parkingandroidapp.model.FavouriteLocation;
import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.Arrays;
import java.util.List;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.util.GatewayUtils.buildServiceURL;


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

    public Boolean create(FavouriteLocation favouriteLocation) {
        Log.i("FavouritesGateway", "Sending add favourite request");
        String requestBody = jsonUtils.convertObjectToString(favouriteLocation);
        HttpPost httpPost = httpUtils.buildHttpPost(favouritesUrl, requestBody);
        HttpResponse response = httpUtils.makeRequest(httpPost);
        if(response.getStatusLine().getStatusCode() == 201) {
            Log.i("ParkingLocationGateway", "Sending add favourite request complete");
            return true;
        }
        Log.i("ParkingLocationGateway", "Sending add favourite request failed");
        return false;
    }

    public Boolean delete(FavouriteLocation favouriteLocation) {
        Log.i("FavouritesGateway", "Sending delete favourite request");
        String url = buildDeleteFavouriteUrl(favouritesUrl, favouriteLocation);
        HttpDelete httpDelete = httpUtils.buildHttpDelete(url);
        HttpResponse response = httpUtils.makeRequest(httpDelete);
        if(response.getStatusLine().getStatusCode() == 204) {
            Log.i("ParkingLocationGateway", "Sending delete favourite request complete");
            return true;
        }
        Log.i("ParkingLocationGateway", "Sending delete favourite request failed");
        return false;
    }

    private String buildDeleteFavouriteUrl(String favouritesUrl, FavouriteLocation favouriteLocation) {
        return favouritesUrl + "?userId=" + favouriteLocation.getUserId() + "&locationId=" + favouriteLocation.getLocationId();
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
