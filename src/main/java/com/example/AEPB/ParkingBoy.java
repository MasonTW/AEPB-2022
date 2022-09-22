package com.example.AEPB;

import com.example.AEPB.model.CarInResult;
import com.example.AEPB.model.ParkingResponse;
import java.util.LinkedList;
import static com.example.AEPB.CarParking.INVALID_TOKEN;

public class ParkingBoy {
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
}
