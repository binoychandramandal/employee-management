package com.bookmyshow.service.impl;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.MovieResponseDto;
import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.Movie;
import com.bookmyshow.repository.MovieRepository;
import com.bookmyshow.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long> implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        setJpaRepository(movieRepository);
    }

    @Override
    public List<MovieResponseDto> findAllByLanguagesIn(Collection<Language> languages) {
        return movieRepository.findAllByLanguagesIn(languages).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> findAllByGenresIn(Collection<Genre> genres) {
        return movieRepository.findAllByGenresIn(genres).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> findAllByFeaturesIn(Collection<Feature> features) {
        return movieRepository.findAllByFeaturesIn(features).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> findAllByActorsIdIn(List<Long> ids) {
        return movieRepository.findAllByActorsIdIn(ids).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages) {
        return movieRepository.findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(ids, features, genres, languages).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }
    @Override
    public List<MovieResponseDto> findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages) {
        return movieRepository.findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(ids, features, genres, languages).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> findAllByActorsNameIn(List<String> names) {
        return movieRepository.findAllByActorsNameIn(names).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }
}
