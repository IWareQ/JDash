package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.model.account.AccountException;
import ru.dragonestia.jdash.model.account.AccountManager;
import ru.dragonestia.jdash.model.account.model.Account;
import ru.dragonestia.jdash.model.account.model.AccountSettings;
import ru.dragonestia.jdash.model.player.PlayerManager;
import ru.dragonestia.jdash.model.player.model.Player;
import ru.dragonestia.jdash.model.player.model.Skin;
import ru.dragonestia.jdash.model.util.stat.AbstractPlayerStatBuilder;
import ru.dragonestia.jdash.model.util.stat.OtherPlayerStatBuilder;
import ru.dragonestia.jdash.model.util.stat.SelfPlayerStatBuilder;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class StatController {

    private final PlayerManager playerManager;
    private final AccountManager accountManager;

    @Autowired
    public StatController(PlayerManager playerManager, AccountManager accountManager) {
        this.accountManager = accountManager;
        this.playerManager = playerManager;
    }

    @PostMapping("/getGJUserInfo20.php")
    String profile(@RequestParam(name = "targetAccountID") int targetAccountId,
                   @RequestParam(name = "secret") String secret,
                   @RequestAttribute Account account) {

        if (account == null) return "-1";

        Account target;
        Player targetPlayer;
        Skin targetSkin;
        AccountSettings targetSettings;

        if(targetAccountId == account.getUid()) {
            target = account;
        } else {
            try {
                target = accountManager.getAccount(targetAccountId);
            } catch (AccountException ex) {
                return "-1";
            }
        }
        targetPlayer = playerManager.getPlayer(target);
        targetSkin = playerManager.getSkin(targetPlayer);
        targetSettings = accountManager.getSettings(account);

        AbstractPlayerStatBuilder playerStatBuilder;
        if (targetAccountId == account.getUid()) playerStatBuilder = new SelfPlayerStatBuilder(target, targetPlayer, targetSkin, targetSettings);
        else playerStatBuilder = new OtherPlayerStatBuilder(target, targetPlayer, targetSkin, targetSettings);

        playerStatBuilder.setElement(AbstractPlayerStatBuilder.StatElement.RANK, playerManager.getGlobalRank(targetPlayer));

        return playerStatBuilder.toString();
    }
}
