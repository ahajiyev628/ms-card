package org.example.spring.service;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.dao.repository.CardRepository
import org.example.spring.exception.NotFoundException
import org.example.spring.model.enums.CardStatus;
import spock.lang.Specification;

class CardServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()  // will be used if random data is required ("io.github.benas:random-beans:3.9.0")
    // CardService will be tested where CardRepository has been injected
    private CardService cardService
    private CardRepository cardRepository
    private AsyncCardService asyncCardService

    def setup() {   // like the constructor
        cardRepository = Mock() // repo will be mocked
        asyncCardService = Mock()
        cardService = new CardService(cardRepository, asyncCardService)   // a new object will be created based on mocked repository, so it will never been a real call to DB
    }

    //  test success case
    def "TestGetCardById success"() {
        given:
        def id = random.nextObject(Long)    // or random.nextLong()
        def entity = random.nextObject(CardEntity)

        when:
        def actual = cardService.getCardById(id)

        then:
        // since private method (fetchCardIfExist) cannot be called, all logics inside it will be tested separately.
        // static method (buildCardResponse) cannot be mocked since it does not belong to Object, so it will be called as it is.
        // 1*  shows that the method is called only once in the tested method. >> shows what the method should return.
        1 * cardRepository.findById(id) >> Optional.of(entity)
        // also test what static buildCardResponse method does
        actual.id == entity.id
        actual.cardHolder == entity.cardHolder
        actual.expireDate == entity.expireDate
        actual.cvv == entity.cvv
        actual.pan == entity.pan
    }

    //  test error case
    def "TestGetCardById Entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        cardService.getCardById(id)

        then:
        1 * cardRepository.findById(id) >> Optional.empty()

        // tests if Exception is thrown and the error message is CARD_NOT_FOUND
        NotFoundException e = thrown()
        e.message == "CARD_NOT_FOUND"

        // thrown(NotFoundException)   // tests if exception is thrown in fail case
    }

    def "TestDeleteCard success"() {
        given:
        def id = random.nextLong()
        def card = random.nextObject(CardEntity)

        when:
        cardService.deleteCard(id)

        then:
        1 * cardRepository.findById(id) >> Optional.of(card)
        1 * cardRepository.save(card)
        card.status == CardStatus.BLOCKED
    }

    def "TestDeleteCard error when card not found"() {
        given:
        def id = random.nextLong()

        when:
        cardService.deleteCard(id)

        then:
        1 * cardRepository.findById(id) >> Optional.empty()
        0 * cardRepository.save()   // test in case if no entity found, it should not call save method

        NotFoundException e = thrown()
        e.message == "CARD_NOT_FOUND"
    }
}
