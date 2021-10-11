package com.example.springemailclient;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
	
	public void sendSimpleMail(String toEmail,
							   String body,
							   String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("akashm1509@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		logger.info("Message sent successfully....");
	}
	
	public void sendEmailAttachment(String toEmail,
									String body,
									String subject,
									String attachment) throws MessagingException {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		
		mimeMessageHelper.setFrom("akashm1509@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystem =
					new FileSystemResource(new File(attachment));
		
		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
		
		mailSender.send(mimeMessage);
		logger.info("Message with attachment sent successfully....");
		
	}
}
