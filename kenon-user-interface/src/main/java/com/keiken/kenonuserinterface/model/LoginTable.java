package com.keiken.kenonuserinterface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_login_table")
public class LoginTable {

	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	
	@Column(name="アクティブ",nullable = false)
	private boolean active;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
