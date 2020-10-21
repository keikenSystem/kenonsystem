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
	private boolean role=false;
	
	
	public boolean checkUserIdExistOrNot(String userId) {
		
			return repoUserLoginOperation.findById(userId).isPresent();
			
	}
	
	public boolean validated(String userId, String password) {
	
		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		if(user.getPassword().equals(password))
			return true;

	
		 
		return false;
		
		
	}
	
	public void resetPassword(String userId,String newPassword) {
		RegistrationInfo user= repoUserLoginOperation.findById(userId).get();
		user.setPassword(newPassword);
		 repoUserLoginOperation.save(user);
	}
	
	
	


		
		
		
		
	
	
}
