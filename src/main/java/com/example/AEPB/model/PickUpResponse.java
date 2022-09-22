package com.example.AEPB.model;

public class PickUpResponse {
    private Boolean isSucceed;
    private String carCard;

    public PickUpResponse(Boolean isSucceed, String carCard) {
        this.isSucceed = isSucceed;
        this.carCard = carCard;
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
