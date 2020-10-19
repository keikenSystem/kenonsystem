package com.keiken.kenonuserinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.keiken.kenonuserinterface.mailSender.JavaMailSenderConf;
import com.keiken.kenonuserinterface.service.LoginService;
import com.keiken.kenonuserinterface.service.PageControlService;
import com.keiken.kenonuserinterface.service.UserDataService;

@Controller
@SessionAttributes("userId")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired 
	UserDataService userDataService;

	@Autowired
	JavaMailSenderConf mailService;

	@Autowired
	PageControlService pageControlService;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}
	


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		System.out.println("get requested");
		pageControlService.setLoggedIn(false);
		pageControlService.setVisitLogin(true);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView showWelcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		boolean isValidUser = loginService.validated(userId, password);
  System.out.println("logged in"+isValidUser);
   ModelAndView logmv = new ModelAndView("login");
		if (!isValidUser) {
			model.put("errorMessage", "Invalid Credentials");
			return logmv;
		}
	
      
		pageControlService.setLoggedId(userId);
		pageControlService.setLoggedIn(true);
		String role="admin";
		if(!userDataService.isAdmin(userId))
			role="user";
		model.addAttribute("role", role);
		 System.out.println(model.getAttribute("role"));
		 return new ModelAndView("redirect:/user_information", model);
		
	}
	

	@RequestMapping(value = "/user_information", method = RequestMethod.GET)
	public String showInfoInputPage(ModelMap model,@RequestParam String role) {
		System.out.println("user info get");
		model.addAttribute("role",role);
		if(pageControlService.isLoggedIn())		
		return "insert_info";
		else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/user_information", method = RequestMethod.POST)
	public String insertInfomation(ModelMap model) {
		
			System.out.println("Registerd");
			return "redirect:/login";
	}

	@RequestMapping(value = "/login/recover", method = RequestMethod.GET)
	public String recoverPassword(ModelMap model) {
		if (pageControlService.isVisitLogin())
			return "recover_pass";
		else
			return "redirect:/login";

	}

	@RequestMapping(value = "/login/recover", method = RequestMethod.POST)
	public String recoverPassword(ModelMap model, @RequestParam String userEmail) {

		mailService.sendEmail(userEmail);

		return "redirect:/login";

	}

}
