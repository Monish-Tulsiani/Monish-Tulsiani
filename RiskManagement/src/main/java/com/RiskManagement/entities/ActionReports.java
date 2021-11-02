package com.RiskManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="ACTIONREPORTS")
public class ActionReports {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int baid;
	private int cid;
	private String result;
	private String description;
	private String rejectingparameter; 
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ActionReports() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRejectingparameter() {
		return rejectingparameter;
	}
	public void setRejectingparameter(String rejectingparameter) {
		this.rejectingparameter = rejectingparameter;
	}
	
	public int getBaid() {
		return baid;
	}
	public void setBaid(int baid) {
		this.baid = baid;
	}
	public ActionReports(int id, int baid, int cid, String result, String description, String rejectingparameter) {
		super();
		this.id = id;
		this.baid = baid;
		this.cid = cid;
		this.result = result;
		this.description = description;
		this.rejectingparameter = rejectingparameter;
	}
	
	
	

}
