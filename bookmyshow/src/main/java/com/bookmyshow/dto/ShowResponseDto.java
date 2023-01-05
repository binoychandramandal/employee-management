package com.bookmyshow.dto;

import com.bookmyshow.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ShowResponseDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long auditoriumId;
    private Long movieId;
    private List<Feature> features;
    private List<Language> language;
    private AuditoriumResponseDto auditorium;
    private MovieResponseDto movie;
    private List<ShowSeatResponseDto> showSeats;
    private List<ShowSeatTypeResponseDto> showSeatTypes;
}
