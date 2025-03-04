package com.bit.backend.mappers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.MemberEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MemberMapper {
    MemberDto toMemberDto(MemberEntity memberEntity);
    MemberEntity toMemberEntity(MemberDto memberDto);
    List<MemberDto> toMemberDto(List<MemberEntity> memberEntities);
}
