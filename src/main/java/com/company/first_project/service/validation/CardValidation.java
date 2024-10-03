package com.company.first_project.service.validation;


import com.company.first_project.dto.CardDto;
import com.company.first_project.dto.ErrorDto;
import com.company.first_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {
    private final UserRepository userRepository;

    public List<ErrorDto> cardValidation(CardDto dto) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if (dto.getUserId() == null) {
            errorDtoList.add(new ErrorDto("UserId", "UserId cannot be empty."));

        } else {
            if (!this.userRepository.existsByIdAndDeletedAtIsNull(dto.getUserId())) {
                errorDtoList.add(new ErrorDto("userId", "userId not fund"));
            }
        }
        if (dto.getCardCode() == null
                || dto.getCardCode().trim().isEmpty()
                || checkCardNumber(dto.getCardCode())
                || dto.getCardCode().length() != 4
        ) {
            errorDtoList.add(new ErrorDto("Card Code", "Card Code cannot be empty or null."));

        }
        if (dto.getCardNumber() == null
                || dto.getCardNumber().trim().isEmpty()
                || checkCardNumber(dto.getCardNumber())
                || dto.getCardNumber().length() != 16
        ) {
            errorDtoList.add(new ErrorDto("Card Number", "Card Number cannot be empty or null or length must be 16."));
        }
        if (dto.getCardType() == null || dto.getCardType().trim().isEmpty()) {
            errorDtoList.add(new ErrorDto("Card Type", "Card Type cannot be empty or null."));
        }
        if (dto.getExpiredDate() == null) {
            errorDtoList.add(new ErrorDto("Card Expired Date", "Card Expired Date cannot be empty."));

        }
        return errorDtoList;

    }

    private boolean checkCardNumber(String cardValue) {
        for (char value : cardValue.toCharArray()) {
            if (!Character.isDigit(value)) {
                return true;
            }
        }
        return false;
    }

    public List<ErrorDto> validateCardWithUserId(CardDto dto) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if (dto.getUserId() != null) {
            if (this.userRepository.existsByIdAndDeletedAtIsNull(dto.getUserId())) {
                errorDtoList.add(new ErrorDto("UserId", "UserID not found"));
            }
        }
        return errorDtoList;
    }
}
