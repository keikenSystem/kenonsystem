package com.keiken.kenonuserinterface.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutesController implements ErrorController{
	private static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
	public String error() {
		return "redirect:/login";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
