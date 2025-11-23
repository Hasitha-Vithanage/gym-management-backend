package com.bit.backend.services;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MembershipCategoryDto;

import java.util.List;

public interface MemberServiceI {

    MemberDto addMemberEntity(MemberDto memberDto);
    List<MemberDto> getMember();
    MemberDto updateMember(long id, MemberDto memberDto);
    MemberDto deleteMember(long id);
    MemberDto getMemberById(long id);
    List<MemberDto> getMemberByMember(String member);
    long getMemberCount();
    Integer newMembersInThisMonth();
}
