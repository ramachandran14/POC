package com.ofs.maven.Impl;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

public class MailServiceImpl {

	public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("shankarramachandran404@gmail.com");
        mailSender.setPassword("Password@ofs12345");
         
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }

	@RequestMapping("/sendMail")
	@Scheduled(fixedRate=5000)
	public void printMessage() {
		JavaMailSender mailSender = getJavaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("chandru14beece@gmail.com");
            helper.setText("Greetings :) Hi, You now learnt Spring Boot");
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error while sending mail ..");
        }
        mailSender.send(message);
        System.out.println("Mail Sent Success!");
    }
}