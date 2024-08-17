package org.example.spring.controller

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.example.spring.exception.ErrorHandler
import org.example.spring.model.response.CardResponse
import org.example.spring.service.CardService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDate

class CardControllerTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardService cardService
    private MockMvc mockMvc

    void setup() {
        cardService = Mock()
        def cardController = new CardController(cardService)
        mockMvc = MockMvcBuilders.standaloneSetup(cardController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetCardById"() {
        given:
        def id = 1L
        def url = "/v1/cards/$id"
        def responseView = new CardResponse(1L, "2", "3", "4", LocalDate.of(2020,1,1))
        def expectedResponse = '''
                {
                    "id": 1,
                    "pan": "2",
                    "cvv": "3",
                    "cardHolder": "4",
                    "expireDate": [2020,1,1]
                }
        '''

        when:
        def result = mockMvc.perform(
                get(url)
        ).andReturn()

        then:
        1 * cardService.getCardById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }
}
