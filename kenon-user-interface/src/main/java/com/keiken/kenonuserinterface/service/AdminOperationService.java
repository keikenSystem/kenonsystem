package com.keiken.kenonuserinterface.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.repository.RepoUser;

@Service
public class AdminOperationService {

	@Autowired
	RepoUser repoUser;
	public void readDataFromDBandDownload(String filePath) throws FileNotFoundException, IOException {
		
		
		
		ArrayList<EmployeeInfo> userList = new ArrayList<EmployeeInfo>();
		System.out.println("reading database value");
		//社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		String[] headers= {"社員番号","氏名","カナ氏名","部門","mail","パスワード","管理権限"};
		
		userList = (ArrayList<EmployeeInfo>) repoUser.findAll();
		writeExcel(userList,headers,filePath);
		
	
		
	}
	
	public void writeExcel(ArrayList<EmployeeInfo> userList,String headers[],String pathName) throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("user_list");
		
		int rowCount=0;
		Row row = sheet.createRow(0);
		for(int i=0;i<headers.length;i++) {
			row.createCell(i).setCellValue(headers[i]);
		}
		
		for(EmployeeInfo user: userList) {
			row = sheet.createRow(++rowCount);
		row.createCell(0).setCellValue(user.getUserId());
		row.createCell(1).setCellValue(user.getFullName());
		row.createCell(2).setCellValue(user.getFullNameInKata());
		row.createCell(3).setCellValue(user.getDepartment());
		row.createCell(4).setCellValue(user.getEmail());
		row.createCell(5).setCellValue("");
		row.createCell(6).setCellValue(user.isAdmin());		
			
		}
		
		try(FileOutputStream outputStream = new FileOutputStream(pathName)){
			workbook.write(outputStream);
		}
		
	}

	
	


}
