package com.employee.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    @GetMapping
    public String sayHello(){
        return "Hello";
    }
    @DeleteMapping
    public String sayHello1(){
        return "Hello";
    }
}
