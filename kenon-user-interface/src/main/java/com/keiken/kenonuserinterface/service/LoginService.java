package com.keiken.kenonuserinterface.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoTemperatureAndSymtomsOperation;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.repository.RepoUser;



@Service
public class LoginService {
	
	
	@Autowired
	RepoUserLoginOperation impUserLoginOperation;
	
	@Autowired
	RepoUser ImpUserRepository;
	
	@Autowired
	RepoTemperatureAndSymtomsOperation impTemperatureAndSymtomsOperation;
	
	
	public boolean validated(String userId,String password) {
		 Optional<RegistrationInfo> userInfo = impUserLoginOperation.findById(userId);
		System.out.println(userInfo);
		return false;
		
		
		
		
	}
	
}
