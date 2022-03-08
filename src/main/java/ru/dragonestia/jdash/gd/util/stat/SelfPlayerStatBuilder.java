package ru.dragonestia.jdash.gd.util.stat;

import ru.dragonestia.jdash.gd.account.model.Account;
import ru.dragonestia.jdash.gd.account.model.AccountSettings;
import ru.dragonestia.jdash.gd.player.model.Player;
import ru.dragonestia.jdash.gd.player.model.Skin;

public class SelfPlayerStatBuilder extends AbstractPlayerStatBuilder {

    public SelfPlayerStatBuilder(Account account, Player player, Skin skin, AccountSettings settings) {
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
