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

import com.keiken.kenonuserinterface.service.LoginService;

@Controller
public class PasswordReset {

	@Autowired
	HttpSession session;
	@Autowired
	LoginService loginService;

	
	
	@RequestMapping(value = "/password_reset", method = RequestMethod.GET)
	public String passwordResetView(ModelMap model) {

		if (session.getAttribute("isLoggedIn") == null) {
			session.removeAttribute("userId");
			session.removeAttribute("role");
			return "redirect:/login";
		}

		return "reset_password";
	}
	//Password reset Operation and checking 
	//Helper LoginService , 

	@RequestMapping(value = "/password_reset", method = RequestMethod.POST)
	public ModelAndView passwordResetOperation(ModelMap model, @RequestParam String currentPassword,
			@RequestParam String newPassword, @RequestParam String confirmPassword) throws NoSuchAlgorithmException {
		if (session.getAttribute("isLoggedIn") == null) {

			session.removeAttribute("userId");
			session.removeAttribute("role");
			
			return new ModelAndView("redirect:/login");

		}
		String userId = (String) session.getAttribute("userId");
		String errorMsg = "";

		if (!loginService.validated(userId, currentPassword)) {
			errorMsg += "現在のパスワード 　エラー ";
			model.put("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new ModelAndView("reset_password", model);
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errorMsg += "newPassword and confirm password doesn't match ";
			model.put("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new ModelAndView("reset_password", model);
		}
		if(confirmPassword.length()<6||confirmPassword.length()>50)
		{
			errorMsg += "password length should be 6 to 50 ";
			model.put("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new ModelAndView("reset_password", model);
		}

		// Change password

		loginService.resetPassword(userId, newPassword);

		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
		System.out.println("success");

		return new ModelAndView("redirect:/login");

	}
	
	//New password set with  link provided by Email
	//Helper LoginService 
	
	@RequestMapping(value = "/new_password_set", method = RequestMethod.GET)
	public ModelAndView newPasswordSetView(ModelMap model, @RequestParam String userId, @RequestParam String token) {
        session.setAttribute("userId", userId);
       if(!loginService.isTokenMatch(userId, token)) {
    	   session.removeAttribute(userId);
    	  return new ModelAndView("redirect:/login");
       }
       
		return new ModelAndView("/new_password_set");
	}
	
	//New Password setting

	@RequestMapping(value = "/new_password_set", method = RequestMethod.POST)
	public ModelAndView newPasswordSetOperation(ModelMap model,@RequestParam String newPassword, @RequestParam String confirmPassword) throws NoSuchAlgorithmException {
	
		String errorMsg = "";

		if (!newPassword.equals(confirmPassword)) {
			errorMsg += "newPassword and confirm password doesn't match ";
			model.put("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new ModelAndView("new_password_set", model);
		}

		// Change password
        String userId = (String) session.getAttribute("userId");
		loginService.resetPassword(userId, newPassword);
		loginService.removeToken(userId);


		return new ModelAndView("redirect:/login");

	}

}
