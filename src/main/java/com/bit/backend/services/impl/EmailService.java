package com.bit.backend.services.impl;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendTrialRequestEmail(String name, String email, String company, String message) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo("hasithavithanageict@gmail.com"); // where you receive the request
//        mail.setSubject("New Trial Request - Combat Fitness");
//        mail.setText(
//                "Name: " + name + "\n" +
//                        "Email: " + email + "\n" +
//                        "Gym Name: " + company + "\n" +
//                        "Message: " + message
//        );
//
//        mailSender.send(mail);
//    }

    @Autowired
    private JavaMailSender mailSender;

    public String sendTrialRequestEmail(String name, String email, String company, String messageContent) {
        try {
            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo("contact.combatfitness@gmail.com"); // recipient
            helper.setSubject("New Trial Request - Combat Fitness");

            // HTML email content
            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "  <style>" +
                    "    body { font-family: Arial, sans-serif; color: #333; }" +
                    "    .container { padding: 20px; background-color: #f9f9f9; border-radius: 8px; }" +
                    "    h2 { color: #2c3e50; }" +
                    "    p { line-height: 1.5; }" +
                    "    .label { font-weight: bold; color: #34495e; }" +
                    "    .footer { margin-top: 20px; font-size: 12px; color: #888; }" +
                    "    .button { " +
                    "       display: inline-block; " +
                    "       background-color:#28a745; " +
                    "       color:white; " +
                    "       padding:10px 20px; " +
                    "       text-decoration:none !important; " +
                    "       border-radius:5px; " +
                    "       margin-top:15px;" +
                    "    }" +
                    "  </style>" +
                    "</head>" +
                    "<body>" +
                    "  <div class='container'>" +
                    "    <h2>New Trial Request</h2>" +
                    "    <p><span class='label'>Name:</span> " + name + "</p>" +
                    "    <p><span class='label'>Email:</span> " + email + "</p>" +
                    "    <p><span class='label'>Gym Name:</span> " + company + "</p>" +
                    "    <p><span class='label'>Message:</span><br>" + messageContent + "</p>" +
                    "    <a href='https://yourdomain.com/accept-request?userId=12345' class='button'>Accept Request</a>" +
                    "    <div class='footer'>This email was generated automatically by Combat Fitness Gym Management System.</div>" +
                    "  </div>" +
                    "</body>" +
                    "</html>";


            helper.setText(htmlContent, true); // true = HTML

            // Send email
            mailSender.send(message);
            System.out.println("Trial request email sent successfully!");
            return "Trial request email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send trial request email.");
            return "Failed to send trial request email.";
        }
    }

}
