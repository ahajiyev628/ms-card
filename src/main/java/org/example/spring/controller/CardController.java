package org.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.criteria.CardCriteria;
import org.example.spring.model.criteria.PageCriteria;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.model.request.UpdateCardRequest;
import org.example.spring.model.response.CardResponse;
import org.example.spring.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.spring.model.response.PageableCardResponse;

import java.util.List;

@RestController // it is the combination of @Controller and @ResponseBody, enables to map the data into json format just with one annotation to communicate with frontend.
@RequestMapping("v1/cards") // instead of writing the base path (domain) in every HTTP methods, it can be specified just once with RequestMapping over the class to avoid repetition.
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{id}")
    public CardResponse getCardById(@PathVariable Long id) {
        // PathVariable annotation extracts values from the URL path and maps them to method parameters in a controller, allowing dynamic handling of variable parts in URLs.
        return cardService.getCardById(id);
    }

    @GetMapping("all")
    public List<CardResponse> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // if the method is void and creates a new record, we need to set CREATED
    public void saveCard(@RequestBody SaveCardRequest request) {
        cardService.saveCard(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // if the method is void and does not create a record, we need to set NO_CONTENT
    public void updateCard(@PathVariable Long id,
                           @RequestBody UpdateCardRequest request) {
        cardService.updateCard(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @GetMapping
    public PageableCardResponse getCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        /*
        No need to write @RequestParam annotation before PageCriteria or CardCriteria.
        They will work the same as if @RequestParam was used, by default.
         */
        return cardService.getCards(pageCriteria, cardCriteria);
    }
}
