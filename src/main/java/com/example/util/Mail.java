package com.example.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mail {
	
	private final static Logger logger = LoggerFactory.getLogger(Mail.class);
	
	public static void mailSend() {
		System.out.println("StonePASS 통합인증앱 에러발생");
		
		String host = "smtp.naver.com";
        String user = "dbtmdfl4215@naver.com";  // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	    String password = "swe0409!@#";  // 패스워드     

	    // SMTP 서버 정보를 설정한다.
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", 587);
	    props.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        
	        //단일메일 보내기
	        //message.addRecipient(Message.RecipientType.TO, new InternetAddress("victory@swempire.co.kr"));
	        
	        //다중메일 보내기
	        InternetAddress[] addArray = new InternetAddress[2];
	        addArray[0] = new InternetAddress("victory@swempire.co.kr");
	        addArray[1] = new InternetAddress("kts3800@swempire.co.kr");
	        message.addRecipients(Message.RecipientType.TO, addArray);
	        
	        // 메일 제목
	        message.setSubject("StonePASS통합인증앱 서버 상태를 확인해 주세요");

	        // 메일 내용
	        message.setText("StonePASS APP서버에서 에러가 발생했습니다. StonePASS통합인증앱 서버를 확인해주세요");

	        // send the message
	        Transport.send(message);
//	        logger.info("[Mail] mailSend() SuccessMessageSend");

	    } catch (MessagingException e) {
//	    	logger.info("[Mail] mailSend() MessagingException");
	        e.printStackTrace();
	    }
	}
}
