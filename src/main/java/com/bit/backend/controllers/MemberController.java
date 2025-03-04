package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.services.MemberServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MemberController {

    private MemberServiceI memberServiceI;

    public MemberController(MemberServiceI memberServiceI) {
        this.memberServiceI = memberServiceI;
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto memberDto) {
        MemberDto memberDtoResponse = memberServiceI.addMemberEntity(memberDto);
        return ResponseEntity.created(URI.create("/member" + memberDtoResponse.getFirstName())).body(memberDtoResponse);
    }
}
