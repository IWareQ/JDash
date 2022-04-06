package ru.dragonestia.jdash.managers;

import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.account.AccountSettings;
import ru.dragonestia.jdash.model.profilecomment.ProfileComment;

import java.util.List;

public interface IAccountManager {

    // todo: @Deprecated
    boolean isRegistered(String login);

    // todo: Этот метод не должен ничего возвращать. Также он должен выбрасывать соответствующие исключения вроде AccountRegistrationException
    Account registerAccount(String login, String password, String email) throws AccountException;
    
    // todo: Этот метод должен выбрасывать NoSuchAccountException вместо AccountException
    Account getAccount(int id) throws AccountException;

    // todo: Этот метод должен выбрасывать NoSuchAccountException вместо AccountException
    Account getAccount(String login) throws AccountException;

    // todo: Этот метод должен выбрасывать NoSuchAccountException, AccountAuthenticationException вместо AccountException
    Account login(String login, String password) throws AccountException;

    Account login(int accountId, String gjp);

    AccountSettings getSettings(Account account);

    void updateSettings(Account account, AccountSettings settings);

    void publishComment(Account account, String encodedText);

    List<ProfileComment> getComments(Account account, int page);

    int countComments(Account account);

    ProfileComment getComment(int commentId);

    void deleteComment(ProfileComment comment);
}
