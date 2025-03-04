package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;

public interface MemberServiceI {

    MemberDto addMemberEntity(MemberDto memberDto);
}
