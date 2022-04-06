package ru.dragonestia.jdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.managers.IPlayerManager;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.player.Player;
import ru.dragonestia.jdash.model.score.ScoreStatBuilder;
import ru.dragonestia.jdash.model.score.ScoreType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(JDashApplication.DATABASE_ADDRESS)
public class ScoresController {

    private final IPlayerManager playerManager;

    @Autowired
    public ScoresController(IPlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @PostMapping("/getGJScores20.php")
    String getScore(@RequestParam(name = "type") ScoreType type,
                    @RequestParam(name = "count") int count,
                    @RequestParam(name = "secret") String secret,
                    @RequestAttribute Account account) {

        if(account == null || type == null) return "-1";
        Player player = playerManager.getPlayer(account);

        List<ScoreStatBuilder> players;

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
