package com.company.first_project.service;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.CardDto;
import com.company.first_project.module.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    ApiResponse<CardDto> createCard(CardDto dto);

    ApiResponse<CardDto> getCardById(Integer cardId);

    ApiResponse<CardDto> updateCard(Integer cardId, CardDto dto);

    ApiResponse<CardDto> deleteCard(Integer cardId);

    ApiResponse<List<CardDto>> getAllCard();
}
