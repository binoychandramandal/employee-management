package com.bookmyshow.dto;

import com.bookmyshow.entity.SeatStatus;
import com.bookmyshow.entity.SeatType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatRequestDto {
    private Long id;
    private Long auditoriumId;
    private String name;
    private int row;
    private int column;
    private SeatType seatType;
    private SeatStatus status;
}
