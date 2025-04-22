package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.services.MemberServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/member")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> memberDtoList = memberServiceI.getMember();
        return ResponseEntity.ok().body(memberDtoList);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable long id, @RequestBody MemberDto memberDto) {
        MemberDto memberDtoResponse = memberServiceI.updateMember(id, memberDto);
        return ResponseEntity.ok().body(memberDtoResponse);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<MemberDto> deleteMember(@PathVariable long id) {
        MemberDto memberDto = memberServiceI.deleteMember(id);
        return ResponseEntity.ok().body(memberDto);
    }
}
