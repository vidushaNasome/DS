package com.example.REST.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class sensor {
	
	@Id
	@Column(name="location")
	private String location;
	
	@Column(name="activity")
	private String activity;
	
	@Column(name="smokelevel")
	private int smokelevel;
	
	@Column(name="co2")
	private int co2;
	
	public sensor() {
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getSmokelevel() {
		return smokelevel;
	}

	public void setSmokelevel(int smokelevel) {
		this.smokelevel = smokelevel;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}
	

}
