package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.SeatRequestDto;
import com.bookmyshow.dto.SeatResponseDto;
import com.bookmyshow.dto.ShowRequestDto;
import com.bookmyshow.dto.ShowResponseDto;
import com.bookmyshow.entity.Language;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidMovieException;
import com.bookmyshow.exception.InvalidSeatException;
import com.bookmyshow.service.SeatService;
import com.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/show")
public class ShowController {
    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ShowResponseDto createShow(@RequestBody ShowRequestDto showRequestDto) throws InvalidAuditoriumException, InvalidMovieException {
        return showService.createShow(showRequestDto);
    }

    @PutMapping
    public ShowResponseDto update(@RequestBody  ShowRequestDto showRequestDto) throws InvalidSeatException {
        return showService.updateShow(showRequestDto);
    }

    @GetMapping(path = "/{id}")
    public ShowResponseDto getById(@PathVariable("id") Long id) {
        return convert(showService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return showService.delete(id);
    }

    @GetMapping
    public List<ShowResponseDto> getAll() {
        return showService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @GetMapping(path = "/auditorium/{id}")
    public List<ShowResponseDto> getAllByAuditoriumId(@PathVariable("id") Long auditoriumId) {
        return showService.findAllByAuditoriumId(auditoriumId);
    }

    @GetMapping(path = "/movie/{id}")
    public List<ShowResponseDto> getAllByMovieId(@PathVariable("id") Long movieId) {
        return showService.findAllByMovieId(movieId);
    }

    @GetMapping(path = "/movie/{id}/city/{cityId}")
    public List<ShowResponseDto> getAllByMovieIdCityId(@PathVariable("id") Long movieId,@PathVariable("cityId") Long cityId) {
        return showService.findAllByMovieId_CityId_(movieId,cityId);
    }
    @PostMapping(path = "/movie/{id}/city/{cityId}/languages")
    public List<ShowResponseDto> getAllByMovieIdCityIdAndLanguage(@RequestBody List<Language> languages,@PathVariable("id") Long movieId,@PathVariable("cityId") Long cityId) {
        return showService.findAllByMovieId_LanguageIn_CityId_(movieId,languages,cityId);
    }
}
