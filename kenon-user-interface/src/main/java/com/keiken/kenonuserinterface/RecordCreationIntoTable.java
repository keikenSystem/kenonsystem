package com.keiken.kenonuserinterface;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




/*......Class to insert data into database table in runtime........... */


@Component
public class RecordCreationIntoTable implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
//
//	@Autowired
//	private RepoUser repoUser;
//
//	@Autowired
//	private RepoUserLoginOperation repoUserLoginOperation;
//
//	@Autowired
//	private RepoTemperatureAndSymtomsOperation repoTemperatureAndSymtomsOperation;
//	@Autowired
//    private PasswordEncoder pasen;
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		// Data model entity creation
//
//		EmployeeInfo emp = new EmployeeInfo();
//		RegistrationInfo regiInfo = new RegistrationInfo();
//		TemperatureAndSymtomsMesurement temp = new TemperatureAndSymtomsMesurement();
//
//		
//	emp.setUserId("u0001");
//		emp.setFullName("モミン");
//	emp.setFullNameInKata("モミン");
//		emp.setDepartment("ウェブ開発");
//		emp.setEmail("momin.ice16@gmail.com");
//		emp.setAdmin(true);
//	
//	  repoUser.save(emp);
//
//		emp.setUserId("u0002");
//		emp.setFullName("ロビ");
//		emp.setFullNameInKata("ロビー");
//		emp.setDepartment("ウェブ開発");
//		emp.setEmail("shekmomin07@gmail.com");
//		emp.setAdmin(false);
//		repoUser.save(emp);
//
//		regiInfo.setUserId("u0001");
//		regiInfo.setPassword(pasen.encodedPassword("test01"));
//		regiInfo.setToken("");
//		
//		repoUserLoginOperation.save(regiInfo);
//
//		regiInfo.setUserId("u0002");
//		regiInfo.setPassword(pasen.encodedPassword("test02"));
//		regiInfo.setToken("");
//	repoUserLoginOperation.save(regiInfo);
//
//		temp.setUserId("u0001");
//		temp.setTemperature(23.54);
//		temp.setGotSymtoms(false);
//		repoTemperatureAndSymtomsOperation.save(temp);
//
//		temp.setUserId("u0002");
//		temp.setTemperature(33.54);
//		temp.setGotSymtoms(true);
//		repoTemperatureAndSymtomsOperation.save(temp);
//
//	}


}
