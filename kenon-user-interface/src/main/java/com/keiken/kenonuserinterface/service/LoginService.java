package com.keiken.kenonuserinterface.service;

import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.operationDTO.GetUserDataDto;

@Service
public class LoginService {
	public boolean validateUser(String userId, String password) {
		GetUserDataDto getUserDataDto = new GetUserDataDto();
		EmployeeInfo user = getUserDataDto.findById(userId);
		//Password checking
		
		return false;
		
	}

}
