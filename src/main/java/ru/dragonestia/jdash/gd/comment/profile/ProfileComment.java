package ru.dragonestia.jdash.gd.comment.profile;

import lombok.Getter;

import java.sql.Date;

@Getter
public class ProfileComment {

    private int uid;
    private int owner;
    private String text;
    private Date time;
    private boolean spam;
    private int likes;
}
