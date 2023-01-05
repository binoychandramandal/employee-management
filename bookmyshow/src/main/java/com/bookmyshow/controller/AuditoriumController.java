package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.AuditoriumRequestDto;
import com.bookmyshow.dto.AuditoriumResponseDto;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidTheaterException;
import com.bookmyshow.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/auditorium")
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @Autowired
    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public AuditoriumResponseDto save(@RequestBody AuditoriumRequestDto auditoriumRequestDto) throws InvalidTheaterException {
        return auditoriumService.saveAuditorium(auditoriumRequestDto);
    }

    @PutMapping
    public AuditoriumResponseDto update(@RequestBody  AuditoriumRequestDto auditoriumRequestDto) throws InvalidAuditoriumException {
        return auditoriumService.updateAuditorium(auditoriumRequestDto);
    }

    @GetMapping(path = "/{id}")
    public AuditoriumResponseDto getById(@PathVariable("id") Long id) {
        return convert(auditoriumService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return auditoriumService.delete(id);
    }

    @GetMapping
    public List<AuditoriumResponseDto> getAll() {
        return auditoriumService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @GetMapping(path = "/theater/{id}")
    public List<AuditoriumResponseDto> getAllByTheaterId(@PathVariable("id") Long id) {
        return auditoriumService.getAllAuditoriumByTheaterId(id);
    }


}
