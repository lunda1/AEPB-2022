package com.example.AEPB.parking.domain;

import com.example.AEPB.parking.Constants;
import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.RobotParkingBoy;
import com.example.AEPB.parking.domain.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RobotParkingBoyTest {
    @Test
    void should_park_success_when_park_given_a_car() throws ParkingOrPickingUpException {
        RobotParkingBoy parkingBoy = new RobotParkingBoy(Arrays.asList(new ParkingLot(0, 10), new ParkingLot(1, 5)));
        Car car = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(car);
        Ticket expectedTicket = new Ticket(car.getPlateNum(), 0);

        assertEquals(expectedTicket, ticket);

    }

    @Test
    void should_park_fail_when_park_given_a_null_car() throws ParkingOrPickingUpException {
        RobotParkingBoy parkingBoy = new RobotParkingBoy(Arrays.asList(new ParkingLot(0, 5), new ParkingLot(1, 10)));
        Car car = null;
        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingBoy.park(car));

        assertTrue(exception.getMessage().contains(Constants.ERROR_CAR_CANT_BE_NULL));

    }

    @Test
    void should_park_fail_when_park_given_a_blank_plateNum_car() throws ParkingOrPickingUpException {
        RobotParkingBoy parkingBoy = new RobotParkingBoy(Arrays.asList(new ParkingLot(0, 5), new ParkingLot(1, 10)));
        Car car = Car.builder().plateNum("").build();
        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingBoy.park(car));

        assertTrue(exception.getMessage().contains(Constants.ERROR_PLATE_NUM_CANT_BE_BLANK));

    }

    @Test
    void should_pick_up_fail_when_pick_up_given_a_ticket() {
        RobotParkingBoy parkingBoy = new RobotParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Ticket ticket = new Ticket("");

        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingBoy.pickUp(ticket));
        assertTrue(exception.getMessage().contains(Constants.ERROR_ROBOT_NOT_SUPPORT_PICKING_UP));
    }
}