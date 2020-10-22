package com.keiken.kenonuserinterface.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.security.PasswordEncoder;



@Service
public class LoginService {
	
	
	@Autowired
	private RepoUserLoginOperation repoUserLoginOperation;
	
	@Autowired
	 PasswordEncoder passwordEncoder;
	
	public boolean checkUserIdExistOrNot(String userId) {
		
			return repoUserLoginOperation.findById(userId).isPresent();
			
	}
	
	public boolean validated(String userId, String password) throws NoSuchAlgorithmException {
	
		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		if(user.getPassword().equals(passwordEncoder.encodedPassword(password)))
			return true;
		 
		return false;
		
	}
	
	public boolean isTokenMatch(String userId, String token) {
	   RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
	   return user.getToken().equals(token);
		
	}
	
	public void resetPassword(String userId,String newPassword) {
		RegistrationInfo user= repoUserLoginOperation.findById(userId).get();
		user.setPassword(passwordEncoder.encodedPassword(newPassword));
		 repoUserLoginOperation.save(user);
	}

	public void removeToken(String userId) {
		// TODO Auto-generated method stub
		  RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		  user.setToken("");
		  repoUserLoginOperation.save(user);
	
	}


	
	
	


		
		
		
		
	
	
}
