package com.example.AEPB.parking.domain;

import com.example.AEPB.parking.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParkingLot {

    private Integer lotNum;

    private int size;

    private List<Car> carList;

    public ParkingLot(int size) {
        this.size = size;
        this.carList = new ArrayList<>(size);
    }

    public ParkingLot(Integer lotNum, int size) {
        this(size);
        this.lotNum = lotNum;
    }

    public Ticket park(Car car) throws ParkingOrPickingUpException {
        if (car == null) {
            throw new ParkingOrPickingUpException(Constants.ERROR_CAR_CANT_BE_NULL);
        } else if (Strings.isBlank(car.getPlateNum())) {
            throw new ParkingOrPickingUpException(Constants.ERROR_PLATE_NUM_CANT_BE_BLANK);
        }

        if (carList != null && carList.size() < size) {
            carList.add(car);
            return new Ticket(car.getPlateNum(), this.lotNum);
        }

        return null;
    }

    public Car pickUp(Ticket ticket) throws ParkingOrPickingUpException {
        if (ticket == null) {
            throw new ParkingOrPickingUpException(Constants.ERROR_TICKET_CANT_BE_NULL);
        } else if (Strings.isBlank(ticket.getPlateNum())) {
            throw new ParkingOrPickingUpException(Constants.ERROR_PLATE_NUM_CANT_BE_BLANK);
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
