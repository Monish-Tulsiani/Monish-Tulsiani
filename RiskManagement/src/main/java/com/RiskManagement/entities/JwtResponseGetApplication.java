package com.RiskManagement.entities;

public class JwtResponseGetApplication {
	public String result;
	public String message;
	public Applications appli;
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
	public JwtResponseGetApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponseGetApplication(String result, String message, Applications appli) {
		super();
		this.result = result;
		this.message = message;
		this.appli = appli;
	}
	public Applications getAppli() {
		return appli;
	}
	public void setAppli(Applications appli) {
		this.appli = appli;
	}

}
