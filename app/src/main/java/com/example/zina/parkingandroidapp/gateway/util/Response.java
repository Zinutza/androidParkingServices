package com.example.zina.parkingandroidapp.gateway.util;

/**
 * Created by Zina on 10/10/2017.
 */

public class Response<T> {

    private T t;

    private String error;

    public Response(T t) {
        this.t = t;
    }

    public Response(String error) {
        this.error = error;
    }

    public boolean isEmpty() {
        return t == null;
    }

    public T getEntity() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
