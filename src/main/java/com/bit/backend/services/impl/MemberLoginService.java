package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.MemberLoginEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberLoginMapper;
import com.bit.backend.mappers.TrainerLoginMapper;
import com.bit.backend.repositories.MemberLoginRepository;
import com.bit.backend.repositories.TrainerLoginRepository;
import com.bit.backend.services.MemberLoginServiceI;
import com.bit.backend.services.UserServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberLoginService implements MemberLoginServiceI {

    private final MemberLoginRepository memberLoginRepository;
    private final MemberLoginMapper memberLoginMapper;
    private final UserServiceI userServiceI;

    public MemberLoginService(MemberLoginRepository memberLoginRepository, MemberLoginMapper memberLoginMapper, UserServiceI userServiceI) {
        this.memberLoginRepository = memberLoginRepository;
        this.memberLoginMapper = memberLoginMapper;
        this.userServiceI = userServiceI;
    }

    @Override
    public MemberLoginDto addMemberLoginEntity(MemberLoginDto memberLoginDto) {
        try {
            System.out.println("************ In Service *************");
            memberLoginDto.setRole("MEMBER");

            String password = new String(memberLoginDto.getPassword());
            memberLoginDto.setPassword(null);

            SignUpDto signUpDto = new SignUpDto(memberLoginDto.getFirstName(), memberLoginDto.getLastName(), memberLoginDto.getUserName(), null,
                    password.toCharArray(), memberLoginDto.getRole(), null, null, null, memberLoginDto.getMember());

            /* Check if login already exists for the member */
            MemberLoginEntity memberLoginEntityCheck = memberLoginRepository.findByMember(memberLoginDto.getMember());

            if (memberLoginEntityCheck != null) {
                throw new AppException("Login already exists for the member", HttpStatus.NOT_FOUND);
            }

            /* Check if user already exists for the trainer*/
            UserDto oUser = userServiceI.findByLogin(memberLoginDto.getUserName());

            if (oUser != null) {
                throw new AppException("User name already exists for the member", HttpStatus.NOT_FOUND);
            }

            /*Create User Entity*/
            UserDto userDto = userServiceI.register(signUpDto);
            memberLoginDto.setUserId(userDto.getId());
            /*Create Login Entity*/
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

    @Override
    public MemberLoginDto updateMemberLogin(MemberLoginDto memberLoginDto, long id) {
       try {
           /*Check if there is a login*/
           Optional<MemberLoginEntity> optionalMemberLoginEntity = memberLoginRepository.findById(id);

           if (!optionalMemberLoginEntity.isPresent()) {
               throw new AppException("Old Member Login not found!", HttpStatus.NOT_FOUND);
           }

           /*Check if other users has the same username*/
           List<User> userList = userServiceI.checkIfUserNameExistForOtherUsers(memberLoginDto.getUserName(), memberLoginDto.getUserId());

           if (userList != null && userList.size() > 0) {
               throw new AppException("User name found for other users!", HttpStatus.NOT_FOUND);
           }

           MemberLoginEntity oldMemberLoginEntity = optionalMemberLoginEntity.get();
           oldMemberLoginEntity.setUserName(memberLoginDto.getUserName());

           MemberLoginEntity savedMemberLoginEntity = memberLoginRepository.save(oldMemberLoginEntity);
           MemberLoginDto savedMemberLoginDto = memberLoginMapper.toMemberLoginDto(savedMemberLoginEntity);

           /*Update Password*/
           UserDto userDto = userServiceI.updatePassword(memberLoginDto.getUserName(), memberLoginDto.getPassword(), memberLoginDto.getUserId());

           return savedMemberLoginDto;
       } catch (Exception e) {
           throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public MemberLoginDto getMemberLoginDataByMemberId(long id) {
        MemberLoginEntity memberLoginEntity = memberLoginRepository.findByMember(id);
        return memberLoginMapper.toMemberLoginDto(memberLoginEntity);
    }

}
