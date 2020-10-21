package com.keiken.kenonuserinterface.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.keiken.kenonuserinterface.mailSender.JavaMailSenderConf;
import com.keiken.kenonuserinterface.service.LoginService;
import com.keiken.kenonuserinterface.service.PageControlService;
import com.keiken.kenonuserinterface.service.UserDataService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired 
	UserDataService userDataService;

	@Autowired
	JavaMailSenderConf mailService;
  
	@Autowired
	HttpSession session;

	
	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		System.out.println("get requested");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView showWelcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		  ModelAndView logmv = new ModelAndView("login");
		  
		if(!loginService.checkUserIdExistOrNot(userId)) {
			model.put("errorMessage", "社員番号エラー");
			return logmv;
		}
		
		boolean isValidUser = loginService.validated(userId, password);
       System.out.println("logged in"+isValidUser);
 
   
		if (!isValidUser) {
			model.put("errorMessage", "社員番号とパスワードが一致しません");
			return logmv;
		}
		
	
    //  pageControlService.setUserId(userId);
      session.setAttribute("userId", userId);
      session.setAttribute("isLoggedIn", true);
     
		String role="admin";
		  
		if(!userDataService.isAdmin(userId))
			role="user";
		//pageControlService.setRole(role);
		session.setAttribute("role", role);
		model.addAttribute("userId",userId);
		 return new ModelAndView("redirect:/user_information", model);
		
	}
	


	@RequestMapping(value = "/login/recover", method = RequestMethod.GET)
	public String recoverPassword(ModelMap model) {
		if (session.getAttribute("userId")!=null && session.getAttribute("role")!=null && (boolean)session.getAttribute("isLoggedIn")==true)
			return "recover_pass";
		else
			return "redirect:/login";

	}

	@RequestMapping(value = "/login/recover", method = RequestMethod.POST)
	public String recoverPassword(ModelMap model, @RequestParam String userEmail) {

		mailService.sendEmail(userEmail);
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");

		return "redirect:/login";

	}
	
	
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
		return "redirect:/login";
	}
	
	
	
	
	

}
