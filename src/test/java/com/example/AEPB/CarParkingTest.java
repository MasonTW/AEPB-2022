package com.example.AEPB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarParkingTest {

    @Test
    void should_return_true_result_when_car_in_success(){
        CarParking carParking = new CarParking(9);
        String carCard = "陕A12345";

        CarInResult carInResult = carParking.carInRequest(carCard);

        Assertions.assertTrue(carInResult.getIsSucceed());
    }

    @Test
    void should_return_false_result_when_car_in_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String carCard2 = "陕B12345";

        carParking.carInRequest(carCard1);
        CarInResult carInResult = carParking.carInRequest(carCard2);

        Assertions.assertFalse(carInResult.getIsSucceed());
    }

    @Test
    void should_return_remind_info_in_result_when_car_in_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String carCard2 = "陕B12345";

        carParking.carInRequest(carCard1);
        CarInResult carInResult = carParking.carInRequest(carCard2);

        Assertions.assertEquals(CarParking.UNABLE_TO_PARK_BY_PARKING_IS_FULL, carInResult.getToken());
    }

    @Test
    void should_return_car_card_when_car_out_succeed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        String token = "testToken";
        carParking.setCarParkingMap(token, carCard1);

        String actual = carParking.carOutRequest(token);

        Assertions.assertEquals(carCard1, actual);
    }

    @Test
    void should_return_invalid_token_when_car_out_failed(){
        CarParking carParking = new CarParking(1);
        String carCard1 = "陕A12345";
        CarInResult carInResult = carParking.carInRequest(carCard1);

        String actual = carParking.carOutRequest(carInResult.getToken());

        Assertions.assertEquals(CarParking.INVALID_TOKEN, actual);
    }

}
