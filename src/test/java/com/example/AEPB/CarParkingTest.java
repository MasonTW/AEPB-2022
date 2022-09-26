package com.example.AEPB;

import com.example.AEPB.model.CarInResult;
import com.example.AEPB.model.CarParkingState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarParkingTest {

    @Test
    void should_return_true_result_when_car_in_success(){
        CarParking carParking = new CarParking(9);
        String carCard = "陕A12345";

        CarInResult carInResult = carParking.carInRequest(carCard);

        assertTrue(carInResult.getIsSucceed());
    }

    @Test
    void should_return_false_result_when_car_in_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String carCard2 = "陕B12345";

        carParking.carInRequest(carCard1);
        CarInResult carInResult = carParking.carInRequest(carCard2);

        assertFalse(carInResult.getIsSucceed());
    }

    @Test
    void should_return_remind_info_in_result_when_car_in_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String carCard2 = "陕B12345";

        carParking.carInRequest(carCard1);
        CarInResult carInResult = carParking.carInRequest(carCard2);

        assertEquals(CarParking.UNABLE_TO_PARK_BY_PARKING_IS_FULL, carInResult.getToken());
    }

    @Test
    void should_return_car_card_when_car_out_succeed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String token = "testToken";
        carParking.setCarParkingMap(token, carCard1);

        String actual = carParking.carOutRequest(token);

        assertEquals(carCard1, actual);
    }

    @Test
    void should_return_invalid_token_when_car_out_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        CarInResult carInResult = carParking.carInRequest(carCard1);

        String actual = carParking.carOutRequest(carInResult.getToken());

        assertEquals(CarParking.INVALID_TOKEN, actual);
    }

    @Test
    void should_return_available_state_when_car_parking_is_available(){
        CarParking carParking = new CarParking(3);
        String carCard1 = "陕A12345";
        CarInResult carInResult = carParking.carInRequest(carCard1);

        CarParkingState actualState = carParking.checkParingState();

        assertTrue(actualState.isAvailable());
        assertEquals(2,actualState.getAvailableCount());
    }

    @Test
    void should_return_not_available_state_when_car_parking_is_not_available(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        CarInResult carInResult = carParking.carInRequest(carCard1);

        CarParkingState actualState = carParking.checkParingState();

        assertFalse(actualState.isAvailable());
        assertEquals(0,actualState.getAvailableCount());
    }

}
