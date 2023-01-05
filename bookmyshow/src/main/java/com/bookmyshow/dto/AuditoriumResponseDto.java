package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuditoriumResponseDto {
    private Long id;
    private String name;
    private Long theaterId;
    private List<Feature> features;
    private TheaterResponseDto theater;
}
