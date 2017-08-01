package com.example.zina.parkingandroidapp.model;

import java.io.Serializable;

/**
 * Created by Zina on 19/06/2017.
 */

public class User implements Serializable {

    private Long id;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
