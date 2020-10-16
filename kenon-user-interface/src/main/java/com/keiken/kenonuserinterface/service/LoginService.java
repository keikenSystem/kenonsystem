package com.keiken.kenonuserinterface.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;



@Service
public class LoginService {
	
	
	@Autowired
	private RepoUserLoginOperation repoUserLoginOperation;
	
	public boolean validated(String userId, String password) {
	
	
		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		if(user.getPassword().equals(password))
			return true;
	
		 
		return false;
		
		
	}
	
	
	


		
		
		
		
	
	
}
