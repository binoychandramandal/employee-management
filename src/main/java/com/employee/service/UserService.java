package com.employee.service;

import com.employee.dao.RoleRepository;
import com.employee.dao.UserRepository;
import com.employee.entity.ERole;
import com.employee.entity.Role;
import com.employee.entity.User;
import com.employee.payload.request.UserRequest;
import com.employee.payload.response.Response;
import com.employee.util.Constants;
import com.employee.util.Util;
import com.employee.util.VOTOEntityMappingUtil;
import com.employee.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {
    private static final  Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Response registerUser(UserRequest userRequest){
        Response response = Response.getInstance();
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            response.addMessage("Username is already taken!");
            return response;
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            response.addMessage("Email is already in use!");
            return response;
        }

        LOGGER.info("User Registration request validation done");
        // Create new user's account
        User user = new User(userRequest.getUsername(), userRequest.getName(),
                userRequest.getGender(), userRequest.getEmail(), userRequest.getPhone(),
                encoder.encode(userRequest.getPassword()), Util.parseTimestamp(Util.currentDate()),
                userRequest.getCreatedBy(),Util.getUtcDate(userRequest.getDob(), Constants.DOB_PATTERN));
        user.setAddress(userRequest.getAddress());
        //VOTOEntityMappingUtil.customerVOToEntity(userRequest.getCustomers()).forEach(user::addCustomer);
        Set<String> strRoles = userRequest.rolesNames();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
            if (!userRole.isPresent()) {
                response.addMessage("Role is not found.");
                return response;
            }
            roles.add(userRole.get());
        } else {
            for (String role : strRoles) {
                if ("ROLE_ADMIN".equalsIgnoreCase(role) || "admin".equalsIgnoreCase(role)) {
                    Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                    if (!adminRole.isPresent()) {
                        response.addMessage("Role is not found.");
                        return response;
                    }
                    roles.add(adminRole.get());
                } else if ("ROLE_MODERATOR".equalsIgnoreCase(role) || "mod".equalsIgnoreCase(role)) {
                    Optional<Role> modRole = roleRepository.findByName(ERole.ROLE_MODERATOR);
                    if (!modRole.isPresent()) {
                        response.addMessage("Role is not found.");
                        return response;
                    }
                    roles.add(modRole.get());
                } else {
                    Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
                    if (!userRole.isPresent()) {
                        response.addMessage("Role is not found.");
                        return response;
                    }
                    roles.add(userRole.get());
                }
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        response.setStatus(200);
        response.addMessage("User registered successfully!");
        LOGGER.info("User Registration done");
        return response;
    }

    public Response getUser(Integer userId) {
        Response response = Response.getInstance();
        if (Objects.isNull(userId)) {
            response.addMessage("UserId is invalid");
            LOGGER.error("UserId is invalid");
            return response;
        }
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(usr -> response.setData(com.employee.util.VOTOEntityMappingUtil.userEntityToVO(user.get())));
        response.setStatus(Constants.APPLICATION_SUCCESS_CODE);
        return response;
    }

    public Response getUsers() {
        Response response = Response.getInstance();
        List<User> user = userRepository.getAllActiveUsers();
        response.setData(VOTOEntityMappingUtil.userEntityToVO(user));
        response.setStatus(Constants.APPLICATION_SUCCESS_CODE);
        return response;
    }

    public Response updateUser(UserRequest userRequest) {
        Response response = Response.getInstance();
        List<String> errors = Validator.validateUserForUpdate(userRequest);
        if (!errors.isEmpty()) {
            response.addMessage(errors);
            return response;
        }
        Optional<User> userOption = userRepository.findById(userRequest.getUserId());
        if (!userOption.isPresent()) {
            LOGGER.error("UserId : {} does not exists", userRequest.getUserId());
            response.addMessage("UserId" + userRequest.getUserId() + " does not exists");
            return response;
        }
        User user = userOption.get();
        if (Objects.nonNull(userRequest.getAddress()))
            user.setAddress(userRequest.getAddress());
        if (Objects.nonNull(userRequest.getEmail()))
            user.setEmail(userRequest.getEmail());
        if (Objects.nonNull(userRequest.getName()))
            user.setName(userRequest.getName());
        if (Objects.nonNull(userRequest.getPhone()))
            user.setPhone(userRequest.getPhone());
		if (Objects.nonNull(userRequest.getPassword()))
			user.setPassword(encoder.encode(userRequest.getPassword()));
		if (Objects.nonNull(userRequest.getUsername()))
			user.setUsername(userRequest.getUsername());
        if (Objects.nonNull(userRequest.getDob())) {
            user.setDob(Util.getUtcDateOrDefault(userRequest.getDob(), Constants.DOB_PATTERN, userRequest.getDob()));
        }
            user.setGender(userRequest.getGender());
        Set<String> roles=userRequest.rolesNames();
        if(!CollectionUtils.isEmpty(roles))
            user.setRoles(userRequest.rolesNames().stream().map(r-> roleRepository.findByName(ERole.getERole(r))).filter(x->x.isPresent()).map(x->x.get()).collect(Collectors.toSet()));
        response.addMessage("UserId : " + userRequest.getUserId() + " updated successfully");
        response.setStatus(Constants.APPLICATION_SUCCESS_CODE);
        return response;
    }

    public Response deleteUser(Integer userId) {
        Response response = Response.getInstance();
		if (Objects.isNull(userId)) {
			response.addMessage("UserId is invalid");
			LOGGER.error("UserId is invalid");
			return response;
		}
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setDeleted((byte) 1);
			response.setStatus(Constants.APPLICATION_SUCCESS_CODE);
			response.addMessage("User : "+userId+" deleted successfully");
			LOGGER.info("UserId : {} deleted successfully",userId);
            return response;
        }else{
			response.setStatus(Constants.APPLICATION_ERROR_CODE);
			response.addMessage("User : "+userId+" not deleted");
			LOGGER.error("UserId : {} does not exists",userId);
		}
        return response;
    }


}
