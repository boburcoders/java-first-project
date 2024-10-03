package com.company.first_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer prodId;
    private String prodName;
    private String prodType;
    private String prodColor;
    private Float prodPrice;
    private Integer userId;
//    private boolean deleted_at=true;
}
