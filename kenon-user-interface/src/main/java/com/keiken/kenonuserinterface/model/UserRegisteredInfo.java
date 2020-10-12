package com.keiken.kenonuserinterface.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class UserRegisteredInfo {
	private String userId;
	private String token;
	private String password;
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

}
