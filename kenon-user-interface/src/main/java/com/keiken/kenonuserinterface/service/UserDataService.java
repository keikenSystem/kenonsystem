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
	private String name;
	


// To control Role 
	
	public boolean isAdmin(String userId) {
	
	       Optional<EmployeeInfo> employeeData =  userDataRepo.findById(userId);
		 
	       return employeeData.get().isAdmin();
		
		
	}
	


// find Full name to set into the user information form 
	
	public String findName(String userId) {
		// TODO Auto-generated method stub
		
		EmployeeInfo data = userDataRepo.findById(userId).get();
		return data.getFullName();
	}
	
	// Getters and setters method
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	

}
