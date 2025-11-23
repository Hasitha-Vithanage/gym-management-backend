package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MemberLoginServiceI;
import com.bit.backend.services.TrainerLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/member-login/{id}")
    public ResponseEntity<MemberLoginDto> updateMemberLogin(@RequestBody MemberLoginDto memberLoginDto, @PathVariable long id) {
        try {
            MemberLoginDto memberLoginDtoResponse = memberLoginServiceI.updateMemberLogin(memberLoginDto, id);
            return ResponseEntity.created(URI.create("/member-login" + memberLoginDtoResponse.getId())).body(memberLoginDtoResponse);
        } catch (Exception e) {
            throw new AppException("Assign Login failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/member-login/{id}")
    public ResponseEntity<MemberLoginDto> deleteMemberLogin(@PathVariable long id) {
        MemberLoginDto memberLoginDto = memberLoginServiceI.deleteMemberLogin(id);
        return ResponseEntity.ok().body(memberLoginDto);
    }

    @GetMapping("/member-user-id/{id}")
    public ResponseEntity<MemberLoginDto> getMemberLoginData(@PathVariable long id) {
        MemberLoginDto memberLoginDto = memberLoginServiceI.getMemberLoginDataByMemberId(id);
        return ResponseEntity.created(URI.create("/member-user-id" + memberLoginDto.getId())).body(memberLoginDto);
    }
}
