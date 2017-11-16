package com.example.zina.parkingandroidapp.gateway;

import android.os.StrictMode;
import android.util.Log;

import com.example.zina.parkingandroidapp.gateway.util.HttpUtils;
import com.example.zina.parkingandroidapp.gateway.util.JsonUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import java.util.Map;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;
import static com.example.zina.parkingandroidapp.gateway.util.GatewayUtils.buildServiceURL;

public class RatingsGateway {

    private String ratingsUrl;

    private static final String SERVICE_NAME = "ratings";

    private JsonUtils jsonUtils;
    private HttpUtils httpUtils;

    public RatingsGateway() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ratingsUrl = buildServiceURL(PROTOCOL, HOST, PORT, CONTEXT, SERVICE_NAME);
    }

    public Long readUserRating(Long locationId, Long userId) {
        Log.i("RatingsGateway", "Sending user rating request");
        HttpGet httpGet = httpUtils.buildHttpGet(ratingsUrl + "?locationId=" + locationId +"&userId=" + userId);
        HttpResponse response = httpUtils.makeRequest(httpGet);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("RatingsGateway", "user rating request completed successfully");
            return Long.valueOf(responseBody);
        } else {
            Log.i("RatingsGateway", "user rating request failed");
            return new Long(0);
        }
    }

    public Float readAverage(Long locationId) {
        Log.i("RatingsGateway", "Sending average rating request");
        HttpGet httpGet = httpUtils.buildHttpGet(ratingsUrl + "/" + locationId + "/average");
        HttpResponse response = httpUtils.makeRequest(httpGet);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("RatingsGateway", "average rating request completed successfully");
            return Float.valueOf(responseBody);
        } else {
            Log.i("RatingsGateway", "average rating request failed");
            return new Float(0);
        }
    }

    public void createOrUpdate(Long locationId, Long userId, float rating) {
        Log.i("RatingsGateway", "Sending create rating request");

        Map payload = new HashMap<String, Object>();
        payload.put("locationId", locationId);
        payload.put("userId", userId);
        payload.put("rating", rating);

        String body = jsonUtils.convertObjectToString(payload);
        HttpPost httpPost = httpUtils.buildHttpPost(ratingsUrl, body);
        HttpResponse response = httpUtils.makeRequest(httpPost);
        if(response.getStatusLine().getStatusCode() == 200) {
            String responseBody = httpUtils.extractResponseBody(response);
            Log.i("RatingsGateway", "create rating request completed successfully");
        }
    }

    public void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }
}
