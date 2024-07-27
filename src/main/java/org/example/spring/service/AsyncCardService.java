package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.dao.repository.CardRepository;
import org.example.spring.model.request.SaveCardRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.example.spring.mapper.CardMapper.buildCardEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class AsyncCardService {
    private final CardRepository cardRepository;

    @SneakyThrows
    @Async // Proxy design patter working principle
    public void saveCard(SaveCardRequest request) {
        // To apply async, the method should be public, otherwise, when the bean was created, its method will not be accessed
        log.info("Card save process started");
        Thread.sleep(5000L);
        log.info("Card save process successfully completed");
        cardRepository.save(buildCardEntity(request)); // save method will add a new record to the table because there is no any id given in the query
    }
}
