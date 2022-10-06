package com.example.AEPB.parking.service;

import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.domain.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.Ticket;

import java.util.List;

public class RobotParkingBoy extends ParkingBoy{

    public RobotParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingOrPickingUpException {
        return super.park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        throw new ParkingOrPickingUpException("RobotParkingBoy don't support picking up cars");
    }
}
