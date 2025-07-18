package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.entities.MemberEntity;
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

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<MemberLoginDto> getMemberLoginEntity() {
        List<MemberLoginEntity> memberLoginEntities = memberLoginRepository.findAll();
        List<MemberLoginDto> memberLoginDtoList = memberLoginMapper.toMemberLoginDtoList(memberLoginEntities);
        return memberLoginDtoList;
    }

    @Override
    public MemberLoginDto deleteMemberLogin(long id) {
        Optional<MemberLoginEntity> optionalMemberLoginEntity = memberLoginRepository.findById(id);
        if (!optionalMemberLoginEntity.isPresent()) {
            throw new AppException("Member Login Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        memberLoginRepository.deleteById(id);
        MemberLoginDto deletedDto = memberLoginMapper.toMemberLoginDto(optionalMemberLoginEntity.get());
        return deletedDto;
    }

}
