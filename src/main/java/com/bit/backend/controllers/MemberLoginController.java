package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MemberLoginServiceI;
import com.bit.backend.services.TrainerLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MemberLoginController {

    private final MemberLoginServiceI memberLoginServiceI;

    public MemberLoginController(MemberLoginServiceI memberLoginServiceI) {
        this.memberLoginServiceI = memberLoginServiceI;
    }

    @PostMapping("/member-login")
    public ResponseEntity<MemberLoginDto> addMemberLogin(@RequestBody MemberLoginDto memberLoginDto) {
        try {
            MemberLoginDto memberLoginDtoResponse = memberLoginServiceI.addMemberLoginEntity(memberLoginDto);
            return ResponseEntity.created(URI.create("/member-login" + memberLoginDtoResponse.getId())).body(memberLoginDtoResponse);
        } catch (Exception e) {
            throw new AppException("Assign Login failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member-login")
    public ResponseEntity<List<MemberLoginDto>> getMemberLogin() {
        // calling service through interface

        List<MemberLoginDto> memberLoginDtoList = memberLoginServiceI.getMemberLoginEntity();
        return ResponseEntity.ok(memberLoginDtoList);
    }
}
