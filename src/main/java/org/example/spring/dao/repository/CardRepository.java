package org.example.spring.dao.repository;

import org.example.spring.dao.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
