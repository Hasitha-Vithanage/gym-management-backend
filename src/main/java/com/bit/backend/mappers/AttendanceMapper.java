package com.bit.backend.mappers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.AttendanceEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AttendanceMapper {


    AttendanceDto toAttendanceDto(AttendanceEntity attendanceEntity);
    AttendanceEntity toAttendanceEntity(AttendanceDto attendanceDto);
    List<AttendanceDto> toAttendanceDto(List<AttendanceEntity> attendanceEntities);

}
