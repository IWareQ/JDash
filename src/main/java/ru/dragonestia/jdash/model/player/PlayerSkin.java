package ru.dragonestia.jdash.model.player;

import lombok.Getter;
import lombok.Setter;

// todo: Этот класс можно переименовать во что-нибудь получше.

@Getter
public class PlayerSkin {

    private int uid;
    private int player;
    @Setter private int icon;
    @Setter private int firstColor;
    @Setter private int secondColor;
    @Setter private int iconType;
    @Setter private int special;
    @Setter private int accIcon;
    @Setter private int accShip;
    @Setter private int accBall;
    @Setter private int accBird;
    @Setter private int accDart;
    @Setter private int accRobot;
    @Setter private int accGlow;
    @Setter private int accSpider;
    @Setter private int accExplosion;

    private PlayerSkin() {

    }
}
