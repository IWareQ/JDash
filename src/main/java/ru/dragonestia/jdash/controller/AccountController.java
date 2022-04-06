package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.managers.AccountManager;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.managers.PlayerManager;
import ru.dragonestia.jdash.model.player.Player;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS + "/accounts")
public class AccountController {

    private final AccountManager accountManager;
    private final PlayerManager playerManager;

    @Autowired
    public AccountController(AccountManager accountManager, PlayerManager playerManager){
        this.accountManager = accountManager;
        this.playerManager= playerManager;
    }

    @PostMapping("/registerGJAccount.php")
    String register(@RequestParam(name = "userName") String login,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "email") String email,
                    @RequestParam(name = "secret") String secret) {

        if(login.length() > 20) return "-4";

        Account account;
        try {
            account = accountManager.registerAccount(login, password, email);
        } catch (AccountException ex) {
            return "-2";
        }

        return "1";
    }

    @PostMapping("/loginGJAccount.php")
    String login(@RequestParam(name = "userName") String login,
                 @RequestParam(name = "password") String password,
                 @RequestParam(name = "udid") String udid,
                 @RequestParam(name = "sID") String sid,
                 @RequestParam(name = "secret") String secret) {

        Account account;
        try {
            account = accountManager.login(login, password);
        } catch (AccountException ex) {
            return "-1";
        }
        Player player = playerManager.getPlayer(account);

        return account.getUid() +","+ player.getUid();
    }
}
