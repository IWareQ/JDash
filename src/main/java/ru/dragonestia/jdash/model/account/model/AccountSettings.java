package ru.dragonestia.jdash.model.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountSettings {

    private int uid;
    private int account;
    @Setter private int messages = AllowMessagesValues.ALL.value;
    @Setter private int comments = ShowCommentsHistory.ALL.value;
    @Setter private int friend = AllowFriendRequests.ALL.value;

    public enum AllowMessagesValues {
        ALL(0),
        FRIENDS(1),
        NONE(2);

        @Getter
        private final int value;

        AllowMessagesValues(int value){
            this.value = value;
        }
    }

    public enum ShowCommentsHistory {
        ALL(0),
        FRIENDS(1),
        ME(2);

        @Getter
        private final int value;

        ShowCommentsHistory(int value){
            this.value = value;
        }
    }

    public enum AllowFriendRequests {
        ALL(0),
        NONE(1);

        @Getter
        private final int value;

        AllowFriendRequests(int value){
            this.value = value;
        }
    }
}
