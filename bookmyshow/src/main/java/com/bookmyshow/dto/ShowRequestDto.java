package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.SeatStatus;
import com.bookmyshow.entity.SeatType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ShowRequestDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long auditoriumId;
    private Long movieId;
    private List<Feature> features;
    private Map<SeatType, Integer> seatPricing;
    private List<Language> language;
}
