package org.example.spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableCardResponse {
    private List<CardResponse> cards;
    private boolean hasNextPage;
    private int lastPageNumber;
    private long totalElements;
}

