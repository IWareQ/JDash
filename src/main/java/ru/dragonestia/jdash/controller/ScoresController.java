package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.model.account.model.Account;
import ru.dragonestia.jdash.model.player.PlayerManager;
import ru.dragonestia.jdash.model.player.model.Player;
import ru.dragonestia.jdash.model.util.score.ScoreStatBuilder;
import ru.dragonestia.jdash.model.util.score.ScoreType;

import java.util.ArrayList;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class ScoresController {

    private final PlayerManager playerManager;

    @Autowired
    public ScoresController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @PostMapping("/getGJScores20.php")
    String getScore(@RequestParam(name = "type") ScoreType type,
                    @RequestParam(name = "count") int count,
                    @RequestParam(name = "secret") String secret,
                    @RequestAttribute Account account) {

        if(account == null || type == null) return "-1";
        Player player = playerManager.getPlayer(account);

        ArrayList<ScoreStatBuilder> players;

        switch (type) {
            case TOP:
                players = playerManager.getTopByStars();
                break;

            case CREATORS:
                players = playerManager.getTopByCreatorPoints();
                break;

            case RELATIVE:
                players = playerManager.getRelativeTopByPlayer(player);
                break;

            default:
                players = new ArrayList<>();
        }

        String[] result = new String[players.size()];
        int i = 0;
        for (ScoreStatBuilder builder: players) {
            result[i++] = builder.toString();
        }

        return String.join(ScoreStatBuilder.SCORE_SEPARATOR, result);
    }
}
