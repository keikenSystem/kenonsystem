package com.keiken.kenonuserinterface.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.keiken.kenonuserinterface.service.LoginService;

//Custom validator for size, 

@Service
public class CustomValidator {
	
	@Autowired
	LoginService login;

	public String checkUserId(String stringCellValue) {
		// TODO Auto-generated method stub
		if(stringCellValue.length()!=5)
		return "user id should be length 5";
		
		return "";
	}

	public String checkUserNameFormat(String stringCellValue) {
		// TODO Auto-generated method stub
		if(stringCellValue.length()<=0||stringCellValue.length()>50)
			return "name should be null or length should not exceed 50";
			
			return "";
	}

	public String checkEmailFormat(String stringCellValue) {
		
		return "";
	}

	public String checkUserPasswordFormat(String stringCellValue, String stringCellValue2) {
		// TODO Auto-generated method stub
		if(login.checkUserIdExistOrNot(stringCellValue))
			if(!stringCellValue2.equals("")) {
				return "can't change password of registerd user";
			}
		
		return "";
	}

	public String checkBoolean(boolean booleanCellValue) {
		// TODO Auto-generated method stub
		
		return "";
	}
	
	
	//Password length size should be 6 to 50
	
	
	
	

}
