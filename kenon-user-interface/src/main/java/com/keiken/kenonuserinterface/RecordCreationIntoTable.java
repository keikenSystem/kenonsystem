package com.keiken.kenonuserinterface;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.repository.RepoTemperatureAndSymtomsOperation;
import com.keiken.kenonuserinterface.repository.RepoUser;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;




/*......Class to insert data into database table in runtime........... */


@Component
public class RecordCreationIntoTable  {

//	@Autowired
//	private RepoUser repoUser;
//
//	@Autowired
//	private RepoUserLoginOperation repoUserLoginOperation;
//
//	@Autowired
//	private RepoTemperatureAndSymtomsOperation repoTemperatureAndSymtomsOperation;
//
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		// Data model entity creation
//
//		EmployeeInfo emp = new EmployeeInfo();
//		RegistrationInfo regiInfo = new RegistrationInfo();
//		TemperatureAndSymtomsMesurement temp = new TemperatureAndSymtomsMesurement();
//
//		Timestamp tim = new Timestamp(0);
////		emp.setUserId("u01");
////		emp.setFullName("モミン");
////		emp.setFullNameInKata("モミン");
////		emp.setDepartment("ウェブ開発");
////		emp.setCreateDate(tim);
////		emp.setLastUsedDate(tim);
////	  repoUser.save(emp);
//
////		emp.setUserId("u02");
////		emp.setFullName("ロビ");
////		emp.setFullNameInKata("ロビー");
////		emp.setDepartment("ウェブ開発");
////		emp.setCreateDate(tim);
////		emp.setLastUsedDate(tim);
////		repoUser.save(emp);
//
////		regiInfo.setUserId("u01");
////		regiInfo.setPassword("test01");
////		regiInfo.setToken("token01");
////		regiInfo.setRole("admin");
////		repoUserLoginOperation.save(regiInfo);
////
////		regiInfo.setUserId("u02");
////		regiInfo.setPassword("test02");
////		regiInfo.setToken("token02");
////		regiInfo.setRole("user");
////		repoUserLoginOperation.save(regiInfo);
//
//		temp.setUserId("u01");
//		temp.setTemperature(23.54);
//		temp.setGotSymtoms(false);
//		temp.setLastUsedTime(tim);
//		repoTemperatureAndSymtomsOperation.save(temp);
//
//		temp.setUserId("u02");
//		temp.setTemperature(33.54);
//		temp.setGotSymtoms(true);
//		temp.setLastUsedTime(tim);
//		repoTemperatureAndSymtomsOperation.save(temp);
//
//	}

}
