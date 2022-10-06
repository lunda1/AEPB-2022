package com.example.AEPB.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private String plateNum;
    private Integer lotNum;

    public Ticket(String plateNum) {
        this.plateNum = plateNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return lotNum == ticket.lotNum && Objects.equals(plateNum, ticket.plateNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNum, lotNum);
    }
}
