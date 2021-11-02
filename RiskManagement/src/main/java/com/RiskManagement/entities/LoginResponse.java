package com.RiskManagement.entities;

public class LoginResponse {
	public String result;
	public String message;
	public String token;
	public LoginResponse(String result, String message, String token) {
		super();
		this.result = result;
		this.message = message;
		this.token = token;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
