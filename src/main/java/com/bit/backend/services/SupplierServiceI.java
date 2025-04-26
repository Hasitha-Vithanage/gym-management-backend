package com.bit.backend.services;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.SupplierDto;

import java.util.List;

public interface SupplierServiceI {
    SupplierDto addSupplierEntity(SupplierDto supplierDto);
    SupplierDto updateSupplier(long id, SupplierDto supplierDto);
    List<SupplierDto> getSupplier();
    List<SupplierDto>getSuppliers();
    SupplierDto deleteSupplier(long id);
}
