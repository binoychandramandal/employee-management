package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuditoriumRequestDto {
    private Long id;
    private Long theaterId;
    private String name;
    private List<Feature> features;
}
