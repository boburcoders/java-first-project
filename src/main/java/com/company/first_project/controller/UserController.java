package com.company.first_project.controller;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.UsersDto;
import com.company.first_project.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor      //berilgan fildlardan constructor qiladi "final" keyword qoyilsa oladi
//@AllArgsConstructor       //barcha fieldlardan bitta constructor qiladi
@RestController
@RequestMapping(value = "users")    // endpoints is urls
public class UserController {
    private final UserService userService;      // @RequiredArgsConstructor kerakli bolgan


    public UserController(UserService userService) {  // Custom create constructor
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UsersDto> createUser(@RequestBody @Valid UsersDto dto) {
        return this.userService.createUser(dto);
    }

    @GetMapping
    //todo--> localhost://users?id=1
    public ApiResponse<UsersDto> getUser(@RequestParam(value = "id") Integer userId) {

        return this.userService.getUserById(userId);
    }

    @PutMapping
    public ApiResponse<UsersDto> updateUser(
            @RequestBody UsersDto dto,
            @RequestParam(value = "id") Integer userId) {
        return this.userService.updateUserById(userId, dto);
    }

    @DeleteMapping
    public ApiResponse<UsersDto> deleteUser(@RequestParam(value = "id") Integer userId) {
        return this.userService.deleteUserById(userId);
    }

    @GetMapping(value = "/get-all")
    public ApiResponse<List<UsersDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }


}
