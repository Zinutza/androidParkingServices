package com.example.zina.parkingandroidapp.gateway;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Zina on 03/07/2017.
 */

public class HttpUtils {

    private HttpClient httpClient;

    public HttpPost buildHttpPost(String url, String body) {
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body));
            post.setHeader("Content-Type", "application/json");
            return post;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public HttpGet buildHttpGet(String url) {
        return new HttpGet(url);
    }

    public HttpResponse makeRequest(HttpUriRequest request) {
        try {
            HttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == 404) {
               Log.i("HttpUtils", "404 From server : " + EntityUtils.toString(response.getEntity()));
            }
            return response;
        } catch (IOException e) {
            Log.i("HttpUtils", "IO Exception From server");
            throw new IllegalStateException(e);
        }
    }

   public String extractResponseBody(HttpResponse httpResponse) {
       try {
           return EntityUtils.toString(httpResponse.getEntity());
       } catch (IOException e) {
           throw new IllegalStateException(e);
       }
   }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


}
