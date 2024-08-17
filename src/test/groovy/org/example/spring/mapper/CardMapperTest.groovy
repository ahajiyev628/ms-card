package org.example.spring.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.example.spring.model.enums.CardStatus
import org.example.spring.model.request.SaveCardRequest
import spock.lang.Specification

class CardMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    // Mapper method has only success case
    def "TestBuildCardEntity"() {
        given:
        def card = random.nextObject(SaveCardRequest)

        when:
        def actual = CardMapper.buildCardEntity(card)

        then:
        actual.status == CardStatus.ACTIVE
        actual.pan == card.pan
        actual.cardHolder == card.cardHolder
        actual.cvv == card.cvv
        actual.expireDate == card.expireDate
    }
}
