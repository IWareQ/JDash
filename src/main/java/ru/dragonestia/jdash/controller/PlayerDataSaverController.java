package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.managers.AccountManager;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.account.AccountSettings;
import ru.dragonestia.jdash.managers.PlayerManager;
import ru.dragonestia.jdash.model.player.Player;
import ru.dragonestia.jdash.model.player.PlayerSkin;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class PlayerDataSaverController {

    private final PlayerManager playerManager;
    private final AccountManager accountManager;

    @Autowired
    public PlayerDataSaverController(PlayerManager playerManager, AccountManager accountManager){
        this.playerManager = playerManager;
        this.accountManager = accountManager;
    }

    @PostMapping("/getAccountURL.php")
    String accountUrl(@RequestParam(name = "accountID") int accountId,
                      @RequestParam(name = "type") int requestType,
                      @RequestParam(name = "secret") String secret,
                      HttpServletRequest request) {

        return request.getRequestURL().substring(0, 33);
    }

    @PostMapping("/database/accounts/backupGJAccountNew.php")
    String saveData(@RequestParam(name = "userName") String login,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "saveData") String data,
                    @RequestParam(name = "secret") String secret) {

        Account account;
        try {
            account = accountManager.login(login, password);
        } catch (AccountException ex){
            return "-1";
        }

        Player player = playerManager.getPlayer(account);
        playerManager.saveData(player, data);
        return "1";
    }

    @PostMapping("/database/accounts/syncGJAccountNew.php")
    String loadData(@RequestParam(name = "userName") String login,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "secret") String secret) {

        Account account;
        try {
            account = accountManager.login(login, password);
        } catch (AccountException ex){
            return "-1";
        }

        Player player = playerManager.getPlayer(account);
        String data = playerManager.loadData(player);

        if(data == null) return "-1";
        return data + ";21;30;a;a";
    }

    @PostMapping("/updateGJUserScore22.php")
    String update(@RequestParam(name = "userName") String login,
                  @RequestParam(name = "stars") int stars,
                  @RequestParam(name = "demons") int demons,
                  @RequestParam(name = "diamonds") int diamonds,
                  @RequestParam(name = "coins") int coins,
                  @RequestParam(name = "userCoins") int userCoins,
                  @RequestParam(name = "secret") String secret,
                  @RequestParam(name = "icon") int icon,
                  @RequestParam(name = "color1") int firstColor,
                  @RequestParam(name = "color2") int secondColor,
                  @RequestParam(name = "iconType") int iconType,
                  @RequestParam(name = "special") int special,
                  @RequestParam(name = "accIcon") int accIcon,
                  @RequestParam(name = "accShip") int accShip,
                  @RequestParam(name = "accBall") int accBall,
                  @RequestParam(name = "accBird") int accBird,
                  @RequestParam(name = "accDart") int accDart,
                  @RequestParam(name = "accRobot") int accRobot,
                  @RequestParam(name = "accGlow") int accGlow,
                  @RequestParam(name = "accSpider") int accSpider,
                  @RequestParam(name = "accExplosion") int accExplosion,
                  @RequestAttribute Account account) {

        if (account == null) return "-1";

        Player player = playerManager.getPlayer(account);
        player.setCoins(coins);
        player.setDemons(demons);
        player.setDiamonds(diamonds);
        player.setUserCoins(userCoins);
        player.setStars(stars);
        playerManager.updatePlayer(player);

        PlayerSkin skin = playerManager.getSkin(player);
        skin.setIcon(icon);
        skin.setFirstColor(firstColor);
        skin.setSecondColor(secondColor);
        skin.setIconType(iconType);
        skin.setSpecial(special);
        skin.setAccBall(accBall);
        skin.setAccBird(accBird);
        skin.setAccDart(accDart);
        skin.setAccExplosion(accExplosion);
        skin.setAccGlow(accGlow);
        skin.setAccIcon(accIcon);
        skin.setAccShip(accShip);
        skin.setAccRobot(accRobot);
        skin.setAccSpider(accSpider);
        playerManager.updateSkin(skin);

        return "1";
    }

    @PostMapping("/updateGJAccSettings20.php")
    String updateSettings(@RequestParam(name = "mS") int messages,
                          @RequestParam(name = "frS") int friend,
                          @RequestParam(name = "cS") int comments,
                          @RequestParam(name = "yt") String youtube,
                          @RequestParam(name = "twitter") String twitter,
                          @RequestParam(name = "twitch") String twitch,
                          @RequestParam(name = "secret") String secret,
                          @RequestAttribute Account account) {

        if (account == null) return "-1";

        AccountSettings settings = accountManager.getSettings(account);
        settings.setMessages(messages);
        settings.setComments(comments);
        settings.setFriend(friend);
        accountManager.updateSettings(account, settings);

        return "1";
    }
}
