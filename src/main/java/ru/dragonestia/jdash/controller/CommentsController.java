package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.gd.account.AccountManager;
import ru.dragonestia.jdash.gd.account.model.Account;
import ru.dragonestia.jdash.gd.comment.profile.ProfileComment;
import ru.dragonestia.jdash.gd.player.PlayerManager;
import ru.dragonestia.jdash.gd.comment.profile.ProfileCommentBuilder;

import java.util.List;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class CommentsController {

    private final AccountManager accountManager;

    @Autowired
    public CommentsController(PlayerManager playerManager, AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @PostMapping("/getGJAccountComments20.php")
    String profileComments(@RequestParam(name = "accountID") int accountId,
                           @RequestParam(name = "page") int page,
                           @RequestParam(name = "total") int total,
                           @RequestParam(name = "secret") String secret) {

        Account target = accountManager.getAccount(accountId);
        List<ProfileComment> comments = accountManager.getComments(target, page);

        if(page == 0 && comments.size() == 0) return "#0:0:10";
        if(comments.size() == 0) comments = accountManager.getComments(target, page--);

        String[] buffer = new String[comments.size()];
        int i = 0;
        for(ProfileComment comment: comments) {
            buffer[i++] = comment.getOutputBuilder().toString();
        }
        int totalCommentCount = accountManager.countComments(target);

        return String.join(ProfileCommentBuilder.COMMENT_SEPARATOR, buffer) +"#"+ totalCommentCount +":"+ page +":10";
    }

    @PostMapping("/uploadGJAccComment20.php")
    String publishProfileComment(@RequestParam(name = "comment") String encodedText,
                                 @RequestAttribute Account account) {

        if (account == null || encodedText.length() == 0) return "-1";

        accountManager.publishComment(account, encodedText);
        return "1";
    }

    @PostMapping("/deleteGJAccComment20.php")
    String deleteProfileComment(@RequestParam(name = "commentID") int commentId,
                                @RequestParam(name = "cType") int wtfParam,
                                @RequestAttribute Account account) {

        if(account == null) return "-1";

        ProfileComment comment = accountManager.getComment(commentId);
        if(comment == null || comment.getOwner() != account.getUid()) return "-1";

        accountManager.deleteComment(comment);
        return "1";
    }
}
