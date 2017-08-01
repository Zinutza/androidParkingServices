package com.example.zina.parkingandroidapp.gateway;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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

    public HttpResponse makeRequest(HttpUriRequest request) {
        try {
            HttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == 404) {
                throw new IllegalStateException("404 :  Resource not found");
            }
            return response;
        } catch (IOException e) {
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
