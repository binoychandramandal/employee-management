package com.bookmyshow.repository;

import com.bookmyshow.entity.ShowSeat;
import com.bookmyshow.entity.ShowSeatState;
import com.bookmyshow.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
   Ticket save(Ticket ticket);

}
