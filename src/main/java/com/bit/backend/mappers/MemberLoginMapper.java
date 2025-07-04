package com.bit.backend.mappers;

import com.bit.backend.dtos.MemberLoginDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.entities.MemberLoginEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MemberLoginMapper {

    MemberLoginDto toMemberLoginDto(MemberLoginEntity memberLoginEntity);
    MemberLoginEntity toMemberLoginEntity(MemberLoginDto memberLoginDto);
}
