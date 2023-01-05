package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookTicketRequestDto {
    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;
}
