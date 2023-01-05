package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.CityRequestDto;
import com.bookmyshow.dto.CityResponseDto;
import com.bookmyshow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public CityResponseDto save(@RequestBody CityRequestDto cityRequestDto) {
        return convert(cityService.add(convert(cityRequestDto)));
    }

    @PutMapping
    public CityResponseDto update(@RequestBody CityRequestDto cityRequestDto) {
        return convert(cityService.update(convert(cityRequestDto)));
    }

    @GetMapping(path = "/{id}")
    public CityResponseDto getById(@PathVariable("id") Long id) {
        return convert(cityService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return cityService.delete(id);
    }

    @GetMapping
    public List<CityResponseDto> getAll() {
        return cityService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }


}
