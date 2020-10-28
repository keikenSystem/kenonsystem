package com.keiken.kenonuserinterface.service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.mailSender.JavaMailSenderConf;
import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.RegistrationInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.repository.RepoTemperatureAndSymtomsOperation;
import com.keiken.kenonuserinterface.repository.RepoUser;
import com.keiken.kenonuserinterface.repository.RepoUserLoginOperation;
import com.keiken.kenonuserinterface.security.PasswordEncoder;

@Service
public class EmailControlService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private String token;
	@Autowired
	private RepoUserLoginOperation repoUserLoginOperation;

	@Autowired
	RepoTemperatureAndSymtomsOperation repoTemperature;
	@Autowired
	RepoUser repoUser;

	@Autowired
	JavaMailSenderConf mailService;

	// Get Token for reseting password

	public String createTokenForPasswordReset(String userId) {
		byte[] array = new byte[32];
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		String randomText = generatedString;
		token = passwordEncoder.encodedPassword(randomText);

		RegistrationInfo user = repoUserLoginOperation.findById(userId).get();
		user.setToken(token);
		repoUserLoginOperation.save(user);
		return token;

	}

	// Get user Id using Email

	public String getUserIdByEmailId(String email) {
		List<EmployeeInfo> userList = repoUser.findUserByEmail(email);

		return userList.get(0).getUserId();
	}

	// Check inserted email id exist or not

	public boolean isEmailIdExistOrNot(String email) {

		List<EmployeeInfo> userList = repoUser.findUserByEmail(email);

		if (userList.size() == 0)
			return false;

		return true;
	}

	// Getters and setters for Token

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// Reminder sending function for input temperature

	public void sendReminderEmail() {

		Date currentDate = new Date();
		System.out.println(currentDate);

		List<TemperatureAndSymtomsMesurement> userUsedToday = repoTemperature.findEmailListUsedToday(currentDate);
		Iterable<TemperatureAndSymtomsMesurement> totalUser = repoTemperature.findAll();

		for (TemperatureAndSymtomsMesurement user : totalUser) {
			if (!userUsedToday.contains(user)) {
				EmployeeInfo getUserInfo = repoUser.findById(user.getUserId()).get();		
				mailService.sendReminder(getUserInfo.getEmail());
			}

	}

}
	
}
