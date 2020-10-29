package com.keiken.kenonuserinterface.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "m_most_updated_time")
public class DatewiseUserHandler implements Serializable {

	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	

	@Column(name = "最近の挿入時間", nullable = true)
	@UpdateTimestamp
	private Timestamp lastUsedTime;

	@OneToMany(mappedBy = "datewiseUserHandler", cascade = CascadeType.ALL)
	private List<TemperatureAndSymtomsMesurement> tempAndSymtoms = new ArrayList<TemperatureAndSymtomsMesurement>();

	public DatewiseUserHandler() {

		// TODO Auto-generated constructor stub
	}

	public List<TemperatureAndSymtomsMesurement> getTempAndSymtoms() {
		return tempAndSymtoms;
	}

	public void setTempAndSymtoms(List<TemperatureAndSymtomsMesurement> tempAndSymtoms) {
		this.tempAndSymtoms = tempAndSymtoms;
	}

	public DatewiseUserHandler(String userId, Timestamp lastUsedTime) {

		this.userId = userId;
		this.lastUsedTime = lastUsedTime;
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

}
