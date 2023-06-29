package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.dao.repository.CardRepository;
import org.example.spring.model.response.CardResponse;
import org.springframework.stereotype.Service;

import static org.example.spring.mapper.CardMapper.buildCardResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponse getCardById(Long id) {
        var card = cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("CARD_NOT_FOUND")
        );
        return buildCardResponse(card);
    }
}
