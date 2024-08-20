package org.example.spring.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.spring.model.request.SaveCardRequest;
import org.example.spring.service.CardService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueListener {
    private final ObjectMapper objectMapper;
    private final CardService cardService;

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.queue.card}")
    public void consume(String message) {
        var dto = objectMapper.readValue(message, SaveCardRequest.class);
        cardService.saveCard(dto);
    }
}
