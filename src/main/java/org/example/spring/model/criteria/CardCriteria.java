package org.example.spring.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring.model.enums.CardSortDirection;
import org.example.spring.model.enums.CardSortFields;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCriteria {
    // Criteria is nullable
    private String cardHolder;
    private LocalDate expireDate;
    private LocalDateTime createdAt;
    private CardSortFields cardSortFields;
    private CardSortDirection cardSortDirection;
}
