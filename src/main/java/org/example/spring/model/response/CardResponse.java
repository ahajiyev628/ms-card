package org.example.spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponse {
    private Long id;
    private String pan;
    private String cvv;
    private String cardHolder;
    private LocalDate expireDate;
}
