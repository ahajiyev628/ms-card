package org.example.spring;

import lombok.RequiredArgsConstructor;
import org.example.spring.dao.repository.CardRepository;
import org.example.spring.service.CardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@RequiredArgsConstructor
public class Main implements CommandLineRunner {
    private final CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(cardService.getCardById(6L));
    }
}