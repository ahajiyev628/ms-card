package org.example.spring.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCriteria {
    private String cardHolder;
    private LocalDate expireDate;
    private LocalDateTime createdAt;
}
