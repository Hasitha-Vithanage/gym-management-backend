package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.MembershipCategoryDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.MembershipCategoryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MembershipCategoryMapper;
import com.bit.backend.repositories.MembershipCategoryRepository;
import com.bit.backend.services.MembershipCategoryServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipCategoryService implements MembershipCategoryServiceI {

    private final MembershipCategoryRepository membershipCategoryRepository;
    private final MembershipCategoryMapper membershipCategoryMapper;

    public MembershipCategoryService(MembershipCategoryRepository membershipCategoryRepository, MembershipCategoryMapper membershipCategoryMapper) {
        this.membershipCategoryRepository = membershipCategoryRepository;
        this.membershipCategoryMapper = membershipCategoryMapper;
    }

    @Override
    public MembershipCategoryDto addMembershipCategoryEntity(MembershipCategoryDto membershipCategoryDto) {
        try {
            System.out.println("************ In Service *************");

            // Check if this member already has a trainer assigned
            boolean alreadyAdded = membershipCategoryRepository.existsByCategoryName(membershipCategoryDto.getCategoryName());
            if (alreadyAdded) {
                throw new AppException("This category name is already added.", HttpStatus.CONFLICT);
            }

            MembershipCategoryEntity membershipCategoryEntity = membershipCategoryMapper.toMembershipCategoryEntity(membershipCategoryDto);
            MembershipCategoryEntity savedItem = membershipCategoryRepository.save(membershipCategoryEntity);
            return membershipCategoryMapper.toMembershipCategoryDto(savedItem);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MembershipCategoryDto updateMembershipCategory(long id, MembershipCategoryDto membershipCategoryDto) {
        try {
            Optional<MembershipCategoryEntity> optionalMembershipCategoryEntity = membershipCategoryRepository.findById(id);

            if (!optionalMembershipCategoryEntity.isPresent()) {
                throw new AppException("Membership Category Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            MembershipCategoryEntity newMembershipCategoryEntity = membershipCategoryMapper.toMembershipCategoryEntity(membershipCategoryDto);
            newMembershipCategoryEntity.setId(id);
            MembershipCategoryEntity membershipCategoryEntity = membershipCategoryRepository.save(newMembershipCategoryEntity);
            MembershipCategoryDto responseMembershipCategoryDto = membershipCategoryMapper.toMembershipCategoryDto(membershipCategoryEntity);
            return responseMembershipCategoryDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MembershipCategoryDto> getMembershipCategory() {
        try {
            // db operations and send data
            List<MembershipCategoryEntity> membershipCategoryEntityList = membershipCategoryRepository.findAll();
            List<MembershipCategoryDto> membershipCategoryDtoList = membershipCategoryMapper.toMembershipCategoryDto(membershipCategoryEntityList);
            return membershipCategoryDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
