package com.company.first_project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;  // 404, 200
    private String message;   // nod found
    private int code;       // 200
    private T content;
    private List<ErrorDto> errorDtoList;

    // 0 --> ok, -1 is not found, -2 validation error,-3 db error;


}
