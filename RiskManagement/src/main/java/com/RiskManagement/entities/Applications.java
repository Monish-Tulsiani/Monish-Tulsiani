package com.RiskManagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="APPLICATIONS")
public class Applications {
	@Id
	
	private int cid;
	private String name;
	private int creditvalue;
	private int turnover;
	private int companyvalue;
	private int amount;
	private String emailid;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreditvalue() {
		return creditvalue;
	}

	public void setCreditvalue(int creditvalue) {
		this.creditvalue = creditvalue;
	}

	public int getTurnover() {
		return turnover;
	}

	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}

	

	public Applications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCompanyvalue() {
		return companyvalue;
	}

	public void setCompanyvalue(int companyvalue) {
		this.companyvalue = companyvalue;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Applications(int cid, String name, int creditvalue, int turnover, int companyvalue, int amount,
			String emailid) {
		super();
		this.cid = cid;
		this.name = name;
		this.creditvalue = creditvalue;
		this.turnover = turnover;
		this.companyvalue = companyvalue;
		this.amount = amount;
		this.emailid = emailid;
	}

	
	
	
	

}
