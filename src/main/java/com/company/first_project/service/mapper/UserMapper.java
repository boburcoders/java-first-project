package com.company.first_project.service.mapper;

import com.company.first_project.dto.UsersDto;
import com.company.first_project.module.Users;
import com.company.first_project.service.impl.CardServiceImpl;
import com.company.first_project.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected CardServiceImpl cardService;
    @Lazy
    @Autowired
    protected ProductServiceImpl productService;


    @Mapping(target = "cardList", ignore = true)
    public abstract Users toEntity(UsersDto dto);

    @Named(value = "toDto")
    @Mapping(target = "cardList", ignore = true)
    @Mapping(target = "productsList", ignore = true)
    @Mapping(target = "userId", source = "id")
    public abstract UsersDto toDto(Users users);

    @Named(value = "toDtoWithAllEntity")

    @Mapping(target = "cardList", expression = "java(this.cardService.getAllCardsByUserId(users.getId()))")
    @Mapping(target = "productsList", expression = "java(this.productService.getAllProductsByUserId(users.getId()))")
    @Mapping(target = "userId", source = "id")
    public abstract UsersDto toDtoWithAllEntity(Users users);

    @IterableMapping(qualifiedByName = "toDto")
    @Mapping(target = "userId", source = "id")
    public abstract List<UsersDto> todoWithList(List<Users> list);

    @Mapping(target = "cardList", ignore = true)
    @Mapping(target = "productsList", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Users updatedUserAllField(@MappingTarget Users users, UsersDto dto);

}
