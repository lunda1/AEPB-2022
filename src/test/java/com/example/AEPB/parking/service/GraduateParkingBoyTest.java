package com.example.AEPB.parking.service;

import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.domain.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {

    @Test
    void should_park_success_when_park_given_a_car_and_lot_0_has_space() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,10), new ParkingLot(1,5)));
        Car car = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(car);
        Ticket expectedTicket = new Ticket(car.getPlateNum(), 0);

        assertEquals(expectedTicket, ticket);

    }

    @Test
    void should_park_success_when_park_given_a_car_and_lot_0_full_and_lot_1_has_space() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,5)));
        Car holderCar = Car.builder().plateNum("京A XXXX").build();
        parkingBoy.park(holderCar);

        Car car = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(car);
        Ticket expectedTicket = new Ticket(car.getPlateNum(), 1);
        assertEquals(expectedTicket, ticket);

    }

    @Test
    void should_park_fail_when_given_a_car_and_all_lots_full() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Car holderCar1 = Car.builder().plateNum("京A XXXX1").build();
        parkingBoy.park(holderCar1);
        Car holderCar2 = Car.builder().plateNum("京A XXXX2").build();
        parkingBoy.park(holderCar2);

        Car car = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(car);
        assertEquals(null, ticket);
    }

    @Test
    void should_park_fail_when_given_an_invalid_car() {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Car car = Car.builder().plateNum("").build();

        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingBoy.park(car));
        assertTrue(exception.getMessage().contains("invalid car"));
    }

    @Test
    void should_pick_up_success_when_pick_up_given_a_ticket_and_car_in_lot_0() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Car expectedCar = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(expectedCar);

        Car car = parkingBoy.pickUp(ticket);
        assertEquals(expectedCar, car);
    }


    @Test
    void should_pick_up_success_when_pick_up_given_a_ticket_and_car_in_lot_1() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(0,1)));
        Car holderCar = Car.builder().plateNum("京A XXXX").build();
        parkingBoy.park(holderCar);
        Car expectedCar = Car.builder().plateNum("京A 8868").build();
        Ticket ticket = parkingBoy.park(expectedCar);

        Car car = parkingBoy.pickUp(ticket);
        assertEquals(expectedCar, car);
    }

    @Test
    void should_pick_up_fail_when_pick_up_given_an_invalid_ticket() {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Ticket ticket = new Ticket("");

        Exception exception = assertThrows(ParkingOrPickingUpException.class, () -> parkingBoy.pickUp(ticket));
        assertTrue(exception.getMessage().contains("invalid ticket"));
    }

    @Test
    void should_pick_up_fail_when_pick_up_given_a_ticket_and_car_not_in_all_lots() throws ParkingOrPickingUpException {
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(Arrays.asList(new ParkingLot(0,1), new ParkingLot(1,1)));
        Ticket ticket = new Ticket("京A 8868");

        Car car = parkingBoy.pickUp(ticket);
        assertEquals(null, car);
    }

}