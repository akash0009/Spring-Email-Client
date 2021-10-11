package com.example.springemailclient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailClientApplication {
	
	@Autowired
	private EmailSenderService mailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailClientApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerEmail() throws MessagingException {
		mailSender.sendEmailAttachment("kandisusmita8@gmail.com", 
								  	   "This is testing email please ignore it",
								  	   "Testing Email!!!",
								  	   "D:\\Akash\\kitty-kitten.gif");
	}
}
