package ru.dragonestia.jdash.gd.util.stat;

import ru.dragonestia.jdash.gd.account.model.Account;
import ru.dragonestia.jdash.gd.account.model.AccountSettings;
import ru.dragonestia.jdash.gd.player.model.Player;
import ru.dragonestia.jdash.gd.player.model.Skin;

public class OtherPlayerStatBuilder extends AbstractPlayerStatBuilder {

    public OtherPlayerStatBuilder(Account account, Player player, Skin skin, AccountSettings settings) {
        super(account, player, skin, settings);
    }
}
