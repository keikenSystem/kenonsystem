package com.keiken.kenonuserinterface.controller;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Path error handler

@Controller
public class RoutesController implements ErrorController{
	private static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
	public String error(HttpSession session) {
		//System.out.println("Route controller");
		return "redirect:/login";
	}
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}



}
