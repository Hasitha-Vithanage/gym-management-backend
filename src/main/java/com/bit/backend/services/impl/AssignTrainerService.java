package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AssignTrainerMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.repositories.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final EmployeeRepository employeeRepository;

    public AssignTrainerService(AssignTrainerRepository assignTrainerRepository, AssignTrainerMapper assignTrainerMapper, UserService userService, UserRepository userRepository, MemberRepository memberRepository, EmployeeRepository employeeRepository) {
        this.assignTrainerRepository = assignTrainerRepository;
        this.assignTrainerMapper = assignTrainerMapper;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AssignTrainerDto addAssignTrainerEntity(AssignTrainerDto assignTrainerDto) {
        try {
            boolean alreadyAssigned = assignTrainerRepository.existsByMemberId(assignTrainerDto.getMemberId());
            if (alreadyAssigned) {
                throw new AppException("This member is already assigned to a trainer.", HttpStatus.CONFLICT);
            }

            MemberEntity member = memberRepository.findById(assignTrainerDto.getMemberId())
                    .orElseThrow(() -> new AppException("Member not found", HttpStatus.BAD_REQUEST));
            EmployeeEntity trainer = employeeRepository.findById(assignTrainerDto.getTrainerId())
                    .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.BAD_REQUEST));

            AssignTrainerEntity entity = assignTrainerMapper.toAssignTrainerEntity(assignTrainerDto);
            entity.setMember(member.getFirstName() + " " + member.getLastName());
            entity.setTrainer(trainer.getFirstName() + " " + trainer.getLastName());

            AssignTrainerEntity savedItem = assignTrainerRepository.save(entity);
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
    public AssignTrainerDto getAssignTrainerByMember(String loginName) {
        try {
            User user = userRepository.findByLogin(loginName)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
            AssignTrainerEntity entity = assignTrainerRepository.findByMemberId(user.getCustomerLoginId());
            if (entity == null) {
                throw new AppException("No trainer assigned for this member", HttpStatus.NOT_FOUND);
            }
            return assignTrainerMapper.toAssignTrainerDto(entity);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User getTrainerByMember(String id) {
        Long userId = Long.parseLong(id);
        User memberUser = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User Does Not Exist", HttpStatus.BAD_REQUEST));
        AssignTrainerEntity entity = assignTrainerRepository.findByMemberId(memberUser.getCustomerLoginId());
        if (entity == null) {
            throw new AppException("No trainer assigned for this member", HttpStatus.NOT_FOUND);
        }
        return userRepository.findByEmployeeLoginId(entity.getTrainerId())
                .orElseThrow(() -> new AppException("Trainer user not found", HttpStatus.NOT_FOUND));
    }
}
