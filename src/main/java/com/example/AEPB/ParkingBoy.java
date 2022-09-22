package com.example.AEPB;

import com.example.AEPB.model.CarInResult;
import com.example.AEPB.model.ParkingResponse;
import com.example.AEPB.model.PickUpResponse;

import java.util.LinkedList;

import static com.example.AEPB.CarParking.INVALID_TOKEN;

public class ParkingBoy {
    public static final String NO_THIS_CARD = "No this card";
    private LinkedList<CarParking> parkingList = new LinkedList<>();

    public void addParking(CarParking carParking) {
        this.parkingList.add(carParking);
    }


    public int getCarParkingCount() {
        return this.parkingList.size();
    }

    public ParkingResponse carIn(String carCard) {
        ParkingResponse parkingResponse = new ParkingResponse(false, 0, INVALID_TOKEN);
        CarInResult carInResult;
        for (int i = 0; i < parkingList.size(); i++) {
            carInResult = parkingList.get(i).carInRequest(carCard);
            if (carInResult.getIsSucceed()) {
                parkingResponse = new ParkingResponse(carInResult.getIsSucceed(), i + 1, carInResult.getToken());
                break;
            }
        }
        return parkingResponse;
    }

    public PickUpResponse carOut(String token) {
        var pickUpResponse = new PickUpResponse(false, NO_THIS_CARD);
        for (int i = 0; i < parkingList.size(); i++) {
            String carCard = parkingList.get(i).carOutRequest(token);
            if (!carCard.equals(INVALID_TOKEN)) {
                pickUpResponse.setSucceed(true);
                pickUpResponse.setCarCard(carCard);
            }
        }
        return pickUpResponse;
    }
}
