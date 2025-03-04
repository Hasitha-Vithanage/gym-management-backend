package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.mappers.MemberMapper;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.services.MemberServiceI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService implements MemberServiceI {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDto addMemberEntity(MemberDto memberDto) {
        System.out.println("In the addMemberEntity method");

        MemberEntity memberEntity = memberMapper.toMemberEntity(memberDto);
        MemberEntity savedItem = memberRepository.save(memberEntity);
        MemberDto savedDto = memberMapper.toMemberDto(savedItem);
        return savedDto;
    }

    @Override
    public List<MemberDto> getMember() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<MemberDto> memberDtoList = memberMapper.toMemberDto(memberEntities);
        return memberDtoList;
    }
}
