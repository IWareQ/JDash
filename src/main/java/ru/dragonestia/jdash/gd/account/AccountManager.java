package ru.dragonestia.jdash.gd.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.dragonestia.jdash.gd.account.model.Account;
import ru.dragonestia.jdash.gd.account.model.AccountSettings;
import ru.dragonestia.jdash.gd.player.PlayerManager;
import ru.dragonestia.jdash.gd.util.GeometryJumpSecure;

@Component
public class AccountManager {

    private Sql2o sql2o;
    private PlayerManager playerManager;

    @Autowired
    private void init(Sql2o sql2o, PlayerManager playerManager){
        this.sql2o = sql2o;
        this.playerManager = playerManager;
    }

    public boolean isRegistered(String login) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT COUNT(*) FROM accounts WHERE login = :login LIMIT 1;")
                    .addParameter("login", login)
                    .executeScalar(Integer.class) != 0;
        }
    }

    public Account registerAccount(String login, String password, String email) throws AccountException {
        Account account;
        try (Connection conn = sql2o.beginTransaction()) {
            try {
                conn.createQuery("INSERT INTO accounts (login, password, email) VALUES (:login, SHA2(:password, 256), :email);")
                        .addParameter("login", login)
                        .addParameter("password", password)
                        .addParameter("email", email)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                throw new AccountException("This login already used");
            }

            account = conn.createQuery("SELECT * FROM accounts WHERE login = :login LIMIT 1;")
                    .addParameter("login", login)
                    .executeAndFetchFirst(Account.class);

            conn.createQuery("INSERT INTO settings (account) VALUES ("+ account.getUid() +");")
                    .executeUpdate();

            conn.commit();

        }
        playerManager.createPlayer(account);
        return account;
    }

    public Account getAccount(int id) throws AccountException {
        try (Connection conn = sql2o.open()) {
            Account account = conn.createQuery("SELECT * FROM accounts WHERE uid = "+ id +" LIMIT 1;")
                    .executeAndFetchFirst(Account.class);

            if(account == null) throw new AccountException("Invalid account id");
            return account;
        }
    }

    public Account getAccount(String login) throws AccountException {
        try (Connection conn = sql2o.open()) {
            Account account = conn.createQuery("SELECT * FROM accounts WHERE login = :login LIMIT 1;")
                    .addParameter("login", login)
                    .executeAndFetchFirst(Account.class);

            if(account == null) throw new AccountException("Invalid account id");
            return account;
        }
    }

    public Account login(String login, String password) throws AccountException {
        Account account;
        try (Connection conn = sql2o.open()) {
            account = conn.createQuery(
                    "SELECT * FROM accounts " +
                            "WHERE login = :login AND password = SHA2(:password, 256) " +
                            "LIMIT 1;"
            ).addParameter("login", login)
                    .addParameter("password", password)
                    .executeAndFetchFirst(Account.class);
        }
        if(account == null) throw new AccountException("Account not found");
        return account;
    }

    public Account login(int accountId, String gjp) {
        Account account;
        try (Connection conn = sql2o.open()) {
            account = conn.createQuery(
                    "SELECT * FROM accounts WHERE " +
                            "uid = " + accountId + " AND password = SHA2(:password, 256) " +
                            "LIMIT 1;"
            ).addParameter("password", GeometryJumpSecure.gjpDecode(gjp))
                    .executeAndFetchFirst(Account.class);
        }
        if (account == null) throw new AccountException("Authorisation failed");

        return account;
    }

    public AccountSettings getSettings(Account account) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM settings WHERE account = "+ account.getUid() + " LIMIT 1;")
                    .executeAndFetchFirst(AccountSettings.class);
        }
    }

    public void updateSettings(Account account, AccountSettings settings) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "UPDATE settings SET " +
                            "messages = "+ settings.getMessages() +", " +
                            "comments = "+ settings.getComments() +", " +
                            "friend = "+ settings.getFriend() +" " +
                            "WHERE account = "+ account.getUid() +";"
                    ).executeUpdate()
                    .commit();
        }
    }
}
