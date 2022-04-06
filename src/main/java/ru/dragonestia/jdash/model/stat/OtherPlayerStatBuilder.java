package ru.dragonestia.jdash.model.stat;

import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.account.AccountSettings;
import ru.dragonestia.jdash.model.player.Player;
import ru.dragonestia.jdash.model.player.PlayerSkin;

public class OtherPlayerStatBuilder extends AbstractPlayerStatBuilder {

    public OtherPlayerStatBuilder(Account account, Player player, PlayerSkin skin, AccountSettings settings) {
        super(account, player, skin, settings);
    }
}
