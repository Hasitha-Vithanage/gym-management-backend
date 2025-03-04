package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;

import java.util.List;

public interface MemberServiceI {

    MemberDto addMemberEntity(MemberDto memberDto);
    List<MemberDto> getMember();
}
