package com.example.zina.parkingandroidapp.model;

public class FavouriteLocation {

    private Long userId;

    private Long locationId;

    public FavouriteLocation(Long userId, Long locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
