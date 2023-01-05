package com.employee.controller;

import com.employee.payload.request.UserRequest;
import com.employee.payload.response.Response;
import com.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{userId}",method = RequestMethod.GET)
	public Response getUser(@Valid @PathVariable("userId") Integer userId) {
		LOGGER.info("Received request for User by userId: {}",userId);
		return userService.getUser(userId);
	}
	@RequestMapping(method = RequestMethod.GET)
	public Response getUsers() {
		LOGGER.info("Received request for Users");
		return userService.getUsers();
	}

	@RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
	public Response deleteUser(@Valid @PathVariable("userId") Integer userId) {
		LOGGER.info("Received request for delete User: {}",userId);
		return userService.deleteUser(userId);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response updateUser(@Valid @RequestBody UserRequest userUpdateRequest) {
		LOGGER.info("Received request for update User: {}",userUpdateRequest);
		return userService.updateUser(userUpdateRequest);
	}
}
