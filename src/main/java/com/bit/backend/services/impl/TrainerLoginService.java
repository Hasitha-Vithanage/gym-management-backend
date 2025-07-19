package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AssignTrainerMapper;
import com.bit.backend.mappers.TrainerLoginMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.TrainerLoginRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.TrainerLoginServiceI;
import com.bit.backend.services.UserServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerLoginService implements TrainerLoginServiceI {

    private final TrainerLoginRepository trainerLoginRepository;
    private final TrainerLoginMapper trainerLoginMapper;
    private final UserRepository userRepository;
    private final UserServiceI userServiceI;

    public TrainerLoginService(TrainerLoginRepository trainerLoginRepository, TrainerLoginMapper trainerLoginMapper,
                               UserRepository userRepository, UserServiceI userServiceI) {
        this.trainerLoginRepository = trainerLoginRepository;
        this.trainerLoginMapper = trainerLoginMapper;
        this.userRepository = userRepository;
        this.userServiceI = userServiceI;
    }

    // addEmployeeEntity method
    @Override
    public TrainerLoginDto addTrainerLoginEntity(TrainerLoginDto trainerLoginDto) {
        try {
            System.out.println("************ In Service *************");
            trainerLoginDto.setRole("TRAINER");

            String password = new String(trainerLoginDto.getPassword());
            trainerLoginDto.setPassword(null);

            SignUpDto signUpDto = new SignUpDto(trainerLoginDto.getFirstName(), trainerLoginDto.getLastName(), trainerLoginDto.getUserName(),
                    password.toCharArray(), trainerLoginDto.getRole(), trainerLoginDto.getEmployee(), null);

            /* Check if login already exists for the trainer */
            TrainerLoginEntity trainerLoginEntityCheck = trainerLoginRepository.findByEmployee(trainerLoginDto.getEmployee());

            if (trainerLoginEntityCheck != null) {
                throw new AppException("Login already exists for the trainer", HttpStatus.NOT_FOUND);
            }

            /* Check if user already exists for the trainer*/
            UserDto oUser = userServiceI.findByLogin(trainerLoginDto.getUserName());

            if (oUser != null) {
                throw new AppException("User name already exists for the trainer", HttpStatus.NOT_FOUND);
            }

            /*Create User Entity*/
            UserDto userDto = userServiceI.register(signUpDto);
            trainerLoginDto.setUserId(userDto.getId());
            /*Create Login Entity*/
            TrainerLoginEntity trainerLoginEntity = trainerLoginMapper.toTrainerLoginEntity(trainerLoginDto);
            TrainerLoginEntity savedItem = trainerLoginRepository.save(trainerLoginEntity);
            TrainerLoginDto savedTrainerLoginDto = trainerLoginMapper.toTrainerLoginDto(savedItem);

            return savedTrainerLoginDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TrainerLoginDto> getTrainerLoginEntity() {
        List<TrainerLoginEntity> trainerLoginEntities = trainerLoginRepository.findAll();
        List<TrainerLoginDto> trainerLoginDtoList = trainerLoginMapper.toTrainerLoginDtoList(trainerLoginEntities);
        return trainerLoginDtoList;
    }

    @Override
    public TrainerLoginDto updateTrainerLoginEntity(TrainerLoginDto trainerLoginDto, long id) {
        try {

            /*Check if there is a login*/

            Optional<TrainerLoginEntity> oTrainerLoginEntity = trainerLoginRepository.findById(id);

            if (!oTrainerLoginEntity.isPresent()) {
                throw new AppException("Old Trainer Login not found!", HttpStatus.NOT_FOUND);
            }

            /*Check if other users has the same username*/
            List<User> userList = userServiceI.checkIfUserNameExistForOtherUsers(trainerLoginDto.getUserName(), trainerLoginDto.getUserId());

            if (userList != null && userList.size() > 0) {
                throw new AppException("User name found for other users!", HttpStatus.NOT_FOUND);
            }

            TrainerLoginEntity oldTrainerLoginEntity = oTrainerLoginEntity.get();
            oldTrainerLoginEntity.setUserName(trainerLoginDto.getUserName());

            TrainerLoginEntity savedTrainerLoginEntity = trainerLoginRepository.save(oldTrainerLoginEntity);

            TrainerLoginDto savedTrainerLoginDto = trainerLoginMapper.toTrainerLoginDto(savedTrainerLoginEntity);

            /*Update Password*/
            UserDto userDto = userServiceI.updatePassword(trainerLoginDto.getUserName(), trainerLoginDto.getPassword(), trainerLoginDto.getUserId());

            return savedTrainerLoginDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
