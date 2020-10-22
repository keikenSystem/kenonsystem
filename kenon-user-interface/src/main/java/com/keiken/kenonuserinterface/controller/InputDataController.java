package com.keiken.kenonuserinterface.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keiken.kenonuserinterface.service.UserDataService;

/* This controller used to control request for insert information user page  */



/// Get and post method to show user information page 



@Controller
public class InputDataController {


	@Autowired
	UserDataService userDataService;
	
	
	//To Control admin input data
	
	@RequestMapping(value = "/admin/user_information", method = RequestMethod.GET)
	public String showInfoInputPage(ModelMap model,HttpSession session) {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");	
		if(role==null||role.equals("user")||session.getAttribute("isLoggedIn")==null){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}


		model.put("lastUsedDate",userDataService.getLastUsedDateText(userSession));
		model.put("userName", userDataService.getName());
		model.put("userId", userSession);
		model.put("role", role);
		return "insert_info";
	
	}

// To show both user and admin information 
	
	
	@RequestMapping(value = "user_information", method = RequestMethod.GET)
	public String showInfoInputPage(ModelMap model,HttpSession session, @RequestParam String userId ) {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if(!userSession.equals(userId)||role==null||session.getAttribute("isLoggedIn")==null){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("role", role);
		model.put("lastUsedDate",userDataService.getLastUsedDateText(userId));
		model.put("userName", userDataService.getName());
		model.put(userId, userId);
		return "insert_info";
	
	}


}
