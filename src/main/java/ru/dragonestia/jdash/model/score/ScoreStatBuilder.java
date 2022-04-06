package ru.dragonestia.jdash.model.score;

import lombok.Getter;
import ru.dragonestia.jdash.model.player.FullPlayerData;

import java.util.EnumMap;
import java.util.Map;

public class ScoreStatBuilder {

    public static final String SCORE_SEPARATOR = "|";

    private final int position;
    @Getter
    private final FullPlayerData player;
    private final Map<ScoreStatElement, Object> elements = new EnumMap<>(ScoreStatElement.class);

    public ScoreStatBuilder(int position, FullPlayerData player) {
        this.position = position;
        this.player = player;

        init();
    }

    public void setElement(ScoreStatElement element, Object value) {
        elements.put(element, value);
    }

    protected void init() {
        setElement(ScoreStatElement.NAME, player.getName());
        setElement(ScoreStatElement.PLAYER_ID, player.getId());
        setElement(ScoreStatElement.STARS, player.getStars());
        setElement(ScoreStatElement.DEMONS, player.getDemons());
        setElement(ScoreStatElement.TOP_POSITION, position);
        setElement(ScoreStatElement.ACCOUNT_ID1, player.getAccountId());
        setElement(ScoreStatElement.CREATOR_POINTS, player.getCreatorPoints());
        setElement(ScoreStatElement.SKIN_ICON, player.getSkinIcon());
        setElement(ScoreStatElement.SKIN_FIRST_COLOR, player.getSkinFirstColor());
        setElement(ScoreStatElement.SKIN_SECOND_COLOR, player.getSkinSecondColor());
        setElement(ScoreStatElement.COINS, player.getCoins());
        setElement(ScoreStatElement.SKIN_ICON_TYPE, player.getSkinIconType());
        setElement(ScoreStatElement.SKIN_SPECIAL, player.getSkinSpecial());
        setElement(ScoreStatElement.ACCOUNT_ID2, player.getAccountId());
        setElement(ScoreStatElement.USER_COINS, player.getUserCoins());
        setElement(ScoreStatElement.DIAMONDS, player.getDiamonds());
    }

    @Override
    public String toString() {
        String[] buffer = new String[elements.size() * 2];

        int i = 0;
        for (ScoreStatElement element : elements.keySet()) {
            buffer[i * 2] = element.getCode();
            buffer[i * 2 + 1] = elements.get(element).toString();

            i++;
        }

        return String.join(":", buffer);
    }

    public enum ScoreStatElement {
        NAME("1"),
        PLAYER_ID("2"),
        STARS("3"),
        DEMONS("4"),
        TOP_POSITION("6"),
        ACCOUNT_ID1("7"),
        CREATOR_POINTS("8"),
        SKIN_ICON("9"),
        SKIN_FIRST_COLOR("10"),
        SKIN_SECOND_COLOR("11"),
        COINS("13"),
        SKIN_ICON_TYPE("14"),
        SKIN_SPECIAL("15"),
        ACCOUNT_ID2("16"),
        USER_COINS("17"),
        DIAMONDS("46");

        @Getter
        private final String code;

        ScoreStatElement(String code) {
            this.code = code;
        }
    }
}
