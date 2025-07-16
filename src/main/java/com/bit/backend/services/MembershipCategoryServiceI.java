package com.bit.backend.services;

import com.bit.backend.dtos.MembershipCategoryDto;

import java.util.List;

public interface MembershipCategoryServiceI {

    MembershipCategoryDto addMembershipCategoryEntity(MembershipCategoryDto membershipCategoryDto);
    MembershipCategoryDto updateMembershipCategory(long id, MembershipCategoryDto membershipCategoryDto);
    List<MembershipCategoryDto> getMembershipCategory();
}
