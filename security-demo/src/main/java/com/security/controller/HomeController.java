package com.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String hello(){
        return "I am from Home "+ SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public String register(){
        return "I am from Register home";
    }

}
