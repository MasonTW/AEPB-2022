package com.example.AEPB.model;

public class ParkingResponse {
    private boolean result;
    private int carParkingNum;
    private String token;

    public ParkingResponse(boolean result, int carParkingNum, String token) {
        this.result = result;
        this.carParkingNum = carParkingNum;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public boolean getIsResult() {
        return result;
    }

    public int getCarParkingNum() {
        return this.carParkingNum;
    }
}
