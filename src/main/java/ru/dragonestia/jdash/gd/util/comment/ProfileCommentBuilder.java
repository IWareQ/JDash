package ru.dragonestia.jdash.gd.util.comment;

import lombok.Getter;

import java.util.HashMap;

public class ProfileCommentBuilder {

    public static final String COMMENT_SEPARATOR = "|";

    private final HashMap<Element, Object> elements = new HashMap<>();

    public ProfileCommentBuilder(){
        init();
    }

    public void setElement(Element element, Object value) {
        elements.put(element, value);
    }

    protected void init() {
        //TODO: Установка свойств элементов сообения
        setElement(Element.TEXT, "QkBQQEJK");
        setElement(Element.LIKES, 0);
        setElement(Element.UNKNOWN_ELEMENT, 2);
        setElement(Element.DATE, "Watatatattttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt t t t ttttterwr ewr wer");
        setElement(Element.IS_SPAM, 0);
        setElement(Element.COMMENT_ID, 2);
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
