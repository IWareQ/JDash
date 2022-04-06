package ru.dragonestia.jdash.model.account;

import lombok.Getter;

public class AccountException extends RuntimeException {

    @Getter
    private final String message;

    public AccountException(String message) {
        this.message = message;
    }
}
