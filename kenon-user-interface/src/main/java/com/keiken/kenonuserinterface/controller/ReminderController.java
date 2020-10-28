package com.keiken.kenonuserinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.keiken.kenonuserinterface.service.EmailControlService;

@Controller
public class ReminderController {
	
	//cron expression <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year> <command>

	//setting reminder email
	
	@Autowired
	EmailControlService emailService;
	
	@Scheduled(cron="0 00 10 * * ?")
	@Scheduled(cron="0 00 12 * * ?")
	public void reminderEmailSender() {
		emailService.sendReminderEmail();
	}

}
