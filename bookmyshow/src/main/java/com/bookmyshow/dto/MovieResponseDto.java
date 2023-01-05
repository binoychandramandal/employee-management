package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MovieResponseDto {
    private Long id;
    private String name;
    private List<Language> languages;
    private List<Feature> features;
    private List<Genre> genres;
    private List<ActorResponseDto> actors;
}
