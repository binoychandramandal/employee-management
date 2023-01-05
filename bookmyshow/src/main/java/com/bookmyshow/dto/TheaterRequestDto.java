package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TheaterRequestDto {
    private Long id;
    private Long cityId;
    private String name;
    private String address;

}
