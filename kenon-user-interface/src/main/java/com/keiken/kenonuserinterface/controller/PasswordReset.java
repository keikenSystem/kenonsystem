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
			removedAllSessionData();
			return "redirect:/login";
		}

		return "reset_password";
	}
	// Password reset Operation and checking
	// Helper LoginService ,

	@RequestMapping(value = "/password_reset", method = RequestMethod.POST)
	public RedirectView passwordResetOperation(ModelMap model, @RequestParam String currentPassword,
			@RequestParam String newPassword, @RequestParam String confirmPassword, RedirectAttributes attr)
			throws NoSuchAlgorithmException {
		if (session.getAttribute("isLoggedIn") == null) {

			removedAllSessionData();

			return new RedirectView("/kenon/login");

		}
		newPassword = newPassword.trim();
		confirmPassword = confirmPassword.trim();
		String userId = (String) session.getAttribute("userId");
		String errorMsg = "";

		if (!loginService.validated(userId, currentPassword)) {
			errorMsg += "現在のパスワード 　エラー ";
			attr.addFlashAttribute("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new RedirectView("password_reset");
		}

		if (!newPassword.equals(confirmPassword)) {
			errorMsg += "新しいパスワード と パスワード確認 が一致しない ";
			attr.addFlashAttribute("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new RedirectView("password_reset");
		}
		if (confirmPassword.length() < 6 || confirmPassword.length() > 50) {
			errorMsg += "パスワードの長さは6から50にする必要があります ";
			attr.addFlashAttribute("errorMessage", errorMsg);
			System.out.println(errorMsg);
			return new RedirectView("password_reset");
		}

		// Change password

		loginService.resetPassword(userId, newPassword);
		//removedAllSessionData();
		System.out.println("success");
		attr.addAttribute("userId",session.getAttribute("userId"));

		return new RedirectView("/kenon/user_information");

	}

	// New password set with link provided by Email
	// Helper LoginService

	@RequestMapping(value = "/new_password_set", method = RequestMethod.GET)
	public ModelAndView newPasswordSetView(ModelMap model, @RequestParam String userId, @RequestParam String token) {
		session.setAttribute("userId", userId);
		session.setAttribute("token", token);
		if (!loginService.isTokenMatch(userId, token)) {
			session.removeAttribute(userId);
			return new ModelAndView("redirect:/login");
		}
		

		return new ModelAndView("/new_password_set");
	}

	// New Password setting

	@RequestMapping(value = "/new_password_set", method = RequestMethod.POST)
	public RedirectView newPasswordSetOperation(ModelMap model, @RequestParam String newPassword,
			@RequestParam String confirmPassword, RedirectAttributes attr) throws NoSuchAlgorithmException {

		String errorMsg = "";
		newPassword = newPassword.trim();
		confirmPassword = confirmPassword.trim();
		

		if (!newPassword.equals(confirmPassword)) {
			errorMsg += "新しいパスワード と パスワード確認 が一致しない ";
			attr.addFlashAttribute("errorMessage", errorMsg);
			attr.addAttribute("userId",session.getAttribute("userId"));
			attr.addAttribute("token",session.getAttribute("token"));
			System.out.println(errorMsg);
			return new RedirectView("/kenon/new_password_set");

		}
         
	
		if (confirmPassword.length() < 6 || confirmPassword.length() > 50) {
			errorMsg += "パスワードの長さは6から50にする必要があります ";
			attr.addFlashAttribute("errorMessage", errorMsg);
			attr.addAttribute("userId",session.getAttribute("userId"));
			attr.addAttribute("token",session.getAttribute("token"));
			return new RedirectView("/kenon/new_password_set");
		}
		// Change password
		String userId = (String) session.getAttribute("userId");
		loginService.resetPassword(userId, newPassword);
		loginService.removeToken(userId);
		removedAllSessionData();
		

		return new RedirectView("/kenon/login");

	}

	private void removedAllSessionData() {
		session.removeAttribute("userId");
		session.removeAttribute("role");
		session.removeAttribute("isLoggedIn");
		session.removeAttribute("isVisit");
		session.removeAttribute("token");
	}

}
