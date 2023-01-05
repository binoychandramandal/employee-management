package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @RequestMapping("/test")
    public String sayHello(){
        return "Hello from App engine";
    }
    @RequestMapping("/")
    public String sayHello1(){
        return "Hello from App engine";
    }
}
