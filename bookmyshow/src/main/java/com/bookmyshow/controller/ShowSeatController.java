package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.SeatRequestDto;
import com.bookmyshow.dto.SeatResponseDto;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidSeatException;
import com.bookmyshow.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/showseat")
public class ShowSeatController {
    private final SeatService seatService;

    @Autowired
    public ShowSeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public SeatResponseDto save(@RequestBody SeatRequestDto seatRequestDto) throws InvalidAuditoriumException {
        return seatService.saveSeat(seatRequestDto);
    }

    @PutMapping
    public SeatResponseDto update(@RequestBody SeatRequestDto seatRequestDto) throws InvalidSeatException {
        return seatService.updateSeat(seatRequestDto);
    }

    @GetMapping(path = "/{id}")
    public SeatResponseDto getById(@PathVariable("id") Long id) {
        return convert(seatService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return seatService.delete(id);
    }

    @GetMapping
    public List<SeatResponseDto> getAll() {
        return seatService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }
    @GetMapping(path = "/auditorium/{id}")
    public List<SeatResponseDto> getAllByAuditoriumId(@PathVariable("id") Long auditoriumId) {
        return seatService.findAllByAuditoriumId(auditoriumId);
    }
}
