package com.bookmyshow.dto;

import com.bookmyshow.entity.ShowSeatState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ShowSeatResponseDto {
    private Long id;
    private ShowResponseDto show;
    private SeatResponseDto seat;
    private ShowSeatState state;
}
