package com.bit.backend.services;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.User;

import java.util.List;

public interface UserServiceI {
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignUpDto signUpDto);
    List<Integer> getAuthIds(long userId);
    SystemPrivilegeListDto getSystemPrivileges();
    List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto);
    UserDto findByLogin(String userName);
    List<User> checkIfUserNameExistForOtherUsers(String userName, Long userId);
    UserDto updatePassword(String userName, String password, Long userId);
    UserDto getUserById(long userId);
}
