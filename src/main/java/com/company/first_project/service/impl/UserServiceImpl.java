package com.company.first_project.service.impl;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.ErrorDto;
import com.company.first_project.dto.UsersDto;
import com.company.first_project.exceptions.ContentNotFoundException;
import com.company.first_project.module.Card;
import com.company.first_project.module.Users;
import com.company.first_project.repository.CardRepository;
import com.company.first_project.repository.UserRepository;
import com.company.first_project.service.UserService;
import com.company.first_project.service.mapper.UserMapper;
import com.company.first_project.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final CardRepository cardRepository;


    @Override
    public ApiResponse<UsersDto> createUser(UsersDto dto) {
        List<ErrorDto> errorDtoList = this.userValidation.validateUser(dto);
        if (!errorDtoList.isEmpty()) {
            return ApiResponse.<UsersDto>builder()
                    .code(-2)
                    .message("Validation Error")
                    .errorDtoList(errorDtoList)
                    .build();
        }

        Users users = this.userMapper.toEntity(dto);
        users.setCreatedAt(LocalDate.now());
        Users savedUser = this.userRepository.save(users);
        return ApiResponse.<UsersDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDto(savedUser))
                .build();
    }

    @Override
    public ApiResponse<UsersDto> getUserById(Integer userId) {
        Optional<Users> optionalUser = this.userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException(String.format("This user %d id not found", userId));
        }

        return ApiResponse.<UsersDto>builder()
                .success(true)
                .message("Ok")
                .content(this.userMapper.toDtoWithAllEntity(optionalUser.get()))
                .build();


    }

    @Override
    public ApiResponse<UsersDto> updateUserById(Integer userId, UsersDto dto) {
        List<ErrorDto> errorDtoList = this.userValidation.validateUser(dto);
        if(!errorDtoList.isEmpty()){
            return ApiResponse.<UsersDto>builder()
                    .code(-2)
                    .message("Validation Error")
                    .errorDtoList(errorDtoList)
                    .build();
        }
        Optional<Users> optionalUser = this.userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException(String.format("This user %d id not found", userId));
        }
        Users users = optionalUser.get();

        users.setUpdatedAt(LocalDate.now());

        Users updateUser = this.userMapper.updatedUserAllField(users, dto);
        List<Card> cardlist = this.cardRepository.findAllByUserIdAndDeletedAtIsNull(userId);
        for (Card card : cardlist) {
            card.setCardHolderName(String.format("%s %s", updateUser.getFirstname(), updateUser.getLastname()));
        }
        this.cardRepository.saveAll(cardlist);
        Users savedUser = this.userRepository.save(updateUser);
        return ApiResponse.<UsersDto>builder()
                .success(true)
                .message("Ok")
                .content(this.userMapper.toDto(savedUser))
                .build();
    }

    @Override
    public ApiResponse<UsersDto> deleteUserById(Integer userId) {
        Optional<Users> optionalUser = this.userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException(String.format("This user %d id not found", userId));
        }
        Users users = optionalUser.get();
        users.setDeletedAt(LocalDate.now());
        Users savedUser = this.userRepository.save(users);
        return ApiResponse.<UsersDto>builder()
                .success(true)
                .message("Ok")
                .content(this.userMapper.toDto(savedUser))
                .build();
    }

    @Override
    public ApiResponse<List<UsersDto>> getAllUsers() {
        List<Users> usersList1 = this.userRepository.findAllByDeletedAtIsNull();
        if (usersList1.isEmpty()) {
            throw new ContentNotFoundException("This userList is empty");
        }

        return ApiResponse.<List<UsersDto>>builder()
                .success(true)
                .message("Ok")
                .content(this.userMapper.todoWithList(usersList1))
                .build();
    }




}
