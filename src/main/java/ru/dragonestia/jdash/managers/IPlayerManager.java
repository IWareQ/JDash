package ru.dragonestia.jdash.managers;

import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.player.FullPlayerData;
import ru.dragonestia.jdash.model.player.IPlayer;
import ru.dragonestia.jdash.model.player.Player;
import ru.dragonestia.jdash.model.player.PlayerSkin;
import ru.dragonestia.jdash.model.score.ScoreStatBuilder;

import java.util.List;

public interface IPlayerManager {

    Player getPlayer(Account account) throws AccountException;

    Player createPlayer(Account account);

    void updatePlayer(Player player);

    PlayerSkin getSkin(Player player);

    void updateSkin(PlayerSkin skin);

    void saveData(Player player, String data);

    String loadData(Player player);

    FullPlayerData getFullPlayerData(int playerId) throws IllegalArgumentException;

    List<ScoreStatBuilder> getTopByStars();

    List<ScoreStatBuilder> getTopByCreatorPoints();

    int getGlobalRank(IPlayer player);

    List<ScoreStatBuilder> getRelativeTopByPlayer(IPlayer player);


}
