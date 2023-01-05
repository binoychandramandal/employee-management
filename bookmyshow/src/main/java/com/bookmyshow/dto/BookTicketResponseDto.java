package com.bookmyshow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookTicketResponseDto {
    private ResponseStatus status;
    private TicketResponseDto ticket;
}
