package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.gd.account.AccountManager;
import ru.dragonestia.jdash.gd.player.PlayerManager;
import ru.dragonestia.jdash.gd.util.comment.ProfileCommentBuilder;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class CommentsController {

    private final PlayerManager playerManager;
    private final AccountManager accountManager;

    @Autowired
    public CommentsController(PlayerManager playerManager, AccountManager accountManager) {
        this.accountManager = accountManager;
        this.playerManager = playerManager;
    }

    @PostMapping("/getGJAccountComments20.php")
    String profileComments(@RequestParam(name = "accountID") int accountId,
                           @RequestParam(name = "page") int page,
                           @RequestParam(name = "total") int total,
                           @RequestParam(name = "secret") String secret) {

        int totalCommentCount = 1;
        int commentPage = 0;

        return new ProfileCommentBuilder() +"#"+ totalCommentCount +":"+ commentPage +":10";
    }

    @PostMapping("/uploadGJAccComment20.php")
    String publishProfileComment() {
        return "-1";
    }
}
