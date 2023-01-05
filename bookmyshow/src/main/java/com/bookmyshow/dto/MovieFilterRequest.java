package com.bookmyshow.dto;

import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MovieFilterRequest {
    private List<Long> ids;
    private Collection<Feature> features;
    private java.util.Collection<Genre> genres;
    private Collection<Language> languages;
}
