package org.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableCaching  // enable L3 caching which belongs to SpringBoot
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
//@RequiredArgsConstructor
//public class Main implements CommandLineRunner {
//    private final CardService cardService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(cardService.getCardById(6L));
//    }
//}