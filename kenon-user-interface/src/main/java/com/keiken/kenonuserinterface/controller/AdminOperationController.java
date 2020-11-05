package com.keiken.kenonuserinterface.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.keiken.kenonuserinterface.service.AdminOperationService;

@Controller
public class AdminOperationController {

	@Autowired
	AdminOperationService adminOperationService;

	// Get method for add or remove user

	@RequestMapping(value = "admin/add_or_remove_user", method = RequestMethod.GET)
	public String showUpdateUserView(ModelMap model, HttpSession session, HttpServletRequest request) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null || role == "user") {
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}

		return "add_or_remove_user";

	}

	// show view of add or remove user

	@RequestMapping(value = "/admin/add_or_remove_user", method = RequestMethod.POST)
	public String showUpdateUserOperation(ModelMap model, HttpSession session,
			@RequestParam("importedFile") MultipartFile readExcelData) throws IOException {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null || role == "user") {
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return "redirect:/login";
		}
		String errorCheck = adminOperationService.hasError(readExcelData);
		if (errorCheck == "") {
			adminOperationService.addUserOrmodifyUser(readExcelData);
			model.put("errorMessage","ユーザの取り込みしました");
			System.out.println("operation successfull");
		} else {
			model.put("errorMessage", "ERROR <br>" + errorCheck);
			System.out.println("operation error");
		}

		return "add_or_remove_user";
	}

	@RequestMapping(value = "admin/download/userlist.xlsx", method = RequestMethod.GET)
	public void downloadUserListExcelFile(HttpSession session, HttpServletResponse response) throws IOException {

		String role = (String) session.getAttribute("role");
		if (role != "admin")
			response.sendRedirect("/login");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=user_list.xlsx");
		ByteArrayInputStream stream = adminOperationService.readDataFromDBandDownload();
		IOUtils.copy(stream, response.getOutputStream());

		// For testing List
//		Date date = new Date();
//		adminOperationService.showHealthInfo(date);

	}

	// Show list
	@RequestMapping(value = "admin/user_list", method = RequestMethod.GET)
	public ModelAndView showSearchView(ModelMap model, HttpSession session, HttpServletRequest request) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null || role == "user") {
			session.removeAttribute("userId");
			session.removeAttribute("role");
			session.removeAttribute("isLoggedIn");
			return new ModelAndView("redirect:/login");
		}

		String Max = adminOperationService.addOrSubtracDate(5);
		String Min = adminOperationService.addOrSubtracDate(-60);
		String today = adminOperationService.addOrSubtracDate(0);

		List<String> departmentList = adminOperationService.getDepartmentList();

		model.put("Min", Min);
		model.put("Max", Max);
		model.put("today", today);
		model.put("departments", departmentList);
		return new ModelAndView("show_list", model);

	}

	// Show list by excel file

	@RequestMapping(value = "admin/user_list", method = RequestMethod.POST)
	public void getUserHealthStatus(ModelMap model, @RequestParam String selectedDate, @RequestParam String department,
			HttpSession session, HttpServletResponse response) throws IOException {
		
		String userSession = (String) session.getAttribute("userId");
		String role = (String) session.getAttribute("role");
		if (!role.equals("admin"))
			response.sendRedirect("/kenon/login");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=fileOutput.xlsx");
		ByteArrayInputStream stream = adminOperationService.readHealthInfo(selectedDate, department);
		IOUtils.copy(stream, response.getOutputStream());
		

	}

}
