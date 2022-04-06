package ru.dragonestia.jdash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dragonestia.jdash.managers.AccountManager;
import ru.dragonestia.jdash.managers.IAccountManager;
import ru.dragonestia.jdash.managers.IPlayerManager;
import ru.dragonestia.jdash.managers.PlayerManager;

@Configuration
public class GeometryDashContext {

    @Bean
    public IAccountManager getAccountManager() {
        return new AccountManager();
    }

    @Bean
    public IPlayerManager getPlayerManager() {
        return new PlayerManager();
    }
}
