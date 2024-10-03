package com.company.first_project.controller;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.CardDto;
import com.company.first_project.module.Card;
import com.company.first_project.service.CardService;
import com.company.first_project.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<CardDto> createCard(@RequestBody CardDto dto) {
        return cardService.createCard(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse<CardDto> getCard(@RequestParam(value = "id") Integer cardId) {

        return cardService.getCardById(cardId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<CardDto> updateCard(
            @RequestParam(value = "id") Integer cardId,
            @RequestBody CardDto dto) {
        return cardService.updateCard(cardId, dto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<CardDto> deleteCard(@RequestParam(value = "id") Integer cardId) {

        return cardService.deleteCard(cardId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public ApiResponse<List<CardDto>> getAllCards() {
        return cardService.getAllCard();
    }


}
