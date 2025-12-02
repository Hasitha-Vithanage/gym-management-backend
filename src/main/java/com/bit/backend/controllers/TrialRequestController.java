package com.bit.backend.controllers;

import com.bit.backend.services.MemberServiceI;
import com.bit.backend.services.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("dsdasdasdasdasd");
        emailService.sendTrialRequestEmail(
                data.get("firstName"),
                data.get("lastName"),
                data.get("userName"),
                data.get("email"),
                data.get("password")
        );

        return ResponseEntity.ok("Email Sent Successfully");
    }
}
