package com.keiken.kenonuserinterface.validator;

import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 Pattern pattern = Pattern.compile(regex);
		 if(pattern.matcher(stringCellValue).matches())
		return "";
		 else return  "Email format error";
	}


	public String checkBoolean(boolean booleanCellValue) {
		
		
		return "";
	}
	public String checkBoolean(String booleanCellValue) {
		// TODO Auto-generated method stub
		
		
		return "Administator value should be true/false";
	}

	public String checkUserPasswordFormat(XSSFCell cell, XSSFCell cell2) {
		if(login.checkUserIdExistOrNot(cell.getStringCellValue())) {
			if(cell2!=null&&!cell2.getStringCellValue().equals("")) {
				System.out.println(cell2.getStringCellValue());
				return "can't change password of registerd user";
			}
		}
		else {
			
		if(cell2==null||cell2.getStringCellValue().length()<6||cell2.getStringCellValue().length()>50) {
			return "password length should be 6 to 50";
		}
		
		}
		
		
		return "";
	}
}
	


	
	//Password length size should be 6 to 50
	
	
	
	
