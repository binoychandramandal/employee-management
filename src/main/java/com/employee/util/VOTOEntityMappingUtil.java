package com.employee.util;

import com.employee.entity.ERole;
import com.employee.entity.Role;
import com.employee.entity.User;
import com.employee.payload.request.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.employee.util.Constants.DOB_PATTERN;

public class VOTOEntityMappingUtil {
    private VOTOEntityMappingUtil() {
    }

    public static UserRequest userEntityToVO(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(user.getUserId());
        userRequest.setCreateAt(user.getCreateAt());
        userRequest.setCreatedBy(user.getCreatedBy());
        userRequest.setEmail(user.getEmail());
        userRequest.setDob(user.getDob());
        userRequest.setGender(user.getGender());
        userRequest.setName(user.getName());
        userRequest.setPhone(user.getPhone());
        userRequest.setAddress(user.getAddress());
        userRequest.setUsername(user.getUsername());
        Set<Role> roles = user.getRoles();
        if (Objects.nonNull(roles)) {
            userRequest.setRoles(roles.stream().map(x -> x.getName().name()).collect(Collectors.joining(",")));
        }
        return userRequest;
    }

    public static User userVOToEntity(UserRequest userRequest) {
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setCreateAt(userRequest.getCreateAt());
        user.setCreatedBy(userRequest.getCreatedBy());
        user.setDob(Util.getUtcDate(userRequest.getDob(), DOB_PATTERN));
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setUsername(userRequest.getUsername());
        Set<String> roles = userRequest.rolesNames();
        if (Objects.nonNull(roles)) {
            user.setRoles(roles.stream().map(x -> new Role(ERole.getERole(x))).collect(Collectors.toSet()));
        }
        return user;
    }

    public static List<UserRequest> userEntityToVO(List<User> users) {
        List<UserRequest> results = new ArrayList<>();
        for (User user : users) {
            results.add(userEntityToVO(user));
        }
        return results;
    }
}