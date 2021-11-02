package com.RiskManagement.entities;

public class JwtResponse {
	public String result;
	public String message;
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
	public JwtResponse(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
