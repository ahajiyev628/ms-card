package org.example.spring.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardSortFields {
    ID("id"), CREATED_AT("createdAT");
    private final String FieldName;
}
