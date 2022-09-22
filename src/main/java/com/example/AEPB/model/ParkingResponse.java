package com.example.AEPB.model;

public class ParkingResponse {
    private boolean result;
    private int carParkingNum;

    public ParkingResponse(boolean result, int carParkingNum) {

        this.result = result;
        this.carParkingNum = carParkingNum;
    }

    public boolean getIsResult() {
        return result;
    }

    public int getCarParkingNum() {
        return this.carParkingNum;
    }
}
