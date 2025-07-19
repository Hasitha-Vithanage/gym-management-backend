package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AssignTrainerMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.AssignTrainerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AssignTrainerService implements AssignTrainerServiceI {

    private final AssignTrainerRepository assignTrainerRepository;
    private final AssignTrainerMapper assignTrainerMapper;
    private final UserRepository userRepository;

    public AssignTrainerService(AssignTrainerRepository assignTrainerRepository, AssignTrainerMapper assignTrainerMapper, UserService userService, UserRepository userRepository) {
        this.assignTrainerRepository = assignTrainerRepository;
        this.assignTrainerMapper = assignTrainerMapper;
        this.userRepository = userRepository;
    }

    @Override
    public AssignTrainerDto addAssignTrainerEntity(AssignTrainerDto assignTrainerDto) {
        try {
            System.out.println("************ In Service *************");

            // Check if this member already has a trainer assigned
            boolean alreadyAssigned = assignTrainerRepository.existsByMember(assignTrainerDto.getMember());
            if (alreadyAssigned) {
                throw new AppException("This member is already assigned to a trainer.", HttpStatus.CONFLICT);
            }

            AssignTrainerEntity assignTrainerEntity = assignTrainerMapper.toAssignTrainerEntity(assignTrainerDto);
            AssignTrainerEntity savedItem = assignTrainerRepository.save(assignTrainerEntity);
            return assignTrainerMapper.toAssignTrainerDto(savedItem);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // getEmployee method
    @Override
    public List<AssignTrainerDto> getAssignTrainer() {
        try {
            // db operations and send data
            List<AssignTrainerEntity> assignTrainerEntityList = assignTrainerRepository.findAll();
            List<AssignTrainerDto> assignTrainerDtoList = assignTrainerMapper.toAssignTrainerDto(assignTrainerEntityList);
            return assignTrainerDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AssignTrainerDto updateAssignTrainer(long id, AssignTrainerDto assignTrainerDto) {
        try {
            Optional<AssignTrainerEntity> optionalAssignTrainerEntity = assignTrainerRepository.findById(id);

            if (!optionalAssignTrainerEntity.isPresent()) {
                throw new AppException("AssignTrainer Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            AssignTrainerEntity newAssignTrainerEntity = assignTrainerMapper.toAssignTrainerEntity(assignTrainerDto);
            newAssignTrainerEntity.setId(id);
            AssignTrainerEntity assignTrainerEntity = assignTrainerRepository.save(newAssignTrainerEntity);
            AssignTrainerDto responseAssignTrainerDto = assignTrainerMapper.toAssignTrainerDto(assignTrainerEntity);
            return responseAssignTrainerDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AssignTrainerDto deleteAssignTrainer(long id) {
        try {
            Optional<AssignTrainerEntity> optionalAssignTrainerEntity = assignTrainerRepository.findById(id);

            if (!optionalAssignTrainerEntity.isPresent()) {
                throw new AppException("AssignTrainer Does Not Exsist", HttpStatus.BAD_REQUEST);
            }

            assignTrainerRepository.deleteById(id);

            AssignTrainerDto assignTrainerDto = assignTrainerMapper.toAssignTrainerDto(optionalAssignTrainerEntity.get());
            return assignTrainerDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AssignTrainerDto getAssignTrainerByMember(String member) {
        try {
            // db operations and send data
            AssignTrainerEntity assignTrainerEntity = assignTrainerRepository.findByMember(member);
            AssignTrainerDto assignTrainerDto = assignTrainerMapper.toAssignTrainerDto(assignTrainerEntity);
            return assignTrainerDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User getTrainerByMember(String id) {
        Long memberId = Long.parseLong(id);
         Optional<User> userOpt = userRepository.findById(memberId);
         if (!userOpt.isPresent()) {
             throw new AppException("User Does Not Exist", HttpStatus.BAD_REQUEST);
         }
         User user = userOpt.get();
         AssignTrainerEntity assignTrainerEntity = assignTrainerRepository.findByMember(user.getFirstName());
         String trainerName = assignTrainerEntity.getTrainer();
         List<User> userList = userRepository.findByFirstName(trainerName);
         User user1 = userList.get(0);
         return user1;
    }
}
