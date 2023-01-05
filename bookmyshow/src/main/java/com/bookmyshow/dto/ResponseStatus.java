package com.bookmyshow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ResponseStatus {
    private String status;
    private String message;
}
