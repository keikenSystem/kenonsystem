package com.keiken.kenonuserinterface.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "m_user_temparature")
public class TemperatureAndSymtomsMesurement {

	@Column(name = "体温",nullable = true)
	private double temperature;
	
	@Column(name = "症状の有無",nullable = true)
	private boolean gotSymtoms;

	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	
	@Column(name = "更新日時",nullable = true)
	@UpdateTimestamp
	private Timestamp lastUsedTime;

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

	public Timestamp getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Timestamp lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	@Override
	public String toString() {
		return "TemperatureAndSymtomsMesurement [temperature=" + temperature + ", gotSymtoms=" + gotSymtoms
				+ ", userId=" + userId + ", lastUsedTime=" + lastUsedTime + "]";
	}

}
