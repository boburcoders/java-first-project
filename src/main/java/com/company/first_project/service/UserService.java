package com.company.first_project.service;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.UsersDto;
import com.company.first_project.module.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ApiResponse<UsersDto> createUser(UsersDto dto);

    ApiResponse<UsersDto> getUserById(Integer userId);

    ApiResponse<UsersDto> updateUserById(Integer userId, UsersDto dto);

    ApiResponse<UsersDto> deleteUserById(Integer userId);

    ApiResponse<List<UsersDto>> getAllUsers();

}
