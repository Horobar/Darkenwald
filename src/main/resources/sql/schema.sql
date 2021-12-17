CREATE TABLE IF NOT EXISTS PLAYER
(
    id                LONG NOT NULL auto_increment PRIMARY KEY,
    playerName        VARCHAR(60)  NOT NULL,
    password          VARCHAR(255) NOT NULL,
    mail              VARCHAR(127) NOT NULL,
    avatar_image_link VARCHAR(256) NOT NULL,
    isAdmin BOOLEAN   not null DEFAULT false

);

CREATE TABLE IF NOT EXISTS MESSAGES
(
    id      LONG NOT NULL auto_increment PRIMARY KEY,
    player_id VARCHAR(128) NOT NUll,
    message VARCHAR NOT NULL,
    contentType ENUM('PLAIN', 'MARKDOWN') NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME,
    CONSTRAINT FK_MESSAGES_PLAYER foreign key (player_id) references PLAYER(id)
);

INSERT INTO PLAYER (playerName, password, mail, avatar_image_link, isAdmin)
VALUES ('admin', 'admin', 'admin@admin.com', 'www.hereIsNoImage.com', true);