package org.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/cards/cache")
public class CacheController {
    private final CacheService cacheService;

    @GetMapping("/{id}")
    public CardEntity getCardEntity(@PathVariable Long id) {
        return cacheService.getCardById(id);
    }

    @GetMapping("/update/{id}")
    public CardEntity updateCardEntity(@PathVariable Long id) {
        return cacheService.updateCache(id);
    }

    @GetMapping("/clear")
    public void clearCardEntity() {
        cacheService.clearCache();
    }
}