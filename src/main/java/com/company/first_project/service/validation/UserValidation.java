package com.company.first_project.service.validation;

import com.company.first_project.dto.ErrorDto;
import com.company.first_project.dto.UsersDto;
import com.company.first_project.module.Users;
import com.company.first_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;
    public List<ErrorDto> validateUser(UsersDto dto) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if(dto.getEmail()!=null){
            if(this.userRepository.existsByEmailAndDeletedAtIsNull(dto.getEmail())){
                errorDtoList.add(new ErrorDto("email","Email is already in use"));
            }
        }
        return errorDtoList;
    }

}
