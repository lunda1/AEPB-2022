package com.example.AEPB.parking.domain;

import com.example.AEPB.parking.ParkingOrPickingUpException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy{

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingOrPickingUpException {
        return super.park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        return super.pickUp(ticket);
    }
}
