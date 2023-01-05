package com.employee.entity;

public enum ERole {
	ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

	public static ERole getERole(String roleName){
      switch (roleName){
          case "ROLE_USER":
          case "role_user":
          case "user":
          case "USER":
              return ROLE_USER;
          case "ROLE_ADMIN":
          case "ADMIN":
          case "admin":
          case "role_admin":
              return ROLE_ADMIN;
          default:return  ROLE_MODERATOR;
      }
    }


}
