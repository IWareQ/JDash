package ru.dragonestia.jdash.gd.player.model;

import lombok.Getter;
import lombok.Setter;
import ru.dragonestia.jdash.gd.account.model.Account;

@Getter
public class Player {

    private int uid;
    private int accountId;
    @Setter private int stars;
    @Setter private int demons;
    @Setter private int coins;
    @Setter private int userCoins;
    @Setter private int diamonds;
    @Setter private int orbs;

    private transient Account account;

    private Player() {

    }

    public void init(Account account) {
        this.account = account;
    }
}
