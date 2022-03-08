package ru.dragonestia.jdash.gd.util.stat;

import lombok.Getter;
import ru.dragonestia.jdash.gd.account.model.Account;
import ru.dragonestia.jdash.gd.account.model.AccountSettings;
import ru.dragonestia.jdash.gd.player.model.Player;
import ru.dragonestia.jdash.gd.player.model.Skin;

import java.util.HashMap;

public abstract class AbstractPlayerStatBuilder {

    protected final Account account;
    protected final Player player;
    protected final Skin skin;
    protected final AccountSettings settings;

    protected final HashMap<StatElement, Object> elements = new HashMap<>();

    public AbstractPlayerStatBuilder(Account account, Player player, Skin skin, AccountSettings settings){
        this.account = account;
        this.player = player;
        this.skin = skin;
        this.settings = settings;

        init();
    }

    public void setElement(StatElement element, Object value) {
        elements.put(element, value);
    }

    protected void init() {
        setElement(StatElement.NAME, account.getLogin());
        setElement(StatElement.CREATOR_POINTS, 0); //TODO: Сделать другое вычисление кп
        setElement(StatElement.RANK, 1); //TODO: Сделать вычисление рейтинга игрока
        setElement(StatElement.PLAYER_ID, player.getUid());
        setElement(StatElement.ACCOUNT_ID, account.getUid());
        setElement(StatElement.SHOW_RANK, 1); //TODO: Система банов

        setElement(StatElement.STARS, player.getStars());
        setElement(StatElement.DEMONS, player.getDemons());
        setElement(StatElement.COINS, player.getCoins());
        setElement(StatElement.USER_COINS, player.getUserCoins());

        setElement(StatElement.FIRST_COLOR, skin.getFirstColor());
        setElement(StatElement.SECOND_COLOR, skin.getSecondColor());
        setElement(StatElement.ACC_ICON, skin.getAccIcon());
        setElement(StatElement.ACC_BALL, skin.getAccBall());
        setElement(StatElement.ACC_BIRD, skin.getAccBird());
        setElement(StatElement.ACC_DART, skin.getAccDart());
        setElement(StatElement.ACC_GLOW, skin.getAccGlow());
        setElement(StatElement.ACC_EXPLOSION, skin.getAccExplosion());
        setElement(StatElement.ACC_ROBOT, skin.getAccRobot());
        setElement(StatElement.ACC_SHIP, skin.getAccShip());
        setElement(StatElement.ACC_SPIDER, skin.getAccSpider());

        setElement(StatElement.YOUTUBE_URL, account.getYoutube());
        setElement(StatElement.TWITCH_URL, account.getTwitch());
        setElement(StatElement.TWITTER_URL, account.getTwitter());

        setElement(StatElement.FRIEND_STATE, 0); //TODO: Система модуля друзей

        setElement(StatElement.ALLOW_FRIEND_REQ_SETTING, settings.getFriend());
        setElement(StatElement.ALLOW_MESSAGES_SETTING, settings.getMessages());
        setElement(StatElement.VISIBLE_COMMENTS_HISTORY_SETTING, settings.getComments());

        setElement(StatElement.BADGE, 2); //TODO: Система статусов
    }

    @Override
    public String toString() {
        String[] buffer = new String[elements.size() * 2];

        int i = 0;
        for(StatElement element: elements.keySet()) {
            buffer[i * 2] = element.getCode();
            buffer[i * 2 + 1] = elements.get(element).toString();

            i++;
        }

        return String.join(":", buffer);
    }

    public enum StatElement {
        NAME("1"),
        PLAYER_ID("2"),
        STARS("3"),
        DEMONS("4"),
        CREATOR_POINTS("8"),
        FIRST_COLOR("10"),
        SECOND_COLOR("11"),
        COINS("13"),
        ACCOUNT_ID("16"),
        USER_COINS("17"),
        ALLOW_MESSAGES_SETTING("18"),
        ALLOW_FRIEND_REQ_SETTING("19"),
        YOUTUBE_URL("20"),
        ACC_ICON("21"),
        ACC_SHIP("22"),
        ACC_BALL("23"),
        ACC_BIRD("24"),
        ACC_DART("25"),
        ACC_ROBOT("26"),
        ACC_GLOW("18"),
        RANK("30"),
        FRIEND_STATE("31"),
        MESSAGES_COUNT("38"),  //player == target
        FRIENDS_REQ_COUNT("39"),  //player == target
        FRIENDS_COUNT("40"), //player == target
        ACC_SPIDER("43"),
        TWITTER_URL("44"),
        TWITCH_URL("45"),
        DIAMONDS("46"),
        SHOW_RANK("29"),
        ACC_EXPLOSION("47"),
        BADGE("49"),
        VISIBLE_COMMENTS_HISTORY_SETTING("50");

        @Getter
        private final String code;

        StatElement(String code) {
            this.code = code;
        }
    }
}
