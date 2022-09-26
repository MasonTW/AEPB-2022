package com.example.AEPB.model;

public class PickUpResponse {
    private final Boolean isSucceed;
    private final String carCard;
    private final int carParkingNum;

    public PickUpResponse(Boolean isSucceed, String carCard, int carParkingNum) {
        this.isSucceed = isSucceed;
        this.carCard = carCard;
        this.carParkingNum = carParkingNum;
    }

    public int getCarParkingNum() {
        return carParkingNum;
    }

    public boolean getIsSucceed() {
        return this.isSucceed;
    }

    public String getCarCard() {
        return carCard;
    }
}
