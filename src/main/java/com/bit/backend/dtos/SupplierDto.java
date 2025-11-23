package com.bit.backend.dtos;

import java.util.List;

public class SupplierDto {

    private Long id;
    private String supplierName;
    private String contactPerson;
    private String contactNo;
    private String emailAddress;
    private String officeAddress;
    private String postalAddress;
    private List<String> equipmentType;
    private boolean status;
    private String remarks;

    public SupplierDto() {
    }

    public SupplierDto(Long id, String supplierName, String contactPerson, String contactNo, String emailAddress, String officeAddress, String postalAddress, List<String> equipmentType, boolean status, String remarks) {
        this.id = id;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.officeAddress = officeAddress;
        this.postalAddress = postalAddress;
        this.equipmentType = equipmentType;
        this.status = status;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public List<String> getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(List<String> equipmentType) {
        this.equipmentType = equipmentType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

