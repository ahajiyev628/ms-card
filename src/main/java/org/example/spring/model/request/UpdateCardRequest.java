package org.example.spring.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardRequest {
    private String pan;
    private LocalDate expireDate;
    private String cvv;
}
