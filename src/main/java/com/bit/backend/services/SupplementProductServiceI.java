package com.bit.backend.services;

import com.bit.backend.dtos.SupplementProductDto;

import java.util.List;

public interface SupplementProductServiceI {

    SupplementProductDto createProduct(SupplementProductDto dto);
    List<SupplementProductDto> getAllProductsForMembers();
    List<SupplementProductDto> getAllProductsForStaff();
    SupplementProductDto updateProduct(Long id, SupplementProductDto dto);
    SupplementProductDto deleteProduct(Long id);
}
