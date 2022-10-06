package com.example.AEPB.parking.service;

import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.domain.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.Ticket;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingOrPickingUpException {
        this.getParkingLots().sort(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.remainingSize() >= o2.remainingSize() ? -1 : 1;
            }
        });

        return super.park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        return super.pickUp(ticket);
    }
}
