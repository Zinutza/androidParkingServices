package com.example.zina.parkingandroidapp.model;

/**
 * Created by Zina on 07/08/2017.
 */

public enum ParkingType {

    FREE, PAID;

    private boolean free;

    public boolean isFree() {
        return this.equals(FREE);
    }
}
