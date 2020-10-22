package com.keiken.kenonuserinterface.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUser;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.security.PasswordEncoder;

@Service
public class EmailControlService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private String token;
	@Autowired
	private RepoUserLoginOperation repoUserLoginOperation;
	@Autowired
	RepoUser repoUser;

	//Get  Token for reseting password
	
	public String createTokenForPasswordReset(String userId) {
		byte[] array = new byte[7]; 
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		String randomText = userId + generatedString;
		token = passwordEncoder.encodedPassword(randomText);

		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		user.setToken(token);
		repoUserLoginOperation.save(user);
		return token;

	}

	//Get user Id using Email
	
	public String getUserIdByEmailId(String email) {
		List<EmployeeInfo> userList = repoUser.findUserByEmail(email);

		return userList.get(0).getUserId();
	}

	//Getters and setters for Token
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
