package com.keiken.kenonuserinterface.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.keiken.kenonuserinterface.service.AdminOperationService;

@Controller
public class AdminOperationController {
	
	
	@Autowired
	AdminOperationService adminOperationService;
	
	//Get method for add or remove user
	
	@RequestMapping(value="admin/add_or_remove_user",method =RequestMethod.GET )
	public String showUpdateUserView(ModelMap model,HttpSession session, HttpServletRequest request) {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");	
		if(role==null||session.getAttribute("isLoggedIn")==null||role=="user"){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		try {
			adminOperationService.readDataFromDBandDownload("data/userlist.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add_or_remove_user";
	}
	
	//show view of add or remove user
	
	@RequestMapping(value="/admin/add_or_remove_user",method =RequestMethod.POST)
	public String showUpdateUserOperation(ModelMap model,HttpSession session,@RequestParam("importedFile") MultipartFile readExcelData) throws IOException {
		
				
		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");	
		if(role==null||session.getAttribute("isLoggedIn")==null||role=="user"){
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		
		adminOperationService.addUserOrmodifyUser(readExcelData);
		
		System.out.println("operation successfull");
		return "add_or_remove_user";
	}
	
	
	

}



