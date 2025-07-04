package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.entities.MemberLoginEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberLoginMapper;
import com.bit.backend.mappers.TrainerLoginMapper;
import com.bit.backend.repositories.MemberLoginRepository;
import com.bit.backend.repositories.TrainerLoginRepository;
import com.bit.backend.services.MemberLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService implements MemberLoginServiceI {

    private final MemberLoginRepository memberLoginRepository;
    private final MemberLoginMapper memberLoginMapper;

    public MemberLoginService(MemberLoginRepository memberLoginRepository, MemberLoginMapper memberLoginMapper) {
        this.memberLoginRepository = memberLoginRepository;
        this.memberLoginMapper = memberLoginMapper;
    }

    @Override
    public MemberLoginDto addMemberLoginEntity(MemberLoginDto memberLoginDto) {
        try {
            System.out.println("************ In Service *************");

            MemberLoginEntity memberLoginEntity = memberLoginMapper.toMemberLoginEntity(memberLoginDto);
            MemberLoginEntity savedItem = memberLoginRepository.save(memberLoginEntity);
            MemberLoginDto savedMemberLoginDto = memberLoginMapper.toMemberLoginDto(savedItem);
            return savedMemberLoginDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
