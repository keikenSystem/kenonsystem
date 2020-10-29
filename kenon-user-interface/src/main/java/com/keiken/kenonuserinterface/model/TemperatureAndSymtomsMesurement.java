package com.keiken.kenonuserinterface.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//0e698a8ffc1a0af622c7b4db3cb750cc
@Entity
@Table(name = "m_user_temparature")
public class TemperatureAndSymtomsMesurement implements Serializable {


	@Column(name = "体温", nullable = true)
	private double temperature;

	@Column(name = "症状の有無", nullable = true)
	private boolean gotSymtoms;

	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;

	@Column(name = "更新日時", nullable = true)
	private Timestamp lastUsedTime;

	@ManyToOne()
	@JoinColumn(name="id")
	private DatewiseUserHandler datewiseUserHandler;

	
	
	public TemperatureAndSymtomsMesurement() {
		
		// TODO Auto-generated constructor stub
	}
	public TemperatureAndSymtomsMesurement(String userId, DatewiseUserHandler datewiseUserHandler) {
		this.userId = userId;
		this.datewiseUserHandler = datewiseUserHandler;
	}


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

	public DatewiseUserHandler getDatewiseUserHandler() {
		return datewiseUserHandler;
	}

	public void setDatewiseUserHandler(DatewiseUserHandler datewiseUserHandler) {
		this.datewiseUserHandler = datewiseUserHandler;
	}

}
