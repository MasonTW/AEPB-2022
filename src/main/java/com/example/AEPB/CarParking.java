package com.example.AEPB;

import com.example.AEPB.model.CarInResult;

import java.util.HashMap;
import java.util.UUID;

public class CarParking {
    public static final String UNABLE_TO_PARK_BY_PARKING_IS_FULL = "Unable to park by parking is full";
    public static final String INVALID_TOKEN = "invalid token";
    private final int capacity;
    private final HashMap<String, String> carParkingMap = new HashMap<>(0);

    public CarParking(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentCarCount() {
        return this.carParkingMap.size();
    }

    public CarInResult carInRequest(String carCard) {
        if (getCurrentCarCount() + 1 <= capacity) {
            String token = generateToken();
            setCarParkingMap(carCard, token);
            return new CarInResult(true, token);
        } else {
            return new CarInResult(false, UNABLE_TO_PARK_BY_PARKING_IS_FULL);
        }
    }

    public void setCarParkingMap(String token, String carCard) {
        carParkingMap.put(token, carCard);
    }

    public String carOutRequest(String token) {
        var carOutResult = carParkingMap.get(token);
        if (carOutResult != null) {
            carParkingMap.remove(token);
            return carOutResult;
        } else {
            return INVALID_TOKEN;
        }
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
