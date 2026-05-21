package com.ous.aethererp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    // Send welcome email
    public void sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Welcome to Aether ERP platform");
        message.setText("Hello, " + name + "\n\nThanks for registering with us!\n\nRegards, \nAether Team!");
        mailSender.send(message);
    }

    // Send email for resetting OTP
    public void sendResetOTPEmail(String toEmail, String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for resetting your password is "+ otp+ ". " +
                "Use this OTP to proceed with resetting your password\n\nRegards, \nAether Team!");
        mailSender.send(message);
    }
}
