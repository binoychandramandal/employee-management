package com.bookmyshow.dto;

import com.bookmyshow.entity.SeatType;
import com.bookmyshow.entity.ShowSeatState;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShowSeatTypeRequestDto {
    private ShowRequestDto show;
    private SeatType seatType;
    private  double price;
}
