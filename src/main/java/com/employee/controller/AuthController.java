package com.employee.controller;

import com.employee.jwt.JwtUtils;
import com.employee.payload.request.LoginRequest;
import com.employee.payload.request.UserRequest;
import com.employee.payload.response.JwtResponse;
import com.employee.payload.response.Response;
import com.employee.service.UserDetailsImpl;
import com.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LOGGER.info("Login request received with information: {}", loginRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        LOGGER.info("Login request authentication done");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getName(), userDetails.getGender(), userDetails.getPhone(), roles, "SUCCESS");
        LOGGER.info("Login request processed with information: {}", jwtResponse);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    @Transactional
    public Response registerUser(@Valid @RequestBody UserRequest signUpRequest) {
        LOGGER.info("User Registration request received with information: {}", signUpRequest);
        return userService.registerUser(signUpRequest);
    }

}


