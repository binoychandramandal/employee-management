package com.bookmyshow.dto;

import com.bookmyshow.entity.TicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketResponseDto {
    private  Long id;
    private UserResponseDto bookedBy;
    private ShowResponseDto show;
    private List<ShowSeatResponseDto> showSeats;
    private double totalAmount;
    private TicketStatus ticketStatus;
    private Date timeOfBooking;
    //private List<Payment> payments;
}
