package com.keiken.kenonuserinterface.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Table(name = "m_user")
public class EmployeeInfo {

	@Column(name = "氏名", nullable = false)
	private String fullName;
	
	@Column(name = "氏名カナ", nullable = false)
	private String fullNameInKata;
	
	@Id
	@Column(name = "社員番号", nullable = false)
	private String userId;
	
	@Column(name="mail",nullable= false)
	private String email;
	
	@Column(name = "部門", nullable = false)
	private String department;
	
	@Column(name = "作成日時", nullable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@Column(name = "更新日時", nullable = false)
	@UpdateTimestamp
	private Timestamp lastUsedDate;
	
	@Column(name = "管理権限", nullable = false)
	private boolean isAdmin;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUsedDate() {
		return lastUsedDate;
	}

	public void setLastUsedDate(Timestamp lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [fullName=" + fullName + ", fullNameInKata=" + fullNameInKata + ", userId=" + userId
				+ ", email=" + email + ", department=" + department + ", isAdmin=" + isAdmin + "]";
	}

}