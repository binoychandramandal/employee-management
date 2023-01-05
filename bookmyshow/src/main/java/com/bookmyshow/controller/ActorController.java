package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.ActorRequestDto;
import com.bookmyshow.dto.ActorResponseDto;
import com.bookmyshow.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@RestController
@RequestMapping(path = "/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ActorResponseDto save(@RequestBody ActorRequestDto actorRequestDto) {
        return convert(actorService.add(convert(actorRequestDto)));
    }

    @PutMapping
    public ActorResponseDto update(@RequestBody ActorRequestDto actorRequestDto) {
        return convert(actorService.update(convert(actorRequestDto)));
    }

    @GetMapping(path = "/{id}")
    public ActorResponseDto getById(@PathVariable("id") Long id) {
        return convert(actorService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return actorService.delete(id);
    }

    @GetMapping
    public List<ActorResponseDto> getAll() {
        return actorService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }
}
