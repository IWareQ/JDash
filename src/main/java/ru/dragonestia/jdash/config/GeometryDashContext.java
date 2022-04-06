package ru.dragonestia.jdash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dragonestia.jdash.model.account.AccountManager;
import ru.dragonestia.jdash.model.player.PlayerManager;

@Configuration
public class GeometryDashContext {

    @Bean
    public AccountManager getAccountManager() {
        return new AccountManager();
    }

    @Bean
    public PlayerManager getPlayerManager() {
        return new PlayerManager();
    }
}
