package com.company.first_project.dto;

import com.company.first_project.module.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer cardId;
    private String cardType;
    private String cardHolderName;
    private String cardNumber;
    private String cardCode;
    private LocalDate expiredDate;
    private Integer userId;
    private Users users;
}
