package com.example.AEPB;

import java.util.HashMap;
import java.util.LinkedList;

public class ParkingBoy {
    private LinkedList<CarParking> parkingList = new LinkedList<>();

    public void addParking(CarParking carParking) {
        this.parkingList.add(carParking);
    }


    public int getCarParkingCount() {
        return this.parkingList.size();
    }

    public ParkingResponse carIn(String carCard) {
        var parkingNum = 0;
        CarInResult carInResult;
        var parkingMap = new HashMap<Integer, CarParking>();
        for (int i = 0; i < parkingList.size(); i++) {
            if (parkingList.get(i).carInRequest(carCard).getIsSucceed()) {
                parkingNum = i + 1;
                break;
            }
        }


        if (parkingNum == 0) {
            return new ParkingResponse(false, parkingNum);
        }
        return new ParkingResponse(true, parkingNum);

    }
}
