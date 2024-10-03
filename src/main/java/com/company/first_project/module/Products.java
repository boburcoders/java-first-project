package com.company.first_project.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Products extends BaseEntity{

    private String prodName;
    private String prodType;
    private String prodColor;
    private Float prodPrice;
    private Integer userId;

}
