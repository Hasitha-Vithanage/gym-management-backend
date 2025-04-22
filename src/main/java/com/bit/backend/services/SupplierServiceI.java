package com.bit.backend.services;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.SupplierDto;

import java.util.List;

public interface SupplierServiceI {
    SupplierDto addSupplierEntity(SupplierDto supplierDto);
    List<SupplierDto> getSupplier();
    SupplierDto updateSupplier(long id, SupplierDto supplierDto);
    SupplierDto deleteSupplier(long id);
}
