package com.bit.backend.services;

import com.bit.backend.dtos.TemplateMealItemDto;

import java.util.List;

public interface TemplateMealItemServiceI {

    List<TemplateMealItemDto> getMealItemsByTemplateId(Long templateId);
    List<TemplateMealItemDto> saveAllMealItems(Long templateId, List<TemplateMealItemDto> items);
    void deleteMealItem(Long id);
}
