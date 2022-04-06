package ru.dragonestia.jdash.exceptions;

import lombok.Getter;

public class AccountException extends RuntimeException {

    @Getter
    private final String message;

    public AccountException(String message) {
        this.message = message;
    }
}
