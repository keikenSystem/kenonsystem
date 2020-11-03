package com.keiken.kenonuserinterface.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keiken.kenonuserinterface.model.DatewiseUserHandler;
import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.repository.RepoLogger;
import com.keiken.kenonuserinterface.repository.RepoUser;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.repository.RepoUserUpdatedTime;
import com.keiken.kenonuserinterface.security.PasswordEncoder;
import com.keiken.kenonuserinterface.validator.CustomValidator;

@Service
public class AdminOperationService {

	private MultipartFile readExcelData;

	@Autowired
	RepoUserLoginOperation repoUserLoginOperation;

	@Autowired
	RepoUserUpdatedTime repoUpdatedTime;
	@Autowired
	RepoLogger repoLogger;

	@Autowired
	CustomValidator isValid;

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Autowired
	RepoUser repoUser;

	@Autowired
	PasswordEncoder passEncoder;

	// Read data from db
	public ByteArrayInputStream readDataFromDBandDownload() {

		ArrayList<EmployeeInfo> userList = new ArrayList<EmployeeInfo>();
		System.out.println("reading database value");
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		String[] headers = { "社員番号", "氏名", "カナ氏名", "部門", "mail", "パスワード", "管理権限" };

		userList = (ArrayList<EmployeeInfo>) repoUser.findAll();
		return writeExcel(userList, headers);

	}
	// Write database data to excel

	public ByteArrayInputStream writeExcel(ArrayList<EmployeeInfo> userList, String headers[]) {

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
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

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// find userId from excel

	private Map<String, Integer> getUserIdFromExcel(MultipartFile readExcelData) throws IOException {

		if (!hasExcelFormat(readExcelData))
			System.out.println("file error");

		Map<String, Integer> userIdData = new HashMap();
		this.readExcelData = readExcelData;

		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());

		XSSFSheet worksheet = workbook.getSheet("user_list");

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);
			int index = i;
			String userId = row.getCell(0).getStringCellValue();
			System.out.println(" getUsed function " + index + " " + userId);
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
		RegistrationInfo registedUser = new RegistrationInfo();
		DatewiseUserHandler tmData = new DatewiseUserHandler();

		XSSFWorkbook workbook = new XSSFWorkbook(readExcelData.getInputStream());
		XSSFSheet worksheet = workbook.getSheet("user_list");
		XSSFRow row = worksheet.getRow(value);
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限

		user.setUserId(row.getCell(0).getStringCellValue());
		user.setFullName(row.getCell(1).getStringCellValue());
		user.setFullNameInKata(row.getCell(2).getStringCellValue());
		user.setDepartment(row.getCell(3).getStringCellValue());
		user.setEmail(row.getCell(4).getStringCellValue());
		user.setAdmin(row.getCell(6).getBooleanCellValue());
		repoUser.save(user);

		registedUser.setUserId(row.getCell(0).getStringCellValue());
		registedUser.setPassword(passEncoder.encodedPassword(row.getCell(5).getStringCellValue()));
		registedUser.setToken("");
		repoUserLoginOperation.save(registedUser);
		tmData.setUserId(key);
		repoUpdatedTime.save(tmData);

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
		excelIdList = getUserIdFromExcel(readExcelData);

		ArrayList<EmployeeInfo> userList = new ArrayList<EmployeeInfo>();
		System.out.println("reading database value");
		// 社員番号,氏名,カナ氏名,部門,mail,パスワード,管理権限
		userList = (ArrayList<EmployeeInfo>) repoUser.findAll();

