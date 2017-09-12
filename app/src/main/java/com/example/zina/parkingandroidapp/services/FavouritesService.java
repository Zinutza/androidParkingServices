package com.example.zina.parkingandroidapp.services;

import com.example.zina.parkingandroidapp.gateway.FavouritesGateway;
import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;

import java.util.List;


public class FavouritesService {

    private FavouritesGateway favouritesGateway;

    public List<ParkingLocation> list(User user) {
        return favouritesGateway.list(user);
    }

    public void setFavouritesGateway(FavouritesGateway favouritesGateway) {
        this.favouritesGateway = favouritesGateway;
    }
}
