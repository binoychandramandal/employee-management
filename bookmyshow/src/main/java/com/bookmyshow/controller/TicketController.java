package com.bookmyshow.controller;

import com.bookmyshow.dto.BookTicketRequestDto;
import com.bookmyshow.dto.BookTicketResponseDto;
import com.bookmyshow.dto.ResponseStatus;
import com.bookmyshow.dto.TicketResponseDto;
import com.bookmyshow.entity.Ticket;
import com.bookmyshow.exception.InvalidUserException;
import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ticket")
public class TicketController {
    private final TicketBookingService ticketBookingService;

    @Autowired
    public TicketController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    @PostMapping
    public BookTicketResponseDto bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) throws ShowSeatNotAvailableException, InvalidUserException {
        TicketResponseDto ticket = ticketBookingService.bookTicket(bookTicketRequestDto);
        BookTicketResponseDto response = new BookTicketResponseDto();
        response.setTicket(ticket);
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus("done");
        response.setStatus(responseStatus);
        return response;
    }
}
