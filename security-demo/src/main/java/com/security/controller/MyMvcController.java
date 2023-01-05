package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth/")
@CrossOrigin
public class MyMvcController {

    @GetMapping("login")
    public String doLogin() {
        return "login.html";
    }

}
