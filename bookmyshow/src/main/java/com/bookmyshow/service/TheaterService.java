package com.bookmyshow.service;

import com.bookmyshow.dto.TheaterRequestDto;
import com.bookmyshow.dto.TheaterResponseDto;
import com.bookmyshow.entity.Theater;
import com.bookmyshow.exception.InvalidCityException;
import com.bookmyshow.exception.InvalidTheaterException;

import java.util.List;


public interface TheaterService extends BaseService<Theater, Long> {

    TheaterResponseDto saveTheater(TheaterRequestDto theaterRequestDto) throws InvalidCityException;

    List<TheaterResponseDto> getAllTheaterByCityId(Long cityId) throws InvalidCityException;

    TheaterResponseDto updateTheater(TheaterRequestDto theaterRequestDto) throws InvalidTheaterException;
}
