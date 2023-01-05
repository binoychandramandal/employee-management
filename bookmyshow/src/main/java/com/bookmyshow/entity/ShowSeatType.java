package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "show_seattype_mapping")
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private  SeatType seatType;
    private  double price;
}
