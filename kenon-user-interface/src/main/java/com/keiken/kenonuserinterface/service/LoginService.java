package com.keiken.kenonuserinterface.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.UserRegisteredInfo;
import com.keiken.kenonuserinterface.repository.ImpTemperatureAndSymtomsOperation;
import com.keiken.kenonuserinterface.repository.ImpUserLoginOperation;
import com.keiken.kenonuserinterface.repository.ImpUserRepository;



@Service
public class LoginService {
	
	
	@Autowired
	ImpUserLoginOperation impUserLoginOperation;
	
	@Autowired
	ImpUserRepository ImpUserRepository;
	
	@Autowired
	ImpTemperatureAndSymtomsOperation impTemperatureAndSymtomsOperation;
	
	
	public boolean validated(String userId,String password) {
		 Optional<UserRegisteredInfo> userInfo = impUserLoginOperation.findById(userId);
		System.out.println(userInfo);
		return false;
		
		
		
		
	}
	
}
