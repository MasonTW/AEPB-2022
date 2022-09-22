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

    public ParkingResponse carIn(String carCard) {
       var parking = parkingList.stream().filter( it -> it.carInRequest(carCard).getIsSucceed() ).findFirst();
       var parkingNum = 1;
        if (parking.isPresent()) {
            return new ParkingResponse(true, parkingNum);
        }else {
            return new ParkingResponse(false, parkingNum);
        }


    }
}
