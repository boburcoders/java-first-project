package com.company.first_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersDto {
    private Integer userId;
    @NotBlank(message = "Firstname cannot be empty or null.")
    private String firstname;
    @NotBlank(message = "Lastname cannot be empty or null.")
    private String lastname;
    @Email(message = "Email is not valid.")
    private String email;
    @NotBlank(message = "Password cannot be empty or null.")
    private String password;
    @NotNull(message = "Age cannot be empty or null.")
    private Integer age;

    List<CardDto> cardList;
    List<ProductDto> productsList;
}
