package com.employee.payload.response;

import com.employee.util.Constants;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Response {
	private Date timestamp=new Date();
	private int status= Constants.APPLICATION_ERROR_CODE;
	private Object data;
	private List<String> message=new ArrayList<>();

	public void addMessage(String message){
		this.message.add(message);
	}
	public void addMessage(List<String> messages){
		this.message.addAll(messages);
	}

	public static  Response getInstance(){
		return new Response();
	}
}
