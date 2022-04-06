package ru.dragonestia.jdash.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.dragonestia.jdash.JDashApplication;
import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.managers.AccountManager;
import ru.dragonestia.jdash.model.account.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccountAuthorisationInterceptor implements HandlerInterceptor {

    private final AccountManager accountManager;

    public AccountAuthorisationInterceptor(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod) || !(request.getRequestURI().startsWith(JDashApplication.DATABASE_ADDRESS))) return true;
        HandlerMethod method = (HandlerMethod) handler;

        Account account = handle(request);

        request.setAttribute("account", account);
        return true;
    }

    private Account handle(HttpServletRequest request) {
        String accountId = request.getParameter("accountID");
        String gjp = request.getParameter("gjp");

        if (accountId == null || gjp == null) return null;
        try {
            return accountManager.login(Integer.parseInt(accountId), gjp);
        } catch (AccountException | NumberFormatException ex) {
            return null;
        }
    }
}
