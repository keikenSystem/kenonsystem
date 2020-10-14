package com.keiken.kenonuserinterface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "m_user_password")
public abstract class UserRegisteredInfo {

	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	@Column(name = "トークン", nullable = false)
	private String token;
	@Column(name = "パスワード", nullable = false)
	private String password;
	
	@Column(name="role")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRegisteredInfo [userId=" + userId + ", token=" + token + ", password=" + password + ", role=" + role
				+ "]";
	}

	public UserRegisteredInfo(String userId, String token, String password, String role) {
		super();
		this.userId = userId;
		this.token = token;
		this.password = password;
		this.role = role;
	}

	public UserRegisteredInfo() {
		
	}
	
	
	

}
