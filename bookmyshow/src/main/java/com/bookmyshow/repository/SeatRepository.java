package com.bookmyshow.repository;

import com.bookmyshow.dto.SeatRequestDto;
import com.bookmyshow.dto.SeatResponseDto;
import com.bookmyshow.entity.Seat;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidSeatException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findAllByAuditoriumId(Long auditoriumId);
}
