package com.bookmyshow.controller;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.UserRequestDto;
import com.bookmyshow.dto.UserResponseDto;
import com.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        return convert(userService.add(convert(userRequestDto)));
    }

    @PutMapping
    public UserResponseDto update(@RequestBody UserRequestDto userRequestDto) {
        return convert(userService.update(convert(userRequestDto)));
    }

    @GetMapping(path = "/{id}")
    public UserResponseDto getById(@PathVariable("id") Long id) {
        return convert(userService.findById(id).orElse(null));
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll().stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }



}
