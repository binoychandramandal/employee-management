package com.mvc.service;

import com.mvc.entity.MyUser;
import com.mvc.model.User;
import com.mvc.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository userRepository;

    public void addUser(String name, String email) {
        MyUser user = new MyUser();
        user.setEmail(email);
        user.setName(name);
        userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll().stream().map(x -> {
            User user = new User();
            user.setEmail(x.getEmail());
            user.setName(x.getName());
            return user;
        }).collect(Collectors.toList());
    }


}
