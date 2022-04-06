package ru.dragonestia.jdash.model.util.stat;

import ru.dragonestia.jdash.model.account.model.Account;
import ru.dragonestia.jdash.model.account.model.AccountSettings;
import ru.dragonestia.jdash.model.player.model.Player;
import ru.dragonestia.jdash.model.player.model.Skin;

public class OtherPlayerStatBuilder extends AbstractPlayerStatBuilder {

    public OtherPlayerStatBuilder(Account account, Player player, Skin skin, AccountSettings settings) {
        super(account, player, skin, settings);
    }
}
