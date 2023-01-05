package com.bookmyshow.service.impl;

import com.bookmyshow.entity.User;
import com.bookmyshow.repository.UserRepository;
import com.bookmyshow.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        setJpaRepository(userRepository);
    }
}