		for (EmployeeInfo user : userList) {
			System.out.println(" user " + user);

			if (excelIdList.get(user.getUserId()) != null) {

				int index = excelIdList.get(user.getUserId());
				if (!matchWithData(user, index)) {
					System.out.println("need to update for index and user" + index + "  " + user);
					updateDbWithNewData(user, index);
					System.out.println("updaed successfull for " + user);

				}
				excelIdList.remove(user.getUserId());
			} else {
				System.out.println("not in excel " + user.getUserId());
				removeUserFromDb(user);
				System.out.println("removed successfull");

			}
		}
		if (excelIdList.size() != 0)
			for (Map.Entry<String, Integer> id : excelIdList.entrySet()) {
				System.out.println("need to add userId " + id.getKey());
				addUserToDb(id.getKey(), id.getValue());
				System.out.println("user id " + id.getKey() + " " + "added succefully");
			}

	}

	// Finding error for Importing Excel file such as blank row, validated input
	// data

	public String hasError(MultipartFile readExcelData2) {

		String[] headers = { "社員番号", "氏名", "カナ氏名", "部門", "mail", "パスワード", "管理権限" };
		String errorCollector = "";
		if (!hasExcelFormat(readExcelData2)) {
			errorCollector = "file type error ";
		}
		if (!errorCollector.equals(""))
			return errorCollector;

		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(readExcelData2.getInputStream());
			XSSFSheet worksheet = workbook.getSheet("user_list");

			for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				if (row == null)
					return "row should not be blank for row " + (i + 1);

				if (i == 0) {
					if (row.getCell(0).getStringCellValue().equals(headers[0])
							&& row.getCell(1).getStringCellValue().equals(headers[1])
							&& row.getCell(2).getStringCellValue().equals(headers[2])
							&& row.getCell(3).getStringCellValue().equals(headers[3])
							&& row.getCell(4).getStringCellValue().equals(headers[4])
							&& row.getCell(5).getStringCellValue().equals(headers[5])
							&& row.getCell(6).getStringCellValue().equals(headers[6])) {
						System.out.println("Column is fine");

					} else {
						errorCollector = "column name error";

						return errorCollector;
					}

				} else {

					if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkUserId(row.getCell(0).getStringCellValue());
					else
						return "user id can't be null " + "at " + "row " + (i + 1);
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(0).getStringCellValue() + "at " + "row "
								+ (i + 1);

					if (row.getCell(1) != null && row.getCell(1).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkUserNameFormat(row.getCell(1).getStringCellValue());
					else
						return "user name can't be null " + "at " + "row " + (i + 1);
					;
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(1).getStringCellValue() + "at " + "row "
								+ (i + 1);

					if (row.getCell(2) != null && row.getCell(2).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkUserNameFormat(row.getCell(2).getStringCellValue());
					else
						return "user name katakana can't be null " + "at " + "row " + (i + 1);
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(2).getStringCellValue() + "at " + "row "
								+ (i + 1);

					if (row.getCell(3) != null && row.getCell(3).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkUserNameFormat(row.getCell(3).getStringCellValue());
					else
						return "department name can't be null " + "at " + "row " + (i + 1);
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(3).getStringCellValue() + "at " + "row "
								+ (i + 1);

					if (row.getCell(4) != null && row.getCell(4).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkEmailFormat(row.getCell(4).getStringCellValue());
					else
						return "Email can't be null at" + "row " + (i + 1);
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(4).getStringCellValue() + "at " + "row "
								+ (i + 1);

					errorCollector = isValid.checkUserPasswordFormat(row.getCell(0), row.getCell(5));
					if (errorCollector != "")
						return errorCollector + " for " + "row " + (i + 1);

					if (row.getCell(6) != null && row.getCell(6).getCellType() != Cell.CELL_TYPE_BLANK)
						errorCollector = isValid.checkBoolean(row.getCell(6).getBooleanCellValue());
					else {
						System.out.println(row.getCell(6).CELL_TYPE_BOOLEAN);
						return "Administrator can't be null and value must be TRUE or FALSE " + "at " + "row "
								+ (i + 1);
					}
					if (errorCollector != "")
						return errorCollector + " for " + row.getCell(6).getBooleanCellValue() + "at " + "row "
								+ (i + 1);

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "value type error";

		}

		return "";
	}

//Control view page by limitation for selectable date

	public String addOrSubtracDate(int i) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, i);
		return String.format("%04d-%02d-%02d", (cal.getTime().getYear() + 1900), (cal.getTime().getMonth() + 1),
				(cal.getTime().getDate()));

	}

//Format date based on 2020-03-12

	public String formatDate(Date date) {
		return String.format("%04d-%02d-%02d", (date.getYear() + 1900), (date.getMonth() + 1), (date.getDate()));
	}

//Conver Locale date into Date

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	// Change date by integer number of date

	public Date changeDate(String date, int i) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate date1 = LocalDate.parse(date, formatter);

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(convertToDateViaSqlDate(date1));
		cal.add(Calendar.DATE, i);

		return cal.getTime();

	}

	// Get available department list

	public List<String> getDepartmentList() {

		List<String> departments = new ArrayList<String>();
		departments.add("全で");

		departments.addAll(repoUser.findDistictDepartmentName());

		return departments;
	}

	// Show list based on queries in excel format

	public ByteArrayInputStream readHealthInfo(String selectedDate, String department) {

		List<TemperatureAndSymtomsMesurement> getData;
		List<String> userIdList;
		List<String> dateList = new ArrayList<>();

		if (department.equals("全で")) {
			getData = repoLogger.getHealthInfoForAll(selectedDate, changeDate(selectedDate, -4));
			userIdList = repoLogger.getUserIdList(selectedDate, changeDate(selectedDate, -4));
		} else {
			getData = repoLogger.getHealthInfoByDate(selectedDate, changeDate(selectedDate, -4), department);
			userIdList = repoLogger.getUserIdList(selectedDate, changeDate(selectedDate, -4), department);
		}

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("user_output");

			int val = -4;
			Row row = sheet.createRow(0);
			Row row1 = sheet.createRow(1);
			String[] headers = { "社員番号", "氏名", "カナ氏名", "所属部署" };

			// Fixed first two rows with title

			for (int i = 0; i <= 13; i++) {

				dateList.add("");
				if (i < 4) {
					row.createCell(i).setCellValue(headers[i]);
					row1.createCell(i).setCellValue("");
				} else {
					if (i % 2 == 0) {

						row.createCell(i).setCellValue(formatDate(changeDate(selectedDate, val)));
						dateList.add(i, row.getCell(i).getStringCellValue());
						row1.createCell(i).setCellValue("体温");
						++val;

					} else {
						row.createCell(i).setCellValue("");
						row1.createCell(i).setCellValue("症状");
					}

				}
			}

			for (int id = 0; id < userIdList.size(); id++) {

				row = sheet.createRow(id + 2);
				for (int j = 0; j <= 13; j++)
					row.createCell(j);

			}

			for (TemperatureAndSymtomsMesurement info : getData) {

				String userId = info.getUserId();
				EmployeeInfo userInfo = repoUser.findById(userId).get();
				String dateInformat = formatDate(info.getLastUsedTime());
				int rowId = userIdList.indexOf(userId) + 2;
				int dateCol = dateList.indexOf(dateInformat);
				sheet.getRow(rowId).getCell(0).setCellValue(userId);
				sheet.getRow(rowId).getCell(1).setCellValue(userInfo.getFullName());
				sheet.getRow(rowId).getCell(2).setCellValue(userInfo.getFullNameInKata());
				sheet.getRow(rowId).getCell(3).setCellValue(userInfo.getDepartment());
				sheet.getRow(rowId).getCell(dateCol).setCellValue(info.getTemperature());
				sheet.getRow(rowId).getCell(dateCol + 1).setCellValue(info.isGotSymtoms());

			}

			for (int i = 0; i <= 13; i++) {
				sheet.autoSizeColumn(i);
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
