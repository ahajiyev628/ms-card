package org.example.spring.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.spring.model.cache.CardDto;
import org.example.spring.util.CacheUtil;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CacheServiceRedis {
    private final CacheUtil cacheUtil;

    @SneakyThrows
    @PostConstruct  // the following method is not called from anywhere but PostConstruct will execute the method directly when the application was raised
    public void test() {
        cacheUtil.saveToCache(getKey(100L), new CardDto("250", "Allahverdi"), 100L, ChronoUnit.SECONDS);

//        Thread.sleep(2000L); // here expireTime tested
        var card = cacheUtil.getBucket(getKey(100L));
        System.out.println(card.toString());
    }

    private String getKey(Long id) {
        return "ms-card: " + id;
    }
}
