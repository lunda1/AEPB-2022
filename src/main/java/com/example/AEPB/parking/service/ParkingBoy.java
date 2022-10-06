package com.example.AEPB.parking.service;

import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.domain.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public Ticket park(Car car) throws ParkingOrPickingUpException {
        Ticket ticket;
        for (ParkingLot parkingLot : parkingLots) {
            ticket = parkingLot.park(car);
            if (ticket != null) {
                return ticket;
            }
        }

        return null;
    }

    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        Car car;
        for (ParkingLot parkingLot : parkingLots) {
            car = parkingLot.pickUp(ticket);
            if (car != null) {
                return car;
            }
        }
        return null;
    }
}
