package com.example.zina.parkingandroidapp.services;

import com.example.zina.parkingandroidapp.gateway.ParkingLocationGateway;
import com.example.zina.parkingandroidapp.model.ParkingLocation;

import java.util.ArrayList;

/**
 * Created by Zina on 07/08/2017.
 */

public class ParkingLocationServices {

    private ParkingLocationGateway parkingLocationGateway;

    public void setParkingLocationGateway(ParkingLocationGateway parkingLocationGateway) {
        this.parkingLocationGateway = parkingLocationGateway;
    }

    public ArrayList<ParkingLocation> findParkingNearby(double latitude, double longitude) {
        return parkingLocationGateway.findParkingNearby(latitude, longitude);
    }

    public ParkingLocation createParkingLocation(ParkingLocation parkingLocation) {
        return parkingLocationGateway.create(parkingLocation);
    }

    public Boolean delete(Long parkingLocationId) {
        return parkingLocationGateway.delete(parkingLocationId);
    }
}
