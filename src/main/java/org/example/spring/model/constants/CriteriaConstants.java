package org.example.spring.model.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CriteriaConstants {
    String CARD_HOLDER = "cardHolder";
    LocalDateTime CREATED_AT = LocalDateTime.parse("createdAt");
    LocalDate EXPIRE_DATE = LocalDate.parse("expireDate");
    Integer PAGE_DEFAULT_VALUE = 0;
    Integer COUNT_DEFAULT_VALUE = 5;
}

