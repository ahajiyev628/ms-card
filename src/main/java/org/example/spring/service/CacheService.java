package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.dao.repository.CardRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"cards"})
public class CacheService {
    private final CardRepository cardRepository;
    // Cache annotations working principle is based on Proxy pattern, thats why they are written inside a separate Bean

    @Cacheable
    public CardEntity getCardById(Long id) {
        // within this method, all queried data will be stored in cache until the application is restarted
        return cardRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @CachePut
    public CardEntity updateCache(Long id) {
        // or the cached data can be updated with the following method.
        // it can be used with another method which works on the data which has already cached, so that at the end of the method, this method can be called to update cached data
        // or this method can be scheduled and triggered within specified time interval
        return cardRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    // or after some period of time, all cached data can be deleted. we can specify which cacheNames to clear. in this case all cached data will be cleared, since the param is true.
    @CacheEvict(allEntries = true)
    public void clearCache() {
    }
}
