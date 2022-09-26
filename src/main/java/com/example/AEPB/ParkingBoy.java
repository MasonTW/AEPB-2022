package com.example.AEPB;

import com.example.AEPB.model.ParkingResponse;
import com.example.AEPB.model.PickUpResponse;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static com.example.AEPB.CarParking.INVALID_TOKEN;

public class ParkingBoy {
    public static final int NO_AVAILABLE_PARKING_CODE = -1;
    private final LinkedList<CarParking> parkingList = new LinkedList<>();

    public void addParking(CarParking carParking) {
        this.parkingList.add(carParking);
    }


    public int getCarParkingCount() {
        return this.parkingList.size();
    }

    public ParkingResponse carIn(String carCard) {
        final Integer availableCarParkingNum = IntStream.range(0, parkingList.size())
                .boxed()
                .filter(it -> parkingList.get(it).checkParingState().isAvailable())
                .max(Comparator.comparing(it -> parkingList.get(it).checkParingState().getAvailableCount()))
                .orElse(NO_AVAILABLE_PARKING_CODE);

        return requestParkingResponse(carCard, availableCarParkingNum);
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
                        new PickUpResponse(false, INVALID_TOKEN, 0)
                );
    }

    private ParkingResponse requestParkingResponse(String carCard, Integer parkingNum) {
        if (parkingNum == NO_AVAILABLE_PARKING_CODE) {
            return new ParkingResponse(false, -1, INVALID_TOKEN);
        }
        var parkResult = parkingList.get(parkingNum).carInRequest(carCard);
        return new ParkingResponse(parkResult.getIsSucceed(), parkingNum + 1, parkResult.getToken());
    }
}
