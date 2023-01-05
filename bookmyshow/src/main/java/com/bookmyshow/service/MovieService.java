package com.bookmyshow.service;

import com.bookmyshow.dto.MovieResponseDto;
import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieService extends BaseService<Movie,Long> {

    List<MovieResponseDto> findAllByLanguagesIn(Collection<Language> languages);

    List<MovieResponseDto> findAllByGenresIn(Collection<Genre> genres);

    List<MovieResponseDto> findAllByFeaturesIn(Collection<Feature> features);

    List<MovieResponseDto> findAllByActorsIdIn(List<Long> ids);

    List<MovieResponseDto> findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages);

    List<MovieResponseDto> findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages);
    List<MovieResponseDto> findAllByActorsNameIn(List<String> names);
}
