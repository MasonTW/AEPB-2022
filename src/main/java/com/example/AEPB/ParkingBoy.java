package com.example.AEPB;

import com.example.AEPB.model.ParkingResponse;
import com.example.AEPB.model.PickUpResponse;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static com.example.AEPB.CarParking.INVALID_TOKEN;

public class ParkingBoy {
    public static final String NO_THIS_CARD = "No this card";
    public static final int NO_CARD_CODE = -1;
    private LinkedList<CarParking> parkingList = new LinkedList<>();

    public void addParking(CarParking carParking) {
        this.parkingList.add(carParking);
    }


    public int getCarParkingCount() {
        return this.parkingList.size();
    }

    public ParkingResponse carIn(String carCard) {
        return IntStream.range(0, parkingList.size())
                .boxed()
                .map(it -> requestParkingResponse(carCard, it))
                .filter(ParkingResponse::getIsResult)
                .findFirst()
                .orElse(new ParkingResponse(false, 0, INVALID_TOKEN));
    }

    public PickUpResponse carOut(String token) {
        return IntStream.range(0, parkingList.size())
                .boxed()
                .map(it -> {
                    var pickUpResult = parkingList.get(it).carOutRequest(token);
                    var isPickUpSucceed = !pickUpResult.equals(INVALID_TOKEN);
                    var carParkingNum = isPickUpSucceed ? it + 1 : 0;
                    return new PickUpResponse(isPickUpSucceed, pickUpResult, carParkingNum);
                }).filter(
                        PickUpResponse::getIsSucceed
                ).findFirst().orElse(
                        new PickUpResponse(false,INVALID_TOKEN,0)
                );
    }

    private ParkingResponse requestParkingResponse(String carCard, Integer it) {
        var parkResult = parkingList.get(it).carInRequest(carCard);
        return new ParkingResponse(parkResult.getIsSucceed(), it + 1, parkResult.getToken());
    }
}
