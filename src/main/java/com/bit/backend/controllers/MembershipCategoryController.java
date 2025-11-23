package com.bit.backend.controllers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MembershipCategoryDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MembershipCategoryServiceI;
import com.bit.backend.services.impl.MembershipCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class MembershipCategoryController {

    private final MembershipCategoryServiceI membershipCategoryServiceI;

    public MembershipCategoryController(MembershipCategoryServiceI membershipCategoryServiceI) {
        this.membershipCategoryServiceI = membershipCategoryServiceI;
    }

    @PostMapping("/membership-category")
    public ResponseEntity<MembershipCategoryDto> addMembershipCategory(@RequestBody MembershipCategoryDto membershipCategoryDto) {
        try {
            MembershipCategoryDto membershipCategoryDtoResponse = membershipCategoryServiceI.addMembershipCategoryEntity(membershipCategoryDto);
            return ResponseEntity.created(URI.create("/membership-category" + membershipCategoryDtoResponse.getCategoryName())).body(membershipCategoryDtoResponse);
        } catch (Exception e) {
            throw new AppException("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("membership-category/{id}")
    public ResponseEntity<MembershipCategoryDto> updateMembershipCategory(@PathVariable long id, @RequestBody MembershipCategoryDto membershipCategoryDto) {
        try {
            MembershipCategoryDto membershipCategoryDtoResponse = membershipCategoryServiceI.updateMembershipCategory(id, membershipCategoryDto);
            return ResponseEntity.ok(membershipCategoryDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the Membership Category. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/membership-category")
    public ResponseEntity<List<MembershipCategoryDto>> getMembershipCategory() {
        try {
            List<MembershipCategoryDto> membershipCategoryDtoList = membershipCategoryServiceI.getMembershipCategory();
            return ResponseEntity.ok(membershipCategoryDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load Membership Category records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/membership-category-fee/{categoryName}")
    public ResponseEntity<List<MembershipCategoryDto>> getMembershipCategoryByCategoryName(@PathVariable String categoryName) {
        List<MembershipCategoryDto> membershipCategoryDtoList = membershipCategoryServiceI.getMembershipCategoryByMember(categoryName);
        return ResponseEntity.ok(membershipCategoryDtoList);
    }
}
