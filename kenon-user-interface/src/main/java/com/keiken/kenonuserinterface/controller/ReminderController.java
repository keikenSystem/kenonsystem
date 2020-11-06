package com.keiken.kenonuserinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.keiken.kenonuserinterface.service.AdminOperationService;
import com.keiken.kenonuserinterface.service.EmailControlService;

@Controller
public class ReminderController {

	// cron expression <second> <minute> <hour> <day-of-month> <month> <day-of-week>
	// <year> <command>

	// setting reminder email

	@Autowired
	EmailControlService emailService;
	@Autowired
	AdminOperationService adminService;

	@Scheduled(cron = "0 00 10 ? * MON-FRI")
	@Scheduled(cron = "0 00 12 ? * MON-FRI")
	public void reminderEmailSender() {
		emailService.sendReminderEmail();

	}


// Before two month data is removed
	
	@Scheduled(cron = "0 00 9 * * ?")
	public void removeData() {

		adminService.removeTemperatureDataBeforeTwoMonth();

	}

}
