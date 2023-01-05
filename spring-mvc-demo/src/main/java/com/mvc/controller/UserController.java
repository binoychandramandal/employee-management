package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private MyUserService myUserService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user1) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        myUserService.addUser(user1.getName(),user1.getEmail());
        return modelAndView;
    }
}
