package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplementProductDto;
import com.bit.backend.entities.SupplementProductEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplementProductMapper;
import com.bit.backend.repositories.SupplementProductRepository;
import com.bit.backend.services.SupplementProductServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementProductService implements SupplementProductServiceI {

    private final SupplementProductRepository repository;
    private final SupplementProductMapper mapper;

    public SupplementProductService(SupplementProductRepository repository, SupplementProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SupplementProductDto createProduct(SupplementProductDto dto) {
        try {
            SupplementProductEntity entity = mapper.toEntity(dto);
            entity.setIsDeleted(false);
            if (entity.getIsActive() == null) entity.setIsActive(true);
            if (entity.getStockQty() == null) entity.setStockQty(0);
            return mapper.toDto(repository.save(entity));
        } catch (Exception e) {
            throw new AppException("Failed to create supplement product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SupplementProductDto> getAllProductsForMembers() {
        try {
            return mapper.toDtoList(repository.findAllByIsDeletedFalseAndIsActiveTrue());
        } catch (Exception e) {
            throw new AppException("Failed to load products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SupplementProductDto> getAllProductsForStaff() {
        try {
            return mapper.toDtoList(repository.findAllByIsDeletedFalse());
        } catch (Exception e) {
            throw new AppException("Failed to load products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplementProductDto updateProduct(Long id, SupplementProductDto dto) {
        try {
            repository.findById(id)
                    .orElseThrow(() -> new AppException("Supplement product not found", HttpStatus.BAD_REQUEST));

            SupplementProductEntity entity = mapper.toEntity(dto);
            entity.setId(id);
            entity.setIsDeleted(false);
            return mapper.toDto(repository.save(entity));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update supplement product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplementProductDto deleteProduct(Long id) {
        try {
            SupplementProductEntity entity = repository.findById(id)
                    .orElseThrow(() -> new AppException("Supplement product not found", HttpStatus.BAD_REQUEST));
            entity.setIsDeleted(true);
            return mapper.toDto(repository.save(entity));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to delete supplement product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
