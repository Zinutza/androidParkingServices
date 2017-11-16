package com.example.zina.parkingandroidapp.services;

import com.example.zina.parkingandroidapp.gateway.RatingsGateway;


public class RatingsService {

    private RatingsGateway ratingsGateway;

    public void setRatingsGateway(RatingsGateway ratingsGateway) {
        this.ratingsGateway = ratingsGateway;
    }

    public Long readUserRating(Long locationId, Long userId) {
        return ratingsGateway.readUserRating(locationId, userId);
    }

    public Float readAverage(Long locationId) {
        return ratingsGateway.readAverage(locationId);
    }

    public void createOrUpdate(Long locationId, Long userId, float rating) {
        ratingsGateway.createOrUpdate(locationId, userId, rating);
    }
}
