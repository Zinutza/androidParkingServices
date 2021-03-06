package com.example.zina.parkingandroidapp.model;

import java.io.Serializable;

/**
 * Created by Zina on 07/08/2017.
 */

public class ParkingLocation implements Serializable {

    private Long id;

    private Double latitude;

    private Double longitude;

    private ParkingType type;

    private String address;

    private Long creatorId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ParkingType getType() {
        return type;
    }

    public void setType(ParkingType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return address;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
