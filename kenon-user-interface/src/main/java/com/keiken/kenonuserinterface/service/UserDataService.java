package com.keiken.kenonuserinterface.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUser;

@Service
public class UserDataService {
	

	@Autowired
	private RepoUser userDataRepo;
	private boolean role=false;
	private String lastUsedDateText;
	private String name;
	
	public boolean isAdmin(String userId) {
	
	       Optional<EmployeeInfo> employeeData =  userDataRepo.findById(userId);
		 
	       return employeeData.get().isAdmin();
		
		
		
	}
	
	

	public String getLastUsedDateText(String userId) {
		EmployeeInfo employeeData =  userDataRepo.findById(userId).get();
		Timestamp lastTimeStamp = employeeData.getLastUsedDate();
		int year = lastTimeStamp.getYear();
		int month = lastTimeStamp.getMonth();
		int date = lastTimeStamp.getDate();
		int hour = lastTimeStamp.getHours();
		int min = lastTimeStamp.getMinutes();
		int sec = lastTimeStamp.getSeconds();
		name = employeeData.getFullName();
		
		return String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(date)+"日"+String.valueOf(hour)+"時"+String.valueOf(min)+"分"+String.valueOf(sec)+"秒　使新";
		
	}

	public void setLastUsedDateText(String lastUsedDateText) {
		this.lastUsedDateText = lastUsedDateText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
