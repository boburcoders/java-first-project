package com.company.first_project.service.mapper;

import com.company.first_project.dto.ProductDto;
import com.company.first_project.module.Products;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProdMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Products toEntity(ProductDto dto);  //kiruvchu malumot id kk emas

    @Mapping(target = "prodId", source = "id")
    public abstract ProductDto toDto(Products products);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Products updateProductsAllFields(@MappingTarget Products oldCard, ProductDto dto);

    @Mapping(target = "prodId", source = "id")
    public abstract List<ProductDto> toDtoWithList(List<Products> productsList);

}
