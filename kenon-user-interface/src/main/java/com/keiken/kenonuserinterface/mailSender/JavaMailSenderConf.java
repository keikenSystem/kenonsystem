package com.keiken.kenonuserinterface.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.service.EmailControlService;
import com.keiken.kenonuserinterface.service.UserDataService;

//Service for create Email message and  send Email to defined email id, send reminder

@Service
public class JavaMailSenderConf {

	@Autowired
	EmailControlService emailControlService;

	@Autowired
	UserDataService userDataService;

	@Autowired
	JavaMailSender javaMailSender;

	// Arguments Email id, and userId

	public void sendEmail(String mail, String userId) {

		SimpleMailMessage msg = new SimpleMailMessage();
		String token = emailControlService.createTokenForPasswordReset(userId);
		String name = userDataService.findName(userId);
		msg.setTo(mail);
		msg.setSubject("（ケンンシステム）パスワード再設定のメール");
		msg.setText(name + "様" + "\n\n"
				+ "パスワードをリセットするには次のリンクをクリックしてください。\n http://localhost:8086/kenon/new_password_set?userId=" + userId
				+ "&token=" + token + "\nこのURLにクリックしてパスワードリセットください");

		javaMailSender.send(msg);
	}

	// Mail sender for reminder

	public void sendReminder(String email) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("体温を入力しなかった場合のリマインドメール");
		msg.setText("本日体温を入力してください。\nhttp://localhost:8086/kenon/login/");
		javaMailSender.send(msg);

	}

}
