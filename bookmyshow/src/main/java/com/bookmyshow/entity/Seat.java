package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String name;
    @Column(name = "rnum")
    private int row;
    @Column(name = "clmns")
    private int column;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus status;
    @ManyToOne
    private Auditorium auditorium;
}
