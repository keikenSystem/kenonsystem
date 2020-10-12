package com.keiken.kenonuserinterface.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ユーザ情報")
public class EmployeeInfo {

	@Column(name = "氏名", nullable = false)
	private String fullName;
	
	@Column(name = "氏名カナ", nullable = false)
	private String fullNameInKata;
	
	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	
	@Column(name = "部門", nullable = false)
	private String department;
	
	@Column(name = "作成日時", nullable = false)
	private Date createDate;

	@Column(name = "更新日時", nullable = false)
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
