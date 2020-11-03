package com.keiken.kenonuserinterface.service;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.security.PasswordEncoder;



@Service
@Transactional
public class LoginService {
	
	
	@Autowired
	private RepoUserLoginOperation repoUserLoginOperation;
	
	@Autowired
	 PasswordEncoder passwordEncoder;
	
	//Check userid exist or not
	
	public boolean checkUserIdExistOrNot(String userId) {
		
			return repoUserLoginOperation.findById(userId).isPresent();
			
	}
	
	//Get permission to logged in
	
	public boolean validated(String userId, String password) throws NoSuchAlgorithmException {
	
		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		if(user.getPassword().equals(passwordEncoder.encodedPassword(password)))
			return true;
		 
		return false;
		
	}
	
	//user is valid to use token to password reset or not
	
	public boolean isTokenMatch(String userId, String token) {
	   RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
	   return user.getToken().equals(token);
		
	}
	
	// Replace the existing password with new password
	
	public void resetPassword(String userId,String newPassword) {
		RegistrationInfo user= repoUserLoginOperation.findById(userId).get();
		user.setPassword(passwordEncoder.encodedPassword(newPassword));
		 repoUserLoginOperation.save(user);
	}
	
	//Dismiss the validity of  password reset link sending using email removing token

	public void removeToken(String userId) {
		// TODO Auto-generated method stub
		  RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		  user.setToken("");
		  repoUserLoginOperation.save(user);
	
	}

}
