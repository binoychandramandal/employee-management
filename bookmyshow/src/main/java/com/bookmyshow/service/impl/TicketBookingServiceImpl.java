package com.bookmyshow.service.impl;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.BookTicketRequestDto;
import com.bookmyshow.dto.TicketResponseDto;
import com.bookmyshow.entity.*;
import com.bookmyshow.exception.InvalidUserException;
import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.repository.ShowRepository;
import com.bookmyshow.repository.ShowSeatRepository;
import com.bookmyshow.repository.TicketRepository;
import com.bookmyshow.repository.UserRepository;
import com.bookmyshow.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {
    private final ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;

    private UserRepository userRepository;
    private ShowRepository showRepository;

    @Autowired
    public TicketBookingServiceImpl(ShowRepository showRepository,ShowSeatRepository showSeatRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketResponseDto bookTicket(BookTicketRequestDto requestDto) throws ShowSeatNotAvailableException, InvalidUserException {

        //1. Fetch show seats from DB
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdIn(requestDto.getShowSeatIds());

        //2. Check status of those seats.
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getState().equals(ShowSeatState.AVAILABLE)) {
                //3. if any of theme is not in AVAILABLE state throws exception.
                throw new ShowSeatNotAvailableException(showSeat.getId());
            }
        }

        //4. Take a lock
        //5. Again check if all are available
        for (ShowSeat showSeat : showSeats) {
            showSeat.setState(ShowSeatState.LOCKED);
            showSeatRepository.save(showSeat);
        }
        //6. if yes book
        Ticket ticket = new Ticket();
        Optional<User> userOptional = userRepository.findById(requestDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new InvalidUserException(requestDto.getUserId());
        }
        ticket.setBookedBy(userOptional.get());
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setShowSeats(showSeats);
        Show show= showRepository.findById(requestDto.getShowId()).get();
        ticket.setShow(show);
        final Map<SeatType,Double> seatTypes =new HashMap<>();
        show.getShowSeatTypes().forEach(x->seatTypes.put(x.getSeatType(),x.getPrice()));
        ticket.setTotalAmount(showSeats.stream().map(x->seatTypes.get(x.getSeat().getSeatType())).collect(Collectors.summingDouble((x)->x)));
        //7. throw exception
        return EntityVOConverter.convert(ticketRepository.save(ticket));
    }

}
