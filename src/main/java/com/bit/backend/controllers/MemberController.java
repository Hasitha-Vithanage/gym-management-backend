package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MemberServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class MemberController {

    private MemberServiceI memberServiceI;

    public MemberController(MemberServiceI memberServiceI) {
        this.memberServiceI = memberServiceI;
    }

    @PostMapping(value = {"/member"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MemberDto> addMember(@RequestPart("memberForm") MemberDto memberDto, @RequestPart("image") MultipartFile file) {
        try {
            memberDto.setImage(file.getBytes());
            memberDto.setImageName(file.getOriginalFilename());
            memberDto.setImageType(file.getContentType());
            MemberDto memberDtoResponse = memberServiceI.addMemberEntity(memberDto);
            return ResponseEntity.created(URI.create("/member"+memberDtoResponse.getFirstName())).body(memberDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> memberDtoList = memberServiceI.getMember();
        return ResponseEntity.ok().body(memberDtoList);
    }

//    @PutMapping("/member/{id}")
//    public ResponseEntity<MemberDto> updateMember(@PathVariable long id, @RequestBody MemberDto memberDto) {
//        MemberDto memberDtoResponse = memberServiceI.updateMember(id, memberDto);
//        return ResponseEntity.ok().body(memberDtoResponse);
//    }

    @PutMapping("/member/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable long id, @RequestPart("memberForm") MemberDto memberDto, @RequestPart("image") MultipartFile file) {
        try {
            memberDto.setImage(file.getBytes());
            memberDto.setImageName(file.getOriginalFilename());
            memberDto.setImageType(file.getContentType());
            MemberDto memberDtoResponse = memberServiceI.updateMember(id, memberDto);
            return ResponseEntity.ok(memberDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<MemberDto> deleteMember(@PathVariable long id) {
        MemberDto memberDto = memberServiceI.deleteMember(id);
        return ResponseEntity.ok().body(memberDto);
    }
}
