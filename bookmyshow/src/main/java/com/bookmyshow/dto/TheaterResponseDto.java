package com.bookmyshow.dto;

import com.bookmyshow.entity.City;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TheaterResponseDto {
    private Long id;
    private Long cityId;
    private String name;
    private String address;
    private CityResponseDto city;

}
