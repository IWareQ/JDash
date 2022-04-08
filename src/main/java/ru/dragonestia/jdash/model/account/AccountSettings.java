package ru.dragonestia.jdash.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountSettings {

    @Getter
    public enum AllowMessagesFrom {
        ALL(0),
        FRIENDS(1),
        NONE(2);

        final int value;

        AllowMessagesFrom(int value) {
            this.value = value;
        }

        public static AllowMessagesFrom fromValue(int value) {
            switch (value) {
                case 0: return ALL;
                case 1: return FRIENDS;
                case 2: return NONE;
                default: throw new IllegalArgumentException(String.valueOf(value));
            }
        }
    }

    @Getter
    public enum AllowFriendRequestsFrom {
        ALL(0),
        NONE(1);

        final int value;

        AllowFriendRequestsFrom(int value) {
            this.value = value;
        }

        public static AllowFriendRequestsFrom fromValue(int value) {
            switch (value) {
                case 0: return ALL;
                case 1: return NONE;
                default: throw new IllegalArgumentException(String.valueOf(value));
            }
        }
    }

    @Getter
    public enum ShowCommentHistoryTo {
        ALL(0),
        FRIENDS(1),
        ME(2);

        final int value;

        ShowCommentHistoryTo(int value) {
            this.value = value;
        }

        public static ShowCommentHistoryTo fromValue(int value) {
            switch (value) {
                case 0: return ALL;
                case 1: return FRIENDS;
                case 2: return ME;
                default: throw new IllegalArgumentException(String.valueOf(value));
            }
        }
    }

    int uid, account;

    @Setter AllowMessagesFrom allowMessagesFrom;
    @Setter AllowFriendRequestsFrom allowFriendRequestsFrom;
    @Setter ShowCommentHistoryTo showCommentHistoryTo;
    @Setter String youtube, twitter, twitch;
}
