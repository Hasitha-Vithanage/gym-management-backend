package com.bit.backend.services;

import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;

public interface MemberLoginServiceI {

    MemberLoginDto addMemberLoginEntity(MemberLoginDto memberLoginDto);
}
