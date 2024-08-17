package org.example.spring.scheduler

import org.example.spring.service.CardService
import spock.lang.Specification

class CardSchedulerTest extends Specification {
    private CardService cardService
    private CardScheduler cardScheduler

    def setup() {
        cardService = Mock()
        cardScheduler = new CardScheduler(cardService)
    }

    // schedule time cannot be tested since the application is not running while testing
    def "TestCardSchedule"() {
        when:
        cardScheduler.cardSchedule()

        then:
        1 * cardService.test()
    }
}
