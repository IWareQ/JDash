package ru.dragonestia.jdash.model.player.model;

import lombok.Getter;
import lombok.Setter;
import ru.dragonestia.jdash.model.account.model.Account;
import ru.dragonestia.jdash.model.player.IPlayer;

@Getter
public class Player implements IPlayer {

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

    @Override
    public int getId() {
        return uid;
    }
}
