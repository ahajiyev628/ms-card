package org.example.spring.controller

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.example.spring.exception.ErrorHandler
import org.example.spring.model.request.SaveCardRequest
import org.example.spring.model.response.CardResponse
import org.example.spring.service.CardService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

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
        // as in the following example, manual mapping is better to use rather than automatic mapper in order to be informed when any field name was updated
        def expectedResponse = '''
                {
                    "id": 1,
                    "pan": "2",
                    "cvv": "3",
                    "cardHolder": "4",
                    "expireDate": [2020,1,1]  // date should be in array format
                }
        '''

        when:
        def result = mockMvc.perform(
                get(url)    // we are testing the method which use GET request
        ).andReturn()

        then:
        /*  tested whether the method return CardResponse,
            then tested the response status, and finally whether the returned data matches with the actual ResponseBody */
        1 * cardService.getCardById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }

    def "TestSaveCard"() {
        given:
        def url = "/v1/cards"
        def dto = new SaveCardRequest("1", LocalDate.of(2020,1,1), "2", "3")
        def requestBody = '''
                {
                    "pan": "1",
                    "expireDate": "2020-01-01",
                    "cvv": "2",
                    "cardHolder": "3"
                }
        '''

        when:
        def result = mockMvc.perform(
                post(url)    // we are testing the method which use POST request
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn()

        then:
        1 * cardService.saveCard(dto) // does not return anything since it is void method

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }
}
