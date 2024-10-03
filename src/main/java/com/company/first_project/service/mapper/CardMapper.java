package com.company.first_project.service.mapper;

import com.company.first_project.dto.CardDto;
import com.company.first_project.dto.UsersDto;
import com.company.first_project.module.Card;
import com.company.first_project.module.Users;
import com.company.first_project.service.impl.CardServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
//@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class CardMapper {
//    @Lazy
//    @Autowired
//    protected final UserMapper userMapper;




    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    public abstract Card toEntity(CardDto cardDto);  //Kiruvchi malumot id kk emas

    @Mapping(target = "cardId", source = "id")
    @Mapping(target = "users", ignore = true)
    public abstract CardDto toDto(Card card);  //chiquvchi malumot hamma narsa kk


//    @Named(value = "toDtoWithUser")
//    @Mapping(target = "cardId", source = "id")
//    @Mapping(target = "users", expression = "java(this.userMapper.toEntity(card.getUsers()))")
//    public abstract CardDto toDtoWithUser(Card card);

    @Mapping(target = "cardId", source = "id")
    public abstract List<CardDto> todoWithList(List<Card> list);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Card checkCardFields(@MappingTarget Card oldCard, CardDto dto);




}
