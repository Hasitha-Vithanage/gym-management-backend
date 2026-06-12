package com.bit.backend.services;

import com.bit.backend.dtos.MemberNutritionProfileDto;

import java.util.List;

public interface MemberNutritionProfileServiceI {

    MemberNutritionProfileDto addProfile(MemberNutritionProfileDto dto);
    List<MemberNutritionProfileDto> getAllProfiles();
    boolean hasProfile(String userId);
    MemberNutritionProfileDto getProfileByUserId(String userId);
}
