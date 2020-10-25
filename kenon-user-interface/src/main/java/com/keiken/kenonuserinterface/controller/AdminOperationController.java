package com.keiken.kenonuserinterface.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminOperationController {
	
	@RequestMapping(value="admin/add_or_remove_user",method =RequestMethod.GET )
	public String showUpdateUserView(ModelMap model,HttpSession session) {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");	
		if(role==null||session.getAttribute("isLoggedIn")==null||role=="user"){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		return "add_or_remove_user";
	}
	@RequestMapping(value="admin/add_or_remove_user",method =RequestMethod.POST )
	public String showUpdateUserOperation(ModelMap model,HttpSession session) {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");	
		if(role==null||session.getAttribute("isLoggedIn")==null||role=="user"){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		return "add_or_remove_user";
	}

}



