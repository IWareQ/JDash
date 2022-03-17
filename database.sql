CREATE TABLE IF NOT EXISTS accounts (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    login VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    twitter VARCHAR(255) NOT NULL DEFAULT '',
    twitch VARCHAR(255) NOT NULL DEFAULT '',
    youtube VARCHAR(255) NOT NULL DEFAULT '',
    register DATETIME NOT NULL DEFAULT NOW(),
    activated BOOLEAN NOT NULL DEFAULT TRUE,
    creatorPoints INTEGER NOT NULL DEFAULT 0,

    PRIMARY KEY(uid)
);

CREATE TABLE IF NOT EXISTS settings (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    account INTEGER NOT NULL UNIQUE,
    messages INTEGER NOT NULL DEFAULT 0,
    comments INTEGER NOT NULL DEFAULT 0,
    friend INTEGER NOT NULL DEFAULT 0,

    PRIMARY KEY(uid),
    FOREIGN KEY(account) REFERENCES accounts (uid)
                                    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS players (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    accountId INTEGER NOT NULL UNIQUE,
    stars INTEGER NOT NULL DEFAULT 0,
    demons INTEGER NOT NULL DEFAULT 0,
    coins INTEGER NOT NULL DEFAULT 0,
    userCoins INTEGER NOT NULL DEFAULT 0,
    diamonds INTEGER NOT NULL DEFAULT 0,
    orbs INTEGER NOT NULL DEFAULT 0,

    PRIMARY KEY(uid),
    FOREIGN KEY(accountId) REFERENCES accounts (uid)
                                   ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS skins (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    player INTEGER NOT NULL UNIQUE,
    icon INTEGER NOT NULL DEFAULT 0,
    firstColor INTEGER NOT NULL DEFAULT 0,
    secondColor INTEGER NOT NULL DEFAULT 3,
    iconType INTEGER NOT NULL DEFAULT 0,
    special INTEGER NOT NULL DEFAULT 0,
    accIcon INTEGER NOT NULL DEFAULT 0,
    accShip INTEGER NOT NULL DEFAULT 0,
    accBall INTEGER NOT NULL DEFAULT 0,
    accBird INTEGER NOT NULL DEFAULT 0,
    accDart INTEGER NOT NULL DEFAULT 0,
    accRobot INTEGER NOT NULL DEFAULT 0,
    accGlow INTEGER NOT NULL DEFAULT 0,
    accSpider INTEGER NOT NULL DEFAULT 0,
    accExplosion INTEGER NOT NULL DEFAULT 0,

    PRIMARY KEY(uid),
    FOREIGN KEY(player) REFERENCES players (uid)
                                 ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS profile_comments (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    owner INTEGER NOT NULL,
    text TEXT NOT NULL,
    time DATETIME NOT NULL DEFAULT NOW(),
    spam BOOLEAN NOT NULL DEFAULT FALSE,
    likes INTEGER NOT NULL DEFAULT 0,

    PRIMARY KEY(uid),
    FOREIGN KEY(owner) REFERENCES accounts (uid)
                                            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friend_requests (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    sender INTEGER NOT NULL,
    receiver INTEGER NOT NULL,

    PRIMARY KEY(uid),
    FOREIGN KEY(sender) REFERENCES accounts (uid)
       ON DELETE CASCADE,
    FOREIGN KEY(receiver) REFERENCES accounts (uid)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friends (
    uid INTEGER NOT NULL AUTO_INCREMENT,
    account1 INTEGER NOT NULL,
    account2 INTEGER NOT NULL,

    PRIMARY KEY(uid),
    FOREIGN KEY(account1) REFERENCES accounts (uid)
       ON DELETE CASCADE,
    FOREIGN KEY(account2) REFERENCES accounts (uid)
       ON DELETE CASCADE
);


DELIMITER $

CREATE FUNCTION IF NOT EXISTS JDash_isFriend(account1 INTEGER, account2 INTEGER)
    RETURNS BOOLEAN
    READS SQL DATA
    NOT DETERMINISTIC
BEGIN
    RETURN (
        SELECT COUNT(*) != 0
        FROM friends as f
        WHERE
            (f.account1 = account1 AND f.account2 = account2)OR
            (f.account2 = account1 AND f.account1 = account2)
    );
END; $

CREATE FUNCTION IF NOT EXISTS JDash_checkBothFriendRequests(account1 INT, account2 INT)
    RETURNS BOOLEAN
    READS SQL DATA
    NOT DETERMINISTIC
BEGIN
    RETURN (
        SELECT COUNT(*) = 2
        FROM friend_requests as r
        WHERE
            (r.sender = account1 AND r.receiver = account2) OR
            (r.receiver = account1 AND r.sender = account2)
    );
END; $


CREATE TRIGGER IF NOT EXISTS JDash_automaticBothFriendRequestAcceptor AFTER INSERT ON friend_requests
    FOR EACH ROW
BEGIN
    IF (JDash_checkBothFriendRequests(new.sender, new.receiver)) THEN
        DELETE FROM friend_requests
        WHERE
            (sender = new.sender AND receiver = new.receiver) OR
            (receiver = new.sender AND sender = new.receiver);
    END IF;
END; $

DELIMITER ;