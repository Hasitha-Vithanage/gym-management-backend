package com.bit.backend.services;

import com.bit.backend.dtos.SupplementInventoryDto;

import java.util.List;

public interface SupplementInventoryServiceI {

    SupplementInventoryDto addSupplementInventoryEntity(SupplementInventoryDto supplementInventoryDto);
    List<SupplementInventoryDto> getSupplementInventory();
    SupplementInventoryDto getSupplementById(long id);
    SupplementInventoryDto updateSupplementInventory(long id, SupplementInventoryDto supplementInventoryDto);
    SupplementInventoryDto deleteSupplementInventory(long id);
}
