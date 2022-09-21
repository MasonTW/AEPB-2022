package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ParkingBoyTest {
    @Test
    void should_add_car_parking_number_when_add_new_car_parking_succeed(){
        var parkingBoy = new ParkingBoy();
        var currentNum = parkingBoy.getCarParkingCount();
        var mockCarParking = mock(CarParking.class);

        parkingBoy.addParking(mockCarParking);

       assertEquals(currentNum + 1, parkingBoy.getCarParkingCount());
    }

}
