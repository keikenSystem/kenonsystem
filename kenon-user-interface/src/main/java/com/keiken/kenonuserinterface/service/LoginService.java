package com.keiken.kenonuserinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.operationDTO.GetUserDataDto;

@Service
public class LoginService {
	
	
	GetUserDataDto getUserDataDto;
	
	public boolean validateUser(String userId, String password) {
		getUserDataDto = new GetUserDataDto();
		
		
		EmployeeInfo user = getUserDataDto.findById(userId);
		//Password checking
		boolean valided;
		valided = getUserDataDto.validatedUser(userId, password);
	
	return valided;	
	}

}
