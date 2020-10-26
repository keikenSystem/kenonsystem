package com.keiken.kenonuserinterface.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.repository.RepoUser;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.security.PasswordEncoder;

@Service
public class AdminOperationService {

	private MultipartFile readExcelData;

	@Autowired
	RepoUserLoginOperation repoUserLoginOperation;
	
public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Autowired
	RepoUser repoUser;

	@Autowired
	PasswordEncoder passEncoder;

	// Read data from db
	public void readDataFromDBandDownload(String filePath) throws FileNotFoundException, IOException {

		ArrayList<EmployeeInfo> userList = new ArrayList<EmployeeInfo>();
		System.out.println("reading database value");
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		String[] headers = { "社員番号", "氏名", "カナ氏名", "部門", "mail", "パスワード", "管理権限" };

		userList = (ArrayList<EmployeeInfo>) repoUser.findAll();
		writeExcel(userList, headers, filePath);

	}
	// Write database data to excel

	public void writeExcel(ArrayList<EmployeeInfo> userList, String headers[], String pathName)
			throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("user_list");

		int rowCount = 0;
		Row row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			row.createCell(i).setCellValue(headers[i]);
		}

		for (EmployeeInfo user : userList) {
			row = sheet.createRow(++rowCount);
			row.createCell(0).setCellValue(user.getUserId());
			row.createCell(1).setCellValue(user.getFullName());
			row.createCell(2).setCellValue(user.getFullNameInKata());
			row.createCell(3).setCellValue(user.getDepartment());
			row.createCell(4).setCellValue(user.getEmail());
			row.createCell(5).setCellValue("");
			row.createCell(6).setCellValue(user.isAdmin());

		}

		try (FileOutputStream outputStream = new FileOutputStream(pathName)) {
			workbook.write(outputStream);
		}

	}

	// find userId from excel

	private Map<String, Integer> getUserIdFromExcel(MultipartFile readExcelData) throws IOException {
		
		if(!hasExcelFormat(readExcelData))
			System.out.println("file error");
			
			
		Map<String, Integer> userIdData = new HashMap();
		this.readExcelData = readExcelData;
		
		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());

		XSSFSheet worksheet = workbook.getSheet("user_list");

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);
			int index = i;
			String userId = row.getCell(0).getStringCellValue();
			System.out.println(" getUsed function "+index+" "+userId);
			userIdData.put(userId, index);
			

		}
		System.out.println(userIdData);
		return userIdData;

	}

	// Check match or not

	private boolean matchWithData(EmployeeInfo user, int index) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());
		XSSFSheet worksheet = workbook.getSheet("user_list");
		XSSFRow row = worksheet.getRow(index);
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		

		if (row.getCell(0).getStringCellValue().equals(user.getUserId())
				&& row.getCell(1).getStringCellValue().equals(user.getFullName())
				&& row.getCell(2).getStringCellValue().equals(user.getFullNameInKata())
				&& row.getCell(3).getStringCellValue().equals(user.getDepartment())
				&& row.getCell(4).getStringCellValue().equals(user.getEmail())
				&& row.getCell(5).getStringCellValue().equals("")
				&& row.getCell(6).getBooleanCellValue() == user.isAdmin())
			return true;

		return false;
	}

	// Update database
	private void updateDbWithNewData(EmployeeInfo user, int index) throws IOException {
		// TODO Auto-generated method stub

		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());
		XSSFSheet worksheet = workbook.getSheet("user_list");
		XSSFRow row = worksheet.getRow(index);
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限

		user.setUserId(row.getCell(0).getStringCellValue());
		user.setFullName(row.getCell(1).getStringCellValue());
		user.setFullNameInKata(row.getCell(2).getStringCellValue());
		user.setDepartment(row.getCell(3).getStringCellValue());
		user.setEmail(row.getCell(4).getStringCellValue());
		user.setAdmin(row.getCell(6).getBooleanCellValue());
		repoUser.save(user);

	}
	// remove user from database

	private void removeUserFromDb(EmployeeInfo user) {
		repoUser.deleteById(user.getUserId());
		repoUserLoginOperation.deleteById(user.getUserId());

	}

	// Add user to database
	private void addUserToDb(String key, int value) throws IOException {
		// TODO Auto-generated method stub
		EmployeeInfo user = new EmployeeInfo();
		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());
		XSSFSheet worksheet = workbook.getSheet("user_list");
		XSSFRow row = worksheet.getRow(value);
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限

		RegistrationInfo registedUser = new RegistrationInfo();

		user.setUserId(row.getCell(0).getStringCellValue());
		user.setFullName(row.getCell(1).getStringCellValue());
		user.setFullNameInKata(row.getCell(2).getStringCellValue());
		user.setDepartment(row.getCell(3).getStringCellValue());
		user.setEmail(row.getCell(4).getStringCellValue());
		user.setAdmin(row.getCell(6).getBooleanCellValue());
		registedUser.setUserId(row.getCell(0).getStringCellValue());
		registedUser.setPassword(passEncoder.encodedPassword(row.getCell(5).getStringCellValue()));
		registedUser.setToken("");
		repoUser.save(user);
		repoUserLoginOperation.save(registedUser);

	}
	private boolean hasExcelFormat(MultipartFile file) {

	        if (!TYPE.equals(file.getContentType())) {
	            return false;
	        }

	        return true;
	    }

	// Update , delete data to database based on excel

	public void addUserOrmodifyUser(MultipartFile readExcelData) throws IOException {
		System.out.println("Entering into registraoin");
		Map<String, Integer> excelIdList = new HashMap<String, Integer>();
		excelIdList.clear();
		// TODO Auto-generated method stub
		 excelIdList= getUserIdFromExcel(readExcelData);
		

		ArrayList<EmployeeInfo> userList = new ArrayList<EmployeeInfo>();
		System.out.println("reading database value");
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		userList = (ArrayList<EmployeeInfo>) repoUser.findAll();

		for (EmployeeInfo user : userList) {
			System.out.println(" user " +user);

			if (excelIdList.get(user.getUserId())!=null) {

				int index = excelIdList.get(user.getUserId());
				if (!matchWithData(user, index)) {
					System.out.println("need to update for index and user"+ index+"  "+user);
					updateDbWithNewData(user, index);
					System.out.println("updaed successfull for "+user);
					
				}
					excelIdList.remove(user.getUserId());
			} else {
				System.out.println("not in excel "+user.getUserId());
				removeUserFromDb(user);
				System.out.println("removed successfull");
				
			}
		}
		if(excelIdList.size()!=0)
		for (Map.Entry<String, Integer> id : excelIdList.entrySet()) {
			System.out.println("need to add userId "+id.getKey());
			addUserToDb(id.getKey(), id.getValue());
			System.out.println("user id "+id.getKey()+" "+"added succefully");
		}

	}

}
