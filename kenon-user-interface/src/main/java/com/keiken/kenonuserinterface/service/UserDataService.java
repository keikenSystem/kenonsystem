package com.keiken.kenonuserinterface.service;

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
	
	public boolean isAdmin(String userId) {
	
	       Optional<EmployeeInfo> employeeData =  userDataRepo.findById(userId);
		 
	       return employeeData.get().isAdmin();
		
		
		
		
	}

}
