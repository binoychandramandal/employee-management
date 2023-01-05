package com.bookmyshow.service;

import com.bookmyshow.dto.AuditoriumRequestDto;
import com.bookmyshow.dto.AuditoriumResponseDto;
import com.bookmyshow.entity.Auditorium;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidTheaterException;

import java.util.List;

public interface AuditoriumService extends BaseService<Auditorium,Long> {

    AuditoriumResponseDto saveAuditorium(AuditoriumRequestDto auditoriumRequestDto) throws InvalidTheaterException;

    List<AuditoriumResponseDto> getAllAuditoriumByTheaterId(Long theaterId);

    AuditoriumResponseDto updateAuditorium(AuditoriumRequestDto auditoriumRequestDto) throws InvalidAuditoriumException;
}
