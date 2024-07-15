package org.example.spring.mapper;

import org.example.spring.dao.entity.CardEntity;
import org.example.spring.model.enums.CardStatus;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.model.request.UpdateCardRequest;
import org.example.spring.model.response.CardResponse;
import org.example.spring.model.response.PageableCardResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    public static PageableCardResponse mapToPageableResponse(Page<CardEntity> cardsPage) {
        List<CardResponse> cardResponses = cardsPage.getContent()
                .stream()
                .map(CardMapper::buildCardResponse)
                .collect(Collectors.toList());

        return PageableCardResponse.builder()
                .cards(cardResponses)
                .hasNextPage(cardsPage.hasNext())
                .lastPageNumber(cardsPage.getTotalPages())
                .totalElements(cardsPage.getTotalElements())
                .build();
    }
}
