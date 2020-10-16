package com.keiken.kenonuserinterface.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JavaMailSenderConf {
	
	@Autowired
	private JavaMailSender javaMailSender;
	public void sendEmail(String mail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String token="";
		msg.setTo(mail);
		msg.setSubject("testing with email");
		msg.setText("please reset your password with this link "+token);
		javaMailSender.send(msg);
	}

}
