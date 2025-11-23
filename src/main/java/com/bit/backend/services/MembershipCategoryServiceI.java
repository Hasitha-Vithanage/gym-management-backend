package com.bit.backend.services;

import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MembershipCategoryDto;

import java.util.List;

public interface MembershipCategoryServiceI {

    MembershipCategoryDto addMembershipCategoryEntity(MembershipCategoryDto membershipCategoryDto);
    MembershipCategoryDto updateMembershipCategory(long id, MembershipCategoryDto membershipCategoryDto);
    List<MembershipCategoryDto> getMembershipCategory();
    List<MembershipCategoryDto> getMembershipCategoryByMember(String categoryName);
}
