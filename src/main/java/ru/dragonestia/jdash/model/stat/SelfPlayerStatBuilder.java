package ru.dragonestia.jdash.model.stat;

import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.account.AccountSettings;
import ru.dragonestia.jdash.model.player.Player;
import ru.dragonestia.jdash.model.player.PlayerSkin;

public class SelfPlayerStatBuilder extends AbstractPlayerStatBuilder {

    public SelfPlayerStatBuilder(Account account, Player player, PlayerSkin skin, AccountSettings settings) {
        super(account, player, skin, settings);
    }

    @Override
    protected void init() {
        super.init();

        setElement(StatElement.MESSAGES_COUNT, 0); //TODO: Модуль сообщений
        setElement(StatElement.FRIENDS_REQ_COUNT, 0); //TODO: Модуль друзей
        setElement(StatElement.FRIENDS_COUNT, 0); //TODO: Модуль друзей
    }
}
