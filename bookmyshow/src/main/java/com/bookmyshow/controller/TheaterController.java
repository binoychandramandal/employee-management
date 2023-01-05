package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.TheaterRequestDto;
import com.bookmyshow.dto.TheaterResponseDto;
import com.bookmyshow.exception.InvalidCityException;
import com.bookmyshow.exception.InvalidTheaterException;
import com.bookmyshow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/theater")
public class TheaterController {
    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }


    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public TheaterResponseDto save(@RequestBody TheaterRequestDto theaterRequestDto) throws InvalidCityException {
        return theaterService.saveTheater(theaterRequestDto);
    }

    @PutMapping
    public TheaterResponseDto update(@RequestBody TheaterRequestDto theaterRequestDto) throws InvalidTheaterException {
        return theaterService.updateTheater(theaterRequestDto);
    }

    @GetMapping(path = "/{id}")
    public TheaterResponseDto getById(@PathVariable("id") Long id) {
        return convert(theaterService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return theaterService.delete(id);
    }

    @GetMapping
    public List<TheaterResponseDto> getAll() {
        return theaterService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @GetMapping(path = "/city/{id}")
    public List<TheaterResponseDto> getAllByCityId(@PathVariable("id") Long cityId) throws InvalidCityException {
        return theaterService.getAllTheaterByCityId(cityId);
    }


}
