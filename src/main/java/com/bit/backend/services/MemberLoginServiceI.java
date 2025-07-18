package com.bit.backend.services;

import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;

import java.util.List;

public interface MemberLoginServiceI {

    MemberLoginDto addMemberLoginEntity(MemberLoginDto memberLoginDto);

    List<MemberLoginDto> getMemberLoginEntity();
}
