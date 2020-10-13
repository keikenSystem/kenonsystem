package com.keiken.kenonuserinterface.model;

import java.sql.Date;

import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class EmployeeInfo {

	private String fullName;

	private String fullNameInKata;

	private String userId;

	private String department;

	private Date createDate;

	private Date lastUsedDate;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullNameInKata() {
		return fullNameInKata;
	}

	public void setFullNameInKata(String fullNameInKata) {
		this.fullNameInKata = fullNameInKata;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUsedDate() {
		return lastUsedDate;
	}

	public void setLastUsedDate(Date lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}
}
