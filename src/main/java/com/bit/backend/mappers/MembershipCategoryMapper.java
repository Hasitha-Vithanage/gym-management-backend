package com.bit.backend.mappers;

import com.bit.backend.dtos.MembershipCategoryDto;
import com.bit.backend.entities.MembershipCategoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MembershipCategoryMapper {

    MembershipCategoryDto toMembershipCategoryDto(MembershipCategoryEntity membershipCategoryEntity);
    MembershipCategoryEntity toMembershipCategoryEntity(MembershipCategoryDto membershipCategoryDto);
    List<MembershipCategoryDto> toMembershipCategoryDto(List<MembershipCategoryEntity> membershipCategoryEntities);
}
