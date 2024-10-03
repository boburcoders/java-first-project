package com.company.first_project.service.impl;

import com.company.first_project.dto.ApiResponse;
import com.company.first_project.dto.CardDto;
import com.company.first_project.dto.ErrorDto;
import com.company.first_project.exceptions.ContentNotFoundException;
import com.company.first_project.module.Card;
import com.company.first_project.module.Users;
import com.company.first_project.repository.CardRepository;
import com.company.first_project.repository.UserRepository;
import com.company.first_project.service.CardService;
import com.company.first_project.service.mapper.CardMapper;
import com.company.first_project.service.mapper.UserMapper;
import com.company.first_project.service.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final UserMapper userMapper;
    private final CardValidation cardValidation;
    private final UserServiceImpl userService;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;


    @Override
    public ApiResponse<CardDto> createCard(CardDto dto) {
        List<ErrorDto> errorDtoList = this.cardValidation.cardValidation(dto);
        if (!errorDtoList.isEmpty()) {
            return ApiResponse.<CardDto>builder()
                    .code(-2)
                    .message("Validation Error")
                    .errorDtoList(errorDtoList)
                    .build();
        }
        Optional<Users> optionalUsers = this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        StringBuilder holderName = new StringBuilder();
        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            holderName.append(users.getFirstname()).append(" ").append(users.getLastname());

        }
        Card card = this.cardMapper.toEntity(dto);
        card.setCardHolderName(holderName.toString());
        card.setCreatedAt(LocalDate.now());
        Card savedCard = this.cardRepository.save(card);

        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("Ok")
                .content(this.cardMapper.toDto(savedCard))
                .build();
    }

    @Override
    public ApiResponse<CardDto> getCardById(Integer cardId) {
        Optional<Card> optionalCard = this.cardRepository.findByIdAndDeletedAtIsNull(cardId);
        if (optionalCard.isEmpty()) {
            throw new ContentNotFoundException(String.format("This card %d id not found", cardId));
        }
        Card card = optionalCard.get();
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(card))
                .build();
    }

    @Override
    public ApiResponse<CardDto> updateCard(Integer cardId, CardDto dto) {
        Optional<Card> optionalCard = this.cardRepository.findByIdAndDeletedAtIsNull(cardId);
        if (optionalCard.isEmpty()) {
            throw new ContentNotFoundException(String.format("This card %d id not found", cardId));
        }

        Card card = optionalCard.get();
        card.setUpdatedAt(LocalDate.now());
        Card updatedCard = this.cardMapper.checkCardFields(card, dto);
        Card savedCard = this.cardRepository.save(updatedCard);
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("Ok")
                .content(this.cardMapper.toDto(savedCard))
                .build();


    }

    @Override
    public ApiResponse<CardDto> deleteCard(Integer cardId) {
        Optional<Card> optionalCard = this.cardRepository.findByIdAndDeletedAtIsNull(cardId);
        if (optionalCard.isEmpty()) {
            throw new ContentNotFoundException(String.format("This card %d id not found", cardId));
        }
        Card card = optionalCard.get();
        card.setDeletedAt(LocalDate.now());
        Card savedCard = this.cardRepository.save(card);
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("Ok")
                .content(this.cardMapper.toDto(savedCard))
                .build();
    }

    @Override
    public ApiResponse<List<CardDto>> getAllCard() {
        List<Card> cardList = this.cardRepository.findAllByDeletedAtIsNull();

        if (cardList.isEmpty()) {
            throw new ContentNotFoundException("This cardlist is empty");
        }
        return ApiResponse.<List<CardDto>>builder()
                .success(true)
                .message("Ok")
                .content(this.cardMapper.todoWithList(cardList))
                .build();
    }


    public List<CardDto> getAllCardsByUserId(Integer userId) {
        return this.cardMapper.todoWithList(
                this.cardRepository.findAllByUserIdAndDeletedAtIsNull(userId)
        );
    }
    public Users getUserByCardId(Integer cardId){
        return this.userRepository.getUsersById(cardId);
    }
}
