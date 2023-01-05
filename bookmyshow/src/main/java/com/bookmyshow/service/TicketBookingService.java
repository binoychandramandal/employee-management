package com.bookmyshow.service;

import com.bookmyshow.dto.BookTicketRequestDto;
import com.bookmyshow.dto.TicketResponseDto;
import com.bookmyshow.entity.Ticket;
import com.bookmyshow.exception.InvalidUserException;
import com.bookmyshow.exception.ShowSeatNotAvailableException;

public interface TicketBookingService {

    TicketResponseDto bookTicket(BookTicketRequestDto requestDto) throws ShowSeatNotAvailableException, InvalidUserException;

}
