package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieRequestDto {
    private Long id;
    private String name;
    private List<Language> languages;
    private List<Feature> features;
    private List<Genre> genres;
    private List<ActorRequestDto> actors;
}
