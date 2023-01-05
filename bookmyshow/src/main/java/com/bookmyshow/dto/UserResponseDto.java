package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Long phone;
    private String address;

}
