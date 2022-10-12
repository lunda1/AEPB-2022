package com.example.AEPB.parking.domain;

import com.example.AEPB.parking.Constants;
import com.example.AEPB.parking.domain.Car;
import com.example.AEPB.parking.domain.ParkingLot;
import com.example.AEPB.parking.ParkingOrPickingUpException;
import com.example.AEPB.parking.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class ParkingBoy {

    protected List<ParkingLot> parkingLots;

    protected Ticket park(Car car) throws ParkingOrPickingUpException {
        Ticket ticket;
        for (ParkingLot parkingLot : parkingLots) {
            ticket = parkingLot.park(car);
            if (ticket != null) {
                return ticket;
            }
        }

        return null;
    }

    protected Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        if (ticket == null) {
            throw new ParkingOrPickingUpException(Constants.ERROR_TICKET_CANT_BE_NULL);
        } else if (ticket.getLotNum() == null) {
            throw new ParkingOrPickingUpException(Constants.ERROR_LOT_NUM_CANT_BE_NULL);
        }

        ParkingLot parkingLot = this.parkingLots.stream()
                .filter(o -> o.getLotNum().equals(ticket.getLotNum()))
                .findFirst()
                .orElse(null);

        if (parkingLot == null) {
            throw new ParkingOrPickingUpException(Constants.ERROR_LOT_NUM_NOT_EXIST);
        }

        return parkingLot.pickUp(ticket);
    }

    protected void sortLotsOrderByRemainingDesc(){
        this.getParkingLots().sort(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.remainingSize() >= o2.remainingSize() ? -1 : 1;
            }
        });
    }

}
