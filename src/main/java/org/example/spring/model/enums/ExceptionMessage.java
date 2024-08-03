package org.example.spring.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    UNEXPECTED_ERROR("Unexpected error occured"),
    CARD_NOT_FOUND("Card not found");
    private final String message;

}
