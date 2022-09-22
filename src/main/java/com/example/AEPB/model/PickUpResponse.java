package com.example.AEPB.model;

public class PickUpResponse {
    private Boolean isSucceed;
    private String carCard;
    private int carParkingNum;

    public PickUpResponse(Boolean isSucceed, String carCard, int carParkingNum) {
        this.isSucceed = isSucceed;
        this.carCard = carCard;
        this.carParkingNum = carParkingNum;
    }

    public int getCarParkingNum() {
        return carParkingNum;
    }

    public void setCarParkingNum(int carParkingNum) {
        this.carParkingNum = carParkingNum;
    }

    public boolean getIsSucceed() {
        return this.isSucceed;
    }

    public String getCarCard() {
        return carCard;
    }

    public void setSucceed(Boolean succeed) {
        isSucceed = succeed;
    }

    public void setCarCard(String carCard) {
        this.carCard = carCard;
    }
}
