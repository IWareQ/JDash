package ru.dragonestia.jdash.managers;

import ru.dragonestia.jdash.exceptions.AccountAuthenticationException;
import ru.dragonestia.jdash.exceptions.AccountException;
import ru.dragonestia.jdash.exceptions.AccountRegistrationException;
import ru.dragonestia.jdash.exceptions.NoSuchAccountException;
import ru.dragonestia.jdash.model.account.Account;
import ru.dragonestia.jdash.model.account.AccountSettings;
import ru.dragonestia.jdash.model.profilecomment.ProfileComment;

import java.util.List;

public interface IAccountManager {

    void registerAccount(String login, String password, String email) throws AccountRegistrationException;

    Account getAccount(int id) throws NoSuchAccountException;

    Account getAccount(String login) throws NoSuchAccountException;

    Account login(String login, String password) throws NoSuchAccountException, AccountAuthenticationException;

    Account login(int accountId, String gjp);

    AccountSettings getSettings(Account account);

    void updateSettings(Account account, AccountSettings settings);

    void publishComment(Account account, String encodedText);

    List<ProfileComment> getComments(Account account, int page);

    int countComments(Account account);

    ProfileComment getComment(int commentId);

    void deleteComment(ProfileComment comment);
}
