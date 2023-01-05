package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.MovieFilterRequest;
import com.bookmyshow.dto.MovieRequestDto;
import com.bookmyshow.dto.MovieResponseDto;
import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public MovieResponseDto save(@RequestBody MovieRequestDto movieRequestDto) {
        return convert(movieService.add(convert(movieRequestDto)));
    }

    @PutMapping
    public MovieResponseDto update(@RequestBody MovieRequestDto movieRequestDto) {
        return convert(movieService.update(convert(movieRequestDto)));
    }

    @GetMapping(path = "/{id}")
    public MovieResponseDto getById(@PathVariable("id") Long id) {
        return convert(movieService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return movieService.delete(id);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @RequestMapping(path = "/by/languages", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByLanguage(@RequestBody Collection<Language> languages) {
        return movieService.findAllByLanguagesIn(languages);
    }

    @RequestMapping(path = "/by/genres", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByGenres(@RequestBody Collection<Genre> genres) {
        return movieService.findAllByGenresIn(genres);
    }

    @RequestMapping(path = "/by/features", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByFeatures(@RequestBody Collection<Feature> features) {
        return movieService.findAllByFeaturesIn(features);
    }

    @RequestMapping(path = "/by/actor/id", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByActorId(@RequestBody List<Long> ids) {
        return movieService.findAllByActorsIdIn(ids);
    }

    @RequestMapping(path = "/by/actor/names", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByActorName(@RequestBody List<String> names) {
        return movieService.findAllByActorsNameIn(names);
    }

    @RequestMapping(path = "/by/all", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(@RequestBody MovieFilterRequest movieFilterRequest) {
        return movieService.findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(movieFilterRequest.getIds(), movieFilterRequest.getFeatures(), movieFilterRequest.getGenres(), movieFilterRequest.getLanguages());
    }

    @RequestMapping(path = "/by/any", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<MovieResponseDto> findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(@RequestBody MovieFilterRequest movieFilterRequest) {
        return movieService.findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(movieFilterRequest.getIds(), movieFilterRequest.getFeatures(), movieFilterRequest.getGenres(), movieFilterRequest.getLanguages());
    }
}
