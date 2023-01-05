package com.bookmyshow.service;

import com.bookmyshow.dto.ShowRequestDto;
import com.bookmyshow.dto.ShowResponseDto;
import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.Show;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidMovieException;

import java.util.List;

public interface ShowService extends BaseService<Show, Long> {
    ShowResponseDto createShow(ShowRequestDto showRequestDto) throws InvalidMovieException, InvalidAuditoriumException;

    ShowResponseDto updateShow(ShowRequestDto showRequestDto);

    List<ShowResponseDto> findAllByAuditoriumId(Long auditoriumId);

    List<ShowResponseDto> findAllByMovieId(Long movieId);
    List<ShowResponseDto> findAllByMovieId_CityId_(Long movieId,Long cityId);
    List<ShowResponseDto> findAllByMovieId_LanguageIn_CityId_(Long movieId, List<Language> languages,Long cityId);
}
