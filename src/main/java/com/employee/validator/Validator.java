package com.employee.validator;

import com.employee.payload.request.UserRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Validator {
    private final static Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    public static List<String> validateUserForUpdate(UserRequest userRequest){
        List<String> result =new ArrayList<>();
        result.addAll(validateUser(userRequest));
        if(Objects.isNull(userRequest.getUserId())) {
            result.add("UserId is invalid");
            LOGGER.error("UserId is invalid");
        }
        return result;
    }
    public static List<String> validateUserForAdd(UserRequest userRequest){
        List<String> result =new ArrayList<>();
        result.addAll(validateUser(userRequest));
        return result;
    }

    private static List<String> validateUser(UserRequest userRequest){
        List<String> result =new ArrayList<>();
        if(Objects.isNull(userRequest))
        {
            result.add("UserRequest object is empty");
            LOGGER.error("UserRequest object is empty");
            return result;
        }
        if(StringUtils.isEmpty(userRequest.getPhone())) {
            result.add("Phone is invalid");
            LOGGER.error("Phone is invalid");
        }
        if(StringUtils.isEmpty(userRequest.getName())) {
            result.add("Name is invalid");
            LOGGER.error("Name is invalid");
        }
        if(StringUtils.isEmpty(userRequest.getEmail())) {
            result.add("Email is invalid");
            LOGGER.error("Email is invalid");
        }
        if(StringUtils.isEmpty(userRequest.getAddress())) {
            result.add("Address is invalid");
            LOGGER.error("Address is invalid");
        }
        if(StringUtils.isEmpty(userRequest.getUsername())) {
            result.add("Username is invalid");
            LOGGER.error("Username is invalid");
        }
        if(StringUtils.isEmpty(userRequest.getDob())) {
            result.add("Dob is invalid");
            LOGGER.error("Dob is invalid");
        }
        return result;
    }




}
