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
		return "社員番号は長さ5でなければなりません。";
		
		return "";
	}

	public String checkUserNameFormat(String stringCellValue) {
		// TODO Auto-generated method stub
		if(stringCellValue.length()<=0||stringCellValue.length()>50)
			return "氏名はNULLではありません や 長さは50を超えてはなりません";
			
			return "";
	}

	public String checkEmailFormat(String stringCellValue) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 Pattern pattern = Pattern.compile(regex);
		 if(pattern.matcher(stringCellValue).matches())
		return "";
		 else return  "メールのフォーマットエラー。";
	}


	public String checkBoolean(boolean booleanCellValue) {
		
		
		return "";
	}
	public String checkBoolean(String booleanCellValue) {
		// TODO Auto-generated method stub
		
		
		return "Booleanタイプ エラー";
	}

	public String checkUserPasswordFormat(XSSFCell cell, XSSFCell cell2) {
		if(login.checkUserIdExistOrNot(cell.getStringCellValue())) {
			if(cell2!=null&&!cell2.getStringCellValue().equals("")) {
				System.out.println(cell2.getStringCellValue());
				return "登録ユーザーのパスワードが変更できません。";
			}
		}
		else {
			
		if(cell2==null||cell2.getStringCellValue().length()<6||cell2.getStringCellValue().length()>50) {
			return "パスワードの長さは6から50にする必要があります";
		}
		
		}
		
		
		return "";
	}
}
	


	
	//Password length size should be 6 to 50
	
	
	
	
