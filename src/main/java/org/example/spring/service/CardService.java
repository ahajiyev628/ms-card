package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.dao.repository.CardRepository;
import org.example.spring.mapper.CardMapper;
import org.example.spring.model.enums.CardStatus;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.model.request.UpdateCardRequest;
import org.example.spring.model.response.CardResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.spring.mapper.CardMapper.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponse getCardById(Long id) {
        var card = fetchCardIfExist(id);
        return buildCardResponse(card);
    }

    public List<CardResponse> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(CardMapper::buildCardResponse)
                .collect(Collectors.toList());
    }

    public void saveCard(SaveCardRequest request) {
        cardRepository.save(buildCardEntity(request));
    }

    public void updateCard(Long id,
                           UpdateCardRequest request) {
        var card = fetchCardIfExist(id);
        updateCardEntity(card, request);
        cardRepository.save(card);  // will update the card info for the specified id (will not add a new record)
    }

    public void deleteCard(Long id) {
        var card = fetchCardIfExist(id);
        card.setStatus(CardStatus.BLOCKED);
        cardRepository.save(card);  // update the card status in table
        //  cardRepository.delete(card);
        /*  it`s not recommended to delete a record from db,
            we can change the status  */
    }

    private CardEntity fetchCardIfExist(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("CARD_NOT_FOUND")
        );
    }
}
