package com.keiken.kenonuserinterface.service;

import org.springframework.stereotype.Service;

@Service
public class PageControlService {
	
	private boolean loggedIn;
	private String loggedId;
	public String getLoggedId() {
		return loggedId;
	}
	public void setLoggedId(String loggedId) {
		this.loggedId = loggedId;
	}
	private boolean visitLogin;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.loggedIn = isLoggedIn;
	}
	public boolean isVisitLogin() {
		return visitLogin;
	}
	public void setVisitLogin(boolean isVisitLogin) {
		this.visitLogin = isVisitLogin;
	}
	

}
