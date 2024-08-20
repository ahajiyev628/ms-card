package org.example.spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    private final String cardQ;
    private final String cardDLQ;
    private final String cardQExchange;
    private final String cardDLQExchange;
    private final String cardQKey;
    private final String cardDLQKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.card}") String cardQ,
                          @Value("${rabbitmq.queue.card-dlq}") String cardDLQ) {
        this.cardQ = cardQ;
        this.cardDLQ = cardDLQ;
        this.cardQExchange = cardQ + "_Exchange";
        this.cardDLQExchange = cardDLQ + "_Exchange";
        this.cardQKey = cardQ + "_Key";
        this.cardDLQKey = cardDLQ + "_Key";
    }

    @Bean
    DirectExchange cardDLQExchange() {
        return new DirectExchange(cardDLQExchange);
    }

    @Bean
    DirectExchange cardQExchange() {
        return new DirectExchange(cardQExchange);
    }

    @Bean
    Queue cardDLQ() {
        return QueueBuilder.durable(cardDLQ).build();
    }

    @Bean
    Queue cardQ() {
        return QueueBuilder.durable(cardQ)
                .withArgument("x-dead-letter-exchange", cardDLQExchange)
                .withArgument("x-dead-letter-routing-key", cardDLQKey)
                .build();
    }

    @Bean
    Binding cardDLQBinding() {
        return BindingBuilder.bind(cardDLQ())
                .to(cardDLQExchange()).with(cardDLQKey);
    }

    @Bean
    Binding cardQBinding() {
        return BindingBuilder.bind(cardQ())
                .to(cardQExchange()).with(cardQKey);
    }
}
