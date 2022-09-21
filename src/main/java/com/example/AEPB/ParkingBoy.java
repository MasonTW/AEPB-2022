package com.example.AEPB;

import java.util.LinkedList;

public class ParkingBoy {
    private LinkedList<CarParking> parkingList = new LinkedList<>();

    public void addParking(CarParking carParking) {
        this.parkingList.add(carParking);
    }


    public int getCarParkingCount() {
        return this.parkingList.size();
    }
}
