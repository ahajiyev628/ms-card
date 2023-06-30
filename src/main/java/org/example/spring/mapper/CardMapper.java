package org.example.spring.mapper;

import org.example.spring.dao.entity.CardEntity;
import org.example.spring.model.enums.CardStatus;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.model.request.UpdateCardRequest;
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

    public static CardEntity buildCardEntity(SaveCardRequest request) {
        return CardEntity.builder()
                .status(CardStatus.ACTIVE)
                .pan(request.getPan())
                .cvv(request.getCvv())
                .cardHolder(request.getCardHolder())
                .expireDate(request.getExpireDate())
                .build();
    }

    public static void updateCardEntity(CardEntity card,
                                              UpdateCardRequest request) {
        card.setPan(request.getPan());
        card.setCvv(request.getCvv());
        card.setExpireDate(request.getExpireDate());
    }
}
