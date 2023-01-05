package com.bookmyshow.service;

import com.bookmyshow.dto.SeatRequestDto;
import com.bookmyshow.dto.SeatResponseDto;
import com.bookmyshow.dto.TheaterRequestDto;
import com.bookmyshow.dto.TheaterResponseDto;
import com.bookmyshow.entity.Seat;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidCityException;
import com.bookmyshow.exception.InvalidSeatException;
import com.bookmyshow.exception.InvalidTheaterException;

import java.util.List;

public interface SeatService extends BaseService<Seat, Long> {

    List<SeatResponseDto> findAllByAuditoriumId(Long auditoriumId);

    SeatResponseDto saveSeat(SeatRequestDto seatRequestDto) throws InvalidAuditoriumException;


    SeatResponseDto updateSeat(SeatRequestDto seatRequestDto) throws InvalidSeatException;

}
