package com.bookmyshow.dto;

import com.bookmyshow.entity.ShowSeatState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShowSeatRequestDto {
    private Long showId;
    private Long seatId;
    private ShowSeatState state;
}
