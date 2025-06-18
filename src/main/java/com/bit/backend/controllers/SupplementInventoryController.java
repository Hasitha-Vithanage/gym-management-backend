package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MemberServiceI;
import com.bit.backend.services.SupplementInventoryServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class SupplementInventoryController {

    private SupplementInventoryServiceI supplementInventoryServiceI;

    public SupplementInventoryController(SupplementInventoryServiceI supplementInventoryServiceI) {
        this.supplementInventoryServiceI = supplementInventoryServiceI;
    }

    @PostMapping(value = {"/supplement"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SupplementInventoryDto> addSupplementInventory(@RequestPart("supplementForm") SupplementInventoryDto supplementInventoryDto, @RequestPart("image") MultipartFile file) {
        try {
            supplementInventoryDto.setImage(file.getBytes());
            supplementInventoryDto.setImageName(file.getOriginalFilename());
            supplementInventoryDto.setImageType(file.getContentType());
            SupplementInventoryDto supplementInventoryDtoResponse = supplementInventoryServiceI.addSupplementInventoryEntity(supplementInventoryDto);
            return ResponseEntity.created(URI.create("/supplement"+supplementInventoryDtoResponse.getProductName())).body(supplementInventoryDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplement")
    public ResponseEntity<List<SupplementInventoryDto>> getAllSupplementInventory() {
        List<SupplementInventoryDto> supplementInventoryDtoList = supplementInventoryServiceI.getSupplementInventory();
        return ResponseEntity.ok().body(supplementInventoryDtoList);
    }

//    @PutMapping("/member/{id}")
//    public ResponseEntity<MemberDto> updateMember(@PathVariable long id, @RequestBody MemberDto memberDto) {
//        MemberDto memberDtoResponse = memberServiceI.updateMember(id, memberDto);
//        return ResponseEntity.ok().body(memberDtoResponse);
//    }

    @PutMapping("/supplement/{id}")
    public ResponseEntity<SupplementInventoryDto> updateSupplementInventory(@PathVariable long id, @RequestPart("supplementForm") SupplementInventoryDto supplementInventoryDto, @RequestPart("image") MultipartFile file) {
        try {
            supplementInventoryDto.setImage(file.getBytes());
            supplementInventoryDto.setImageName(file.getOriginalFilename());
            supplementInventoryDto.setImageType(file.getContentType());
            SupplementInventoryDto supplementInventoryDtoResponse = supplementInventoryServiceI.updateSupplementInventory(id, supplementInventoryDto);
            return ResponseEntity.ok(supplementInventoryDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/supplement/{id}")
    public ResponseEntity<SupplementInventoryDto> deleteSupplementInventory(@PathVariable long id) {
        SupplementInventoryDto supplementInventoryDto = supplementInventoryServiceI.deleteSupplementInventory(id);
        return ResponseEntity.ok().body(supplementInventoryDto);
    }
}
