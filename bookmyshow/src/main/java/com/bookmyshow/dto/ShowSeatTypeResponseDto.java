package com.bookmyshow.dto;

import com.bookmyshow.entity.SeatType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShowSeatTypeResponseDto {
    private SeatType seatType;
    private  double price;
}
