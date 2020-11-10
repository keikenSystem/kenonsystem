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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		return new ModelAndView("login",model);
	}

	
	// Permission controller for logged in
	//Helper  LoginService, UserDataService
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView showWelcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password,RedirectAttributes attributes) throws NoSuchAlgorithmException {
		 RedirectView logmv = new RedirectView("login");
		  userId = userId.trim();
		if(!loginService.checkUserIdExistOrNot(userId)) {
			attributes.addFlashAttribute("errorMessage", "社員番号エラー");
			return logmv;
		}
		
		boolean isValidUser = loginService.validated(userId, password);
   
		if (!isValidUser) {
			attributes.addFlashAttribute("errorMessage", "社員番号とパスワードが一致しません");
			return logmv;
		}
      session.setAttribute("userId", userId);
      session.setAttribute("isLoggedIn", true);
     
		String role="admin";
		  
		if(!userDataService.isAdmin(userId))
			role="user";
		
		session.setAttribute("role", role);
		
		attributes.addAttribute("userId",userId);
		
		 return new RedirectView("user_information");
		
	}
	
	
	
//  In case for forgot password, rescue function
// Helper JavaMailSenderConf used(EmailControlService)

	@RequestMapping(value = "/login/recover", method = RequestMethod.GET)
	public String passwordRecoverView(ModelMap model) {
		session.setAttribute("isVisit", true);
		return "recover_pass";
		
	}

	// Password Recover operation 
	
	@RequestMapping(value = "/login/recover", method = RequestMethod.POST)
	public RedirectView recoverPasswordOperation(ModelMap model, @RequestParam String userEmail, RedirectAttributes attr) {
      
		userEmail = userEmail.trim();
		
		if(session.getAttribute("isVisit")==null) return new RedirectView("login");
       String errorMsg = "メールが登録されていません。";
       if(!emailControlService.isEmailIdExistOrNot(userEmail))
       {
    	   System.out.println("email doesn't found");
    	   attr.addFlashAttribute("errorMessage",errorMsg);
    	   return new RedirectView("recover");
       }
       
		String userId= emailControlService.getUserIdByEmailId(userEmail);
		
		mailService.sendEmail(userEmail,userId);
	   
	    attr.addFlashAttribute("errorMessage","メールチェックをお願いします");
	    removedAllSessionData();
       return new RedirectView("/kenon/login");

	}
	
	
	// Logout Method 
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout() {
		
		removedAllSessionData();
	
		return "redirect:/login";
	}
	
	
	private void removedAllSessionData() {
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
       session.removeAttribute("isVisit");
	}
	
	

}
