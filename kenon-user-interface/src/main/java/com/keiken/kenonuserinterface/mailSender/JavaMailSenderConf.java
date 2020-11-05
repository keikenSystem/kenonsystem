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
				+ "&token=" + token +"\n(このURLは1回の変更のみ有効です。)"+"\n\n"+"================================================\n"+
				"本メールは送信専用メールです。返信はできません。"+"\n"+"問い合わせは下記にお願いします。"+"\n\n"+ " -本件問い合わせ先-：\n "+
				 "tel    :             026-228-6644  \n"+ "E-mail :  miyazawa@keiken.local \n"+
				
				
 "================================================"
				
				);

		javaMailSender.send(msg);
	}

	// Mail sender for reminder
	
	public void sendReminder(String email) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("リマインドメール");
		msg.setText("\n\n"+ "本日の体温及び風邪の症状等が未入力ですので、入力してください。\n"+
				"http://localhost:8086/kenon/login/"+
				  "\n                                   ケイケンシステム管理部 \n"+
				  "================================================================\n"+
				  "本メールは送信専用メールです。返信はできません。\n"+
				  "問い合わせは下記にお願いします。\n\n"+
				  "tel    :             026-228-6644  \n"+
				  "E-mail :        miyazawa@keiken.local \n"+
				  "=================================================================\n"
		
				);
		javaMailSender.send(msg);

	}

}
