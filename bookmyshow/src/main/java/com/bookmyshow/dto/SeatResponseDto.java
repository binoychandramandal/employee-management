package com.bookmyshow.dto;

import com.bookmyshow.entity.SeatStatus;
import com.bookmyshow.entity.SeatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SeatResponseDto {
    private Long id;
    private Long auditoriumId;
    private String name;
    private int row;
    private int column;
    private SeatType seatType;
    private SeatStatus status;
}
