package com.example.AEPB;

import com.example.AEPB.model.CarInResult;
import com.example.AEPB.model.CarParkingState;
import com.example.AEPB.model.ParkingResponse;
import com.example.AEPB.model.PickUpResponse;
import org.junit.jupiter.api.Test;
import static com.example.AEPB.CarParking.INVALID_TOKEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {

    @Test
    void should_add_car_parking_count_when_add_new_car_parking_succeed() {
        var parkingBoy = new ParkingBoy();
        var currentNum = parkingBoy.getCarParkingCount();
        var mockCarParking = mock(CarParking.class);

        parkingBoy.addParking(mockCarParking);

        assertEquals(currentNum + 1, parkingBoy.getCarParkingCount());
    }

    @Test
    void should_make_car_in_first_parking_when_all_parking_has_available_same_space() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        var mockCarParkingNo2 = mock(CarParking.class);
        when(mockCarParkingNo1.checkParingState()).thenReturn(new CarParkingState(true, 3));
        when(mockCarParkingNo2.checkParingState()).thenReturn(new CarParkingState(true, 3));
        when(mockCarParkingNo1.carInRequest("test1"))
                .thenReturn(new CarInResult(true, "token1"));
        when(mockCarParkingNo2.carInRequest("test1"))
                .thenReturn(new CarInResult(true, "token2"));
        parkingBoy.addParking(mockCarParkingNo1);
        parkingBoy.addParking(mockCarParkingNo2);

        ParkingResponse parkingResponse = parkingBoy.carIn("test1");

        assertTrue(parkingResponse.getIsResult());
        assertEquals(1,parkingResponse.getCarParkingNum());
        assertEquals("token1",parkingResponse.getToken());
    }

    @Test
    void should_make_car_in_second_parking_when_available_space_in_second_more_than_first() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        var mockCarParkingNo2 = mock(CarParking.class);
        when(mockCarParkingNo1.checkParingState()).thenReturn(new CarParkingState(true, 1));
        when(mockCarParkingNo2.checkParingState()).thenReturn(new CarParkingState(true, 3));
        when(mockCarParkingNo1.carInRequest("test1"))
                .thenReturn(new CarInResult(true, "token1"));
        when(mockCarParkingNo2.carInRequest("test1"))
                .thenReturn(new CarInResult(true, "token2"));
        parkingBoy.addParking(mockCarParkingNo1);
        parkingBoy.addParking(mockCarParkingNo2);

        ParkingResponse parkingResponse = parkingBoy.carIn("test1");

        assertTrue(parkingResponse.getIsResult());
        assertEquals(2,parkingResponse.getCarParkingNum());
        assertEquals("token2",parkingResponse.getToken());
    }


    @Test
    void should_return_minus_1_when_all_parking_not_available() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        var mockCarParkingNo2 = mock(CarParking.class);
        when(mockCarParkingNo1.checkParingState()).thenReturn(new CarParkingState(false, 0));
        when(mockCarParkingNo2.checkParingState()).thenReturn(new CarParkingState(false, 0));
        when(mockCarParkingNo1.carInRequest("test1"))
                .thenReturn(new CarInResult(false, "token"));
        when(mockCarParkingNo2.carInRequest("test1"))
                .thenReturn(new CarInResult(false, "token"));
        parkingBoy.addParking(mockCarParkingNo1);
        parkingBoy.addParking(mockCarParkingNo2);

        ParkingResponse parkingResponse = parkingBoy.carIn("test1");

        assertFalse(parkingResponse.getIsResult());
        assertEquals(parkingResponse.getCarParkingNum(),-1);
        assertEquals(INVALID_TOKEN, parkingResponse.getToken());
    }

    @Test
    void should_return_true_when_pick_up_succeed() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        when(mockCarParkingNo1.carOutRequest("test1"))
                .thenReturn("陕A2233");
        parkingBoy.addParking(mockCarParkingNo1);

        PickUpResponse pickUpResponse = parkingBoy.carOut("test1");

        assertTrue(pickUpResponse.getIsSucceed());
    }

    @Test
    void should_return_false_when_pick_up_with_fake_token() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        when(mockCarParkingNo1.carOutRequest("fakeToken"))
                .thenReturn(INVALID_TOKEN);
        parkingBoy.addParking(mockCarParkingNo1);

        PickUpResponse pickUpResponse = parkingBoy.carOut("fakeToken");

        assertFalse(pickUpResponse.getIsSucceed());
    }

    @Test
    void should_return_correct_car_card_when_car_out_succeed() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        when(mockCarParkingNo1.carOutRequest("token"))
                .thenReturn("陕a8888");
        parkingBoy.addParking(mockCarParkingNo1);

        PickUpResponse pickUpResponse = parkingBoy.carOut("token");

        assertEquals("陕a8888",pickUpResponse.getCarCard());
    }

    @Test
    void should_return_correct_parking_num_when_car_out_succeed() {
        var parkingBoy = new ParkingBoy();
        var mockCarParkingNo1 = mock(CarParking.class);
        var mockCarParkingNo2 = mock(CarParking.class);
        when(mockCarParkingNo1.carOutRequest("token2"))
                .thenReturn(INVALID_TOKEN);
        when(mockCarParkingNo2.carOutRequest("token2"))
                .thenReturn("陕a8888");
        parkingBoy.addParking(mockCarParkingNo1);
        parkingBoy.addParking(mockCarParkingNo2);

        PickUpResponse pickUpResponse = parkingBoy.carOut("token2");

        assertEquals(2,pickUpResponse.getCarParkingNum());
    }

}
