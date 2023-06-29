package org.example.spring.mapper;

import org.example.spring.dao.entity.CardEntity;
import org.example.spring.model.response.CardResponse;

public class CardMapper {
    public static CardResponse buildCardResponse(CardEntity card) {
        return CardResponse.builder()
                .id(card.getId())
                .pan(card.getPan())
                .cvv(card.getCvv())
                .cardHolder(card.getCardHolder())
                .expireDate(card.getExpireDate())
                .build();
    }
}
