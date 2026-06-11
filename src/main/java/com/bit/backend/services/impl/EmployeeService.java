package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.repositories.TrainerLoginRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final TrainerLoginRepository trainerLoginRepository;
    private final AddClassRepository addClassRepository;
    private final AssignTrainerRepository assignTrainerRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                           TrainerLoginRepository trainerLoginRepository, AddClassRepository addClassRepository,
                           AssignTrainerRepository assignTrainerRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.trainerLoginRepository = trainerLoginRepository;
        this.addClassRepository = addClassRepository;
        this.assignTrainerRepository = assignTrainerRepository;
    }

    // addEmployeeEntity method
    @Override
    public EmployeeDto addEmployeeEntity(EmployeeDto employeeDto) {
        try {
            System.out.println("************ In Service *************");

            EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            EmployeeEntity savedItem = employeeRepository.save(employeeEntity);
            EmployeeDto savedEmployeeDto = employeeMapper.toEmployeeDto(savedItem);
            return savedEmployeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // getEmployee method
    @Override
    public List<EmployeeDto> getEmployee() {
        try {
            // db operations and send data
            List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
            List<EmployeeDto> employeeDtoList = employeeMapper.toEmployeeDto(employeeEntityList);
            return employeeDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);

            if (!optionalEmployeeEntity.isPresent()) {
                throw new AppException("Employee Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            EmployeeEntity newEmployeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            newEmployeeEntity.setId(id);
            EmployeeEntity employeeEntity = employeeRepository.save(newEmployeeEntity);
            EmployeeDto responseEmployeeDto = employeeMapper.toEmployeeDto(employeeEntity);
            return responseEmployeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Override
    // public EmployeeDto deleteEmployee(long id) {
    // try {
    // Optional<EmployeeEntity> optionalEmployeeEntity =
    // employeeRepository.findById(id);

    // if (!optionalEmployeeEntity.isPresent()) {
    // throw new AppException("Employee Does Not Exsist", HttpStatus.BAD_REQUEST);
    // }

    // employeeRepository.deleteById(id);

    // EmployeeDto employeeDto =
    // employeeMapper.toEmployeeDto(optionalEmployeeEntity.get());
    // return employeeDto;
    // } catch (Exception e) {
    // throw new AppException("Request failed with error: " + e,
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @Override
    public EmployeeDto deleteEmployee(long id) {
        try {
            EmployeeEntity existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new AppException("Employee does not exist", HttpStatus.BAD_REQUEST));

            TrainerLoginEntity login = trainerLoginRepository.findByEmployee(id);
            if (login != null && login.isActive()) {
                throw new AppException(
                    "This employee has an active login account. Please deactivate the login before deleting.",
                    HttpStatus.CONFLICT);
            }

            if (assignTrainerRepository.existsByTrainerId(id)) {
                throw new AppException(
                    "This employee is assigned to one or more members. Please remove those trainer assignments before deleting.",
                    HttpStatus.CONFLICT);
            }

            if (addClassRepository.existsScheduledClassByTrainer(id)) {
                throw new AppException(
                    "This employee is assigned to one or more upcoming classes. Please reassign those classes before deleting.",
                    HttpStatus.CONFLICT);
            }

            if (login != null) {
                trainerLoginRepository.delete(login);
            }

            existingEmployee.setIsDeleted(true);
            EmployeeEntity updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toEmployeeDto(updatedEmployee);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<EmployeeDto> getTrainers() {
        // Call repository interface to get data
        List<EmployeeEntity> employeeEntityList = employeeRepository.getTrainers();
        List<EmployeeDto> employeeDtoList = employeeMapper.toEmployeeDto(employeeEntityList);
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getTrainerById(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + id));
        return employeeMapper.toEmployeeDto(employeeEntity);
    }

    @Override
    public Long getEmployeeCount() {
        // call repository to get employee count
        long employeeCount = employeeRepository.count();
        return employeeCount;
    }

    @Override
    public EmployeeDto getTrainerByName(String trainerName) {
        try {
            // db operations and send data
            EmployeeEntity employeeEntity = employeeRepository.findByFirstName(trainerName);
            EmployeeDto employeeDto = employeeMapper.toEmployeeDto(employeeEntity);
            return employeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
