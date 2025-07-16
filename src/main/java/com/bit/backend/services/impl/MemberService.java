package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.MembershipCategoryDto;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.MembershipCategoryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberMapper;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.services.MemberServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public MemberDto updateMember(long id, MemberDto memberDto) {
        System.out.println("In the updateMemberEntity method");

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        if (!optionalMemberEntity.isPresent()) {
            throw new AppException("Member Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        MemberEntity newMemberEntity = memberMapper.toMemberEntity(memberDto);
        newMemberEntity.setId(id);
        MemberEntity savedItem = memberRepository.save(newMemberEntity);
        MemberDto savedDto = memberMapper.toMemberDto(savedItem);
        return savedDto;
    }

    @Override
    public MemberDto deleteMember(long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (!optionalMemberEntity.isPresent()) {
            throw new AppException("Member Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        memberRepository.deleteById(id);
        MemberDto deletedDto = memberMapper.toMemberDto(optionalMemberEntity.get());
        return deletedDto;
    }

    @Override
    public MemberDto getMemberById(long id) {
        MemberEntity memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + id));
        return memberMapper.toMemberDto(memberEntity);
    }

    @Override
    public List<MemberDto> getMemberByMember(String firstName) {
        List<MemberEntity> memberEntityList = memberRepository.findByFirstName(firstName);
        return memberMapper.toMemberDto(memberEntityList);
    }

}
