package com.company.first_project.service.validation;

import com.company.first_project.dto.ErrorDto;
import com.company.first_project.dto.ProductDto;
import com.company.first_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidation {
    private final UserRepository userRepository;

    public List<ErrorDto> productValidation(ProductDto dto){
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if (dto.getUserId() == null) {
            errorDtoList.add(new ErrorDto("UserId", "UserId cannot be empty."));

        } else {
            if (!this.userRepository.existsByIdAndDeletedAtIsNull(dto.getUserId())) {
                errorDtoList.add(new ErrorDto("userId", "userId not fund"));
            }
        }

        return errorDtoList;
    }

}
