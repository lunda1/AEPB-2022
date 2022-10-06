package com.example.AEPB.parking.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_park_success_when_park_given_a_car_and_lot_has_space() throws ParkingOrPickingUpException {
        Car car = Car.builder().plateNum("京A 8868").build();
        ParkingLot parkingLot = new ParkingLot(0,10);

        Ticket expectedTicket = new Ticket(car.getPlateNum());
        Ticket ticket = parkingLot.park(car);

        assertEquals(expectedTicket, ticket);
    }

    @Test
    void should_park_fail_when_park_given_a_car_and_lot_is_full() throws ParkingOrPickingUpException {
        Car holderCar = Car.builder().plateNum("京A XXXX").build();
        ParkingLot parkingLot = new ParkingLot(0,1);
        parkingLot.park(holderCar);

        Car car = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingLot.park(car);
        assertEquals(null, ticket);
    }

    @Test
    void should_pick_up_success_when_pick_up_given_a_ticket() throws ParkingOrPickingUpException {
        Car expectedCar = Car.builder().plateNum("京A 8868").build();
        ParkingLot parkingLot = new ParkingLot(0,10);
        Ticket ticket = parkingLot.park(expectedCar);

        Car car = parkingLot.pickUp(ticket);
        assertEquals(expectedCar, car);
    }

    @Test
    void should_pick_up_fail_when_pick_up_given_a_ticket_and_not_found() throws ParkingOrPickingUpException {
        ParkingLot parkingLot = new ParkingLot(0,10);
        Ticket ticket = new Ticket("京B XXXX");

        Car car = parkingLot.pickUp(ticket);
        assertEquals(null, car);

    }

    @Test
    void should_pick_up_fail_when_pick_up_given_an_blank_plate_num_ticket() {
        ParkingLot parkingLot = new ParkingLot(0,10);
        Ticket ticket = new Ticket("");

        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingLot.pickUp(ticket));

        assertTrue(exception.getMessage().contains("invalid ticket"));

    }

    @Test
    void should_pick_up_fail_when_pick_up_given_an_null_ticket() {
        ParkingLot parkingLot = new ParkingLot(0,10);
        Ticket ticket = null;

        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingLot.pickUp(ticket));

        assertTrue(exception.getMessage().contains("invalid ticket"));

    }
}