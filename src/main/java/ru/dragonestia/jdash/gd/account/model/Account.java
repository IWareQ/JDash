package ru.dragonestia.jdash.gd.account.model;

import lombok.Getter;

import java.sql.Date;

@Getter
public class Account {

    private int uid;
    private String login;
    private String password;
    private String email;
    private boolean admin;
    private String twitter;
    private String twitch;
    private String youtube;
    private Date register;
    private boolean activated;

    private Account() {

    }
}
