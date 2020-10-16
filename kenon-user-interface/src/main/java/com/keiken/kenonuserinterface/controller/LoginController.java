package com.keiken.kenonuserinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.keiken.kenonuserinterface.mailSender.JavaMailSenderConf;
import com.keiken.kenonuserinterface.service.LoginService;

@Controller
@SessionAttributes("userId")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	JavaMailSenderConf mailService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(ModelMap model) {
		System.out.println("get requested");
		return "login";
	}
	

	@RequestMapping(value="/login/recover",method=RequestMethod.GET)
	public String recoverPassword() {
		return "recover_pass";
		
	}
	
	@RequestMapping(value="/login/recover",method=RequestMethod.POST)
	public String recoverPassword(ModelMap model, @RequestParam String userEmail) {
		
	mailService.sendEmail(userEmail);
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(value="/login/",method=RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		boolean isValidUser = loginService.validated(userId, password);
		
		if(!isValidUser) {
		  model.put("errorMessage","Invalid Credentials");
		  return "login";
		}
		model.put("userId", userId);
		model.put("password", password);
		return "welcome";
	}

	

}
