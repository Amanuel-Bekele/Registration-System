package com.waaproject.registrationsystem.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private JavaMailSender emailSender;

    @Autowired
    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendUserCreationEmail(String to, String subject, String message){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        emailSender.send(mailMessage);
    }
}
