package com.RiskManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="APPROVEDAPPLICATIONS")
public class ApprovedApplications {
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getCompanyvalue() {
		return companyvalue;
	}
	public void setCompanyvalue(int companyvalue) {
		this.companyvalue = companyvalue;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int cid;
	private String name;
	private int creditvalue;
	private int turnover;
	private int companyvalue;
	private int amount;
	private String action;
	private String emailid;
	private int baid;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public ApprovedApplications() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public int getBaid() {
		return baid;
	}
	public void setBaid(int baid) {
		this.baid = baid;
	}
	public ApprovedApplications(int id, int cid, String name, int creditvalue, int turnover, int companyvalue,
			int amount, String action, String emailid, int baid) {
		super();
		this.id = id;
		this.cid = cid;
		this.name = name;
		this.creditvalue = creditvalue;
		this.turnover = turnover;
		this.companyvalue = companyvalue;
		this.amount = amount;
		this.action = action;
		this.emailid = emailid;
		this.baid = baid;
	}
    
}
