package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Simple Logging Facade for Java
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.dao.repository.CardRepository;
import org.example.spring.mapper.CardMapper;
import org.example.spring.model.criteria.CardCriteria;
import org.example.spring.model.criteria.PageCriteria;
import org.example.spring.model.enums.CardSortDirection;
import org.example.spring.model.enums.CardStatus;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.model.request.UpdateCardRequest;
import org.example.spring.model.response.CardResponse;
import org.example.spring.model.response.PageableCardResponse;
import org.example.spring.service.specification.CardSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.spring.mapper.CardMapper.*;

@Service(value = "CardService")
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final AsyncCardService asyncCardService;

    public CardResponse getCardById(Long id) {
        log.info("ActionLog.getCardById.start id: {}", id);
        var card = fetchCardIfExist(id);
        log.info("ActionLog.getCardById.end id: {}", id);
        return buildCardResponse(card);
    }

    public List<CardResponse> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(CardMapper::buildCardResponse)
                .collect(Collectors.toList());
    }

    public void saveCard(SaveCardRequest request) {
        // here any exception cases can be handled, that`s why we do not make this method async and instead, create a new class which contains async methods
        asyncCardService.saveCard(request);
    }

    public void updateCard(Long id,
                           UpdateCardRequest request) {
        var card = fetchCardIfExist(id);
        updateCardEntity(card, request);
        cardRepository.save(card);  // will update the card info for the specified id (will not add a new record because id is given)
    }

    public void deleteCard(Long id) {
        var card = fetchCardIfExist(id);
        card.setStatus(CardStatus.BLOCKED);
        cardRepository.save(card);  // update the card status in the table instead of deleting the record
        //  cardRepository.delete(card);
    }

    private CardEntity fetchCardIfExist(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(
                () -> {
                    log.error("ActionLog.getCard.error id: {}", id);
                    return new RuntimeException("CARD_NOT_FOUND");
                }
        );
    }

    /*
    The working principle of the below method : Let`s say we have 1 million Cards in the Cards table in DB
    In the first step, the Cards table is filtered based on the cardCriteria
    then, the filtered cards are ordered based on the field in the Sort.by() parameters of PageRequest.
    finally, the filtered and ordered cards are divided into pages, and each pages contains specified amount of cards in count parameter
     */
    public PageableCardResponse getCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        //var sortBy = cardCriteria.getCardSortFields().getFieldName();
        //var sortDirection = cardCriteria.getCardSortDirection() == CardSortDirection.ASC ? CardSortDirection.ASC : CardSortDirection.DESC;
        var cardsPage = cardRepository.findAll(
                new CardSpecification(cardCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount()//, Sort.by(String.valueOf(sortDirection), sortBy)
                ) // PageRequest class is used to build the pagination
                // there is one more default parameter (Sort.by()) which is used for sorting in the page based on the specific field. Default is random :)
        );
        return mapToPageableResponse(cardsPage);
    }
}
