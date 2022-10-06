package com.example.AEPB.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParkingLot {

    private int lotNum;

    private int size;

    private List<Car> carList;

    public ParkingLot(int lotNum, int size) {
        this.lotNum = lotNum;
        this.size = size;
        this.carList = new ArrayList<>(size);
    }

    public Ticket park(Car car) throws ParkingOrPickingUpException {
        if (car == null || Strings.isBlank(car.getPlateNum())) {
            throw new ParkingOrPickingUpException("invalid car");
        }

        if (carList != null && carList.size() < size) {
            carList.add(car);
            return new Ticket(car.getPlateNum(), this.lotNum);
        }

        return null;
    }

    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        if (ticket == null || Strings.isBlank(ticket.getPlateNum())) {
            throw new ParkingOrPickingUpException("invalid ticket");
        }

        return carList.stream()
                .filter(o -> o.getPlateNum().equals(ticket.getPlateNum()))
                .findFirst()
                .orElse(null);
    }

    public int remainingSize() {
        return this.size - this.carList.size();
    }

}
