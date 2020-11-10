package com.keiken.kenonuserinterface.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.service.TemperatureDataService;
import com.keiken.kenonuserinterface.service.UserDataService;

/* This controller used to control request for insert information user page  */

/// Get and post method to show user information page 

@Controller
public class InputDataController {

	@Autowired
	UserDataService userDataService;
	@Autowired
	private TemperatureDataService tempDataService;

	@Autowired
	HttpSession session;

	// To Control admin input data

	@RequestMapping(value = "/admin/user_information", method = RequestMethod.GET)
	public String showAdminInfoInputPage(ModelMap model) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null) {
			removedAllSessionData();
			return "redirect:/login";
		}
		TemperatureAndSymtomsMesurement tempData = new TemperatureAndSymtomsMesurement();

		// Control is user already given or not

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		if (tempDataService.isUserInsertedDataTodayOrNot(userSession, currentTime)) {
			tempData = tempDataService.getTodaysData(userSession);
			model.put("temperature", tempData.getTemperature());
		}

		model.put("lastUsedDate", tempDataService.getLastUsedDateText(userSession));
		model.put("userName", userDataService.findName(userSession));
		model.put("userId", userSession);
		model.put("role", role);

		return "insert_info";

	}

	@RequestMapping(value = "/user/user_information", method = RequestMethod.GET)
	public String showUserInfoInputPage(ModelMap model) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null) {
			removedAllSessionData();
			return "redirect:/login";
		}
		TemperatureAndSymtomsMesurement tempData = new TemperatureAndSymtomsMesurement();

		// Control is user already given or not

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		if (tempDataService.isUserInsertedDataTodayOrNot(userSession, currentTime)) {
			tempData = tempDataService.getTodaysData(userSession);
			model.put("temperature", tempData.getTemperature());
		}

		model.put("lastUsedDate", tempDataService.getLastUsedDateText(userSession));
		model.put("userName", userDataService.findName(userSession));
		model.put("userId", userSession);
		model.put("role", role);
		return "insert_info";

	}

// To show both user and admin information 

	@RequestMapping(value = "user_information", method = RequestMethod.GET)
	public String showInfoInputPage(ModelMap model, @RequestParam String userId) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (!userSession.equals(userId) || role == null || session.getAttribute("isLoggedIn") == null) {
			removedAllSessionData();
			return "redirect:/login";
		}
		TemperatureAndSymtomsMesurement tempData = new TemperatureAndSymtomsMesurement();

		// Control is user already given or not

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		if (tempDataService.isUserInsertedDataTodayOrNot(userSession, currentTime)) {
			tempData = tempDataService.getTodaysData(userSession);
			model.put("temperature", tempData.getTemperature());
		}

		model.addAttribute("userId", userId);
		model.addAttribute("role", role);
		model.put("lastUsedDate", tempDataService.getLastUsedDateText(userId));
		model.put("userName", userDataService.findName(userId));
		model.put("userId", userId);
		return "insert_info";

	}

	@RequestMapping(value = "inputdata", method = RequestMethod.GET)
	public ModelAndView saveInfoDataToDb(ModelMap model, @RequestParam double temperature,
			@RequestParam int gotSymtom) {

		String userSession = (String) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");
		if (role == null || session.getAttribute("isLoggedIn") == null) {
			removedAllSessionData();
			return new ModelAndView("redirect:/login");
		}

		TemperatureAndSymtomsMesurement tempData = new TemperatureAndSymtomsMesurement();

		// Control is user already given or not

		// save data to db;
		if (gotSymtom == 0)
			tempData.setGotSymtoms(false);
		else
			tempData.setGotSymtoms(true);

		tempData.setUserId(userSession);
		tempData.setTemperature(temperature);

		tempDataService.addData(tempData);
		session.setAttribute("checkAlert", "success");

		model.addAttribute("role", session.getAttribute("role"));
		return new ModelAndView("redirect:/{role}/user_information", model);

	}

	private void removedAllSessionData() {
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
		session.removeAttribute("isVisit");
	}

}
