package com.keiken.kenonuserinterface.controller;

import java.security.NoSuchAlgorithmException;

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
import com.keiken.kenonuserinterface.service.EmailControlService;
import com.keiken.kenonuserinterface.service.UserDataService;

/* Login , Logout and password recover using email */

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired 
	UserDataService userDataService;

	@Autowired
	JavaMailSenderConf mailService;
	
	@Autowired
	EmailControlService emailControlService;
  
	@Autowired
	HttpSession session;
	
 //show login page 
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}
	
// Get Login page
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(ModelMap model) {
		System.out.println("get requested");
		model.put("errorMessage","");
		return new ModelAndView("login",model);
	}

	
	// Permission controller for logged in
	//Helper  LoginService, UserDataService
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView showWelcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) throws NoSuchAlgorithmException {
		  ModelAndView logmv = new ModelAndView("login");
		  
		if(!loginService.checkUserIdExistOrNot(userId)) {
			model.put("errorMessage", "社員番号エラー");
			return logmv;
		}
		
		boolean isValidUser = loginService.validated(userId, password);
   
		if (!isValidUser) {
			model.put("errorMessage", "社員番号とパスワードが一致しません");
			return logmv;
		}
      session.setAttribute("userId", userId);
      session.setAttribute("isLoggedIn", true);
     
		String role="admin";
		  
		if(!userDataService.isAdmin(userId))
			role="user";
		
		session.setAttribute("role", role);
		model.addAttribute("userId",userId);
		 return new ModelAndView("redirect:/user_information", model);
		
	}
	
	
	
//  In case for forgot password, rescure function
// Helper JavaMailSenderConf used(EmailControlService)

	@RequestMapping(value = "/login/recover", method = RequestMethod.GET)
	public String passwordRecoverView(ModelMap model) {
		session.setAttribute("isVisit", true);
		return "recover_pass";
		
	}

	// Password Recover operation 
	
	@RequestMapping(value = "/login/recover", method = RequestMethod.POST)
	public ModelAndView recoverPasswordOperation(ModelMap model, @RequestParam String userEmail) {
       if(session.getAttribute("isVisit")==null) return new ModelAndView("redirect:/login");
       String errorMsg = "Email is not registerd, please try again";
       if(!emailControlService.isEmailIdExistOrNot(userEmail))
       {
    	   model.put("errorMessage",errorMsg);
    	   return new ModelAndView("recover_pass",model);
       }
       
		String userId= emailControlService.getUserIdByEmailId(userEmail);
		mailService.sendEmail(userEmail,userId);
		System.out.print("check email");
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
       session.removeAttribute("isVisit");
       
       return new ModelAndView("redirect:/login");

	}
	
	
	// Logout Method 
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
		return "redirect:/login";
	}
	
	
	
	
	

}
