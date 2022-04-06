package ru.dragonestia.jdash.model.comment.profile;

import lombok.Getter;

import java.util.HashMap;

public class ProfileCommentBuilder {

    public static final String COMMENT_SEPARATOR = "|";

    @Getter private final ProfileComment comment;
    private final HashMap<Element, Object> elements = new HashMap<>();

    public ProfileCommentBuilder(ProfileComment comment){
        this.comment = comment;

        init();
    }

    public void setElement(Element element, Object value) {
        elements.put(element, value);
    }

    protected void init() {
        setElement(Element.PLAYER_ID, comment.getOwner());
        setElement(Element.TEXT, comment.getText());
        setElement(Element.LIKES, comment.getLikes());
        setElement(Element.UNKNOWN_ELEMENT, 0);
        setElement(Element.DATE, "Andrew, do it later"); //TODO: Сделать отображение даты как на оф.сервере
        setElement(Element.IS_SPAM, comment.isSpam()? 1 : 0);
        setElement(Element.COMMENT_ID, comment.getUid());
    }

    @Override
    public String toString() {
        String[] buffer = new String[elements.size() * 2];

        int i = 0;
        for (Element element: elements.keySet()) {
            buffer[i * 2] = element.getCode();
            buffer[i * 2 + 1] = elements.get(element).toString();

            i++;
        }

        return String.join("~", buffer);
    }

    @Getter
    public enum Element {
        TEXT("2"),
        PLAYER_ID("3"),
        LIKES("4"),
        UNKNOWN_ELEMENT("5"), //0
        COMMENT_ID("6"),
        IS_SPAM("7"),
        DATE("9");

        private final String code;

        Element(String code) {
            this.code = code;
        }
    }
}
