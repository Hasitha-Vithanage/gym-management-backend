package com.bit.backend.services.impl;

import com.bit.backend.dtos.SystemPrivilegeDto;
import com.bit.backend.dtos.SystemPrivilegeListDto;
import com.bit.backend.entities.Privilege;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.PrivilegeRepository;
import com.bit.backend.services.PrivilegeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeService implements PrivilegeServiceI {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto) {
        try {
            List<SystemPrivilegeDto> availableSystemPrivilegeDtoList = systemPrivilegeListDto.getSourcePrivileges();
            List<SystemPrivilegeDto> assignedSystemPrivilegeDtoList = systemPrivilegeListDto.getTargetPrivileges();
            List<Privilege> availablePrivilegeList = new ArrayList<>();
            List<Privilege> assignedPrivilegeList = new ArrayList<>();

            if (availableSystemPrivilegeDtoList != null) {
                for (SystemPrivilegeDto systemPrivilegeDto : availableSystemPrivilegeDtoList) {
                    List<Privilege> privileges = privilegeRepository.findAllByAuthId(systemPrivilegeDto.getId());
                    for (Privilege privilege : privileges) {
                        privilege.setAssigned(0);
                        availablePrivilegeList.add(privilege);
                    }
                }
            }

            if (assignedSystemPrivilegeDtoList != null) {
                for (SystemPrivilegeDto systemPrivilegeDto : assignedSystemPrivilegeDtoList) {
                    List<Privilege> privileges = privilegeRepository.findAllByAuthId(systemPrivilegeDto.getId());
                    for (Privilege privilege : privileges) {
                        privilege.setAssigned(1);
                        assignedPrivilegeList.add(privilege);
                    }
                }
            }

            privilegeRepository.saveAll(availablePrivilegeList);
            privilegeRepository.saveAll(assignedPrivilegeList);

            return new ArrayList<>();
        } catch (Exception e) {
            throw new AppException("Failed to save system privileges: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
