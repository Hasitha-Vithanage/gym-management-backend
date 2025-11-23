package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table ( name = "supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "supplierName")
    private String supplierName;

    @Column(name = "contactPerson")
    private String contactPerson;

    @Column(name = "contactNo")
    private String contactNo;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "officeAddress")
    private String officeAddress;

    @Column(name = "postalAddress")
    private String postalAddress;

    @ElementCollection
    @CollectionTable(name = "supplier_equipment_types", joinColumns = @JoinColumn(name = "supplier_id"))
    @Column(name = "equipment_type")
    private List<String> equipmentType;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "remarks")
    private String remarks;

    public SupplierEntity() {
    }

    public SupplierEntity(long id, String supplierName, String contactPerson, String contactNo, String emailAddress, String officeAddress, String postalAddress, List<String> equipmentType, Boolean status, String remarks) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
