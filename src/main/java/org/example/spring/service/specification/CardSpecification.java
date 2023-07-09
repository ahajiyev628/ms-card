package org.example.spring.service.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.example.spring.dao.entity.CardEntity;
import org.example.spring.model.criteria.CardCriteria;
import org.example.spring.util.PredicateUtil;
import org.springframework.data.jpa.domain.Specification;

import static org.example.spring.model.constants.CriteriaConstants.*;
import static org.example.spring.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class CardSpecification implements Specification<CardEntity> {

    private CardCriteria cardCriteria;

    @Override
    public Predicate toPredicate(Root<CardEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(cardCriteria.getCardHolder(),
                        cardHolder -> criteriaBuilder.like(root.get(CARD_HOLDER), applyLikePattern(cardHolder)))
                .addNullSafety(cardCriteria.getCreatedAt(),
                        createdAt -> criteriaBuilder.greaterThanOrEqualTo(root.get(String.valueOf(CREATED_AT)), createdAt))
                .addNullSafety(cardCriteria.getExpireDate(),
                        expireDate -> criteriaBuilder.lessThanOrEqualTo(root.get(String.valueOf(EXPIRE_DATE)), expireDate))
                .build();
        return criteriaBuilder.and(predicates);
    }
}
