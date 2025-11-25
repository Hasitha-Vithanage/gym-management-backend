package com.bit.backend.controllers;

import com.bit.backend.services.MemberServiceI;
import com.bit.backend.services.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TrialRequestController {

    @Autowired
    private EmailService emailService;

    public TrialRequestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email/request-trial")
    public ResponseEntity<String> requestTrial(@RequestBody Map<String, String> data) {

        try {
            emailService.sendTrialRequestEmail(
                    data.get("name"),
                    data.get("email"),
                    data.get("company"),
                    data.get("message")
            );

            return ResponseEntity.ok("Email Sent Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
