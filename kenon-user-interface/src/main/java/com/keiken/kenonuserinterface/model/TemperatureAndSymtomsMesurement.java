package com.keiken.kenonuserinterface.model;

import java.sql.Date;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TemperatureAndSymtomsMesurement {
	private double temperature;
	private boolean gotSymtoms;
	private String userId;
	private Date lastUsedTime;
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public boolean isGotSymtoms() {
		return gotSymtoms;
	}
	public void setGotSymtoms(boolean gotSymtoms) {
		this.gotSymtoms = gotSymtoms;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLastUsedTime() {
		return lastUsedTime;
	}
	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}
	

}
