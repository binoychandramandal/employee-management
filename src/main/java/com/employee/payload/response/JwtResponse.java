package com.employee.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
	private String token;
	private String userId;
	private String type = "Bearer";
	private String username;
	private String email;
	private List<String> roles;
    private String accountNumber;
	private String name;
	private char gender;
	private String phone;
	private Timestamp createAt;
	private String createdBy;
	private String status;
	public JwtResponse() {}
	public JwtResponse(String accessToken,String userId,
					   String username, String email,String name,char gender,String phone,
					   List<String> roles,String status) {
		this.token = accessToken;
		this.userId=userId;
		this.accountNumber = accountNumber;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.name=name;
		this.gender=gender;
		this.phone=phone;
       this.status=status;
	}

}
