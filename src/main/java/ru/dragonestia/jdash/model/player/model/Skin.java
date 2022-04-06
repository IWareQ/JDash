package ru.dragonestia.jdash.model.player.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Skin {

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

    private Skin() {

    }
}
