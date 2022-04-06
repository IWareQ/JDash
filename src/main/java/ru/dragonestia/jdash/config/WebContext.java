package ru.dragonestia.jdash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.converter.ScoreTypeResolveConverter;
import ru.dragonestia.jdash.managers.AccountManager;
import ru.dragonestia.jdash.interceptor.AccountAuthorisationInterceptor;
import ru.dragonestia.jdash.interceptor.DebugInterceptor;

@Configuration
public class WebContext implements WebMvcConfigurer {

    private final AccountManager accountManager;

    @Autowired
    public WebContext(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccountAuthorisationInterceptor(accountManager));
        if(JDashApplication.DEBUG) registry.addInterceptor(new DebugInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ScoreTypeResolveConverter());
    }
}
