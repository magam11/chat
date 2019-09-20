CREATE TABLE `users`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `email`      varchar(255)     NOT NULL,
    `password`   varchar(255)     NOT NULL,
    `token`      varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `groups`
(
    `id`         int(11) unsigned                    NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)                        NOT NULL,
    `image_path` varchar(255)                        NOT NULL,
    `created_at` timestamp default current_timestamp not null,
    `updated_at` timestamp default CURRENT_TIMESTAMP not null on update current_timestamp,
    `owner_id`   int(11) unsigned                    not null,
    PRIMARY KEY (`id`),
    KEY `owner_id` (`owner_id`),
    CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `blocked_users`
(
    `id`              int(11) unsigned                    NOT NULL AUTO_INCREMENT,
    `user_id`         int(11) unsigned                    NOT NULL,
    `blocked_user_id` int(11) unsigned                    NOT NULL,
    `blocked_at`      timestamp default CURRENT_TIMESTAMP not null,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    KEY `blocked_user_id` (`blocked_user_id`),
    CONSTRAINT `blocked_users_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `blocked_users_ibfk_2` FOREIGN KEY (`blocked_user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `files`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `path`        varchar(255) DEFAULT NULL,
    `origin_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `messages`
(
    `id`           bigint unsigned                     NOT NULL AUTO_INCREMENT,
    `text`         text,
    `to_user_id`   int(11) unsigned                    NOT NULL,
    `file_id`      int(11) unsigned                    NOT NULL,
    `from_user_id` int(11) unsigned                    NOT NULL,
    `created_at`   timestamp default CURRENT_TIMESTAMP not null,
    `updated_at`   timestamp default CURRENT_TIMESTAMP not null on update current_timestamp,
    PRIMARY KEY (`id`),
    KEY `file_id` (`file_id`),
    KEY `to_user_id` (`to_user_id`),
    KEY `from_user_id` (`from_user_id`),
    CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`from_user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `group_messages`
(
    `id`           int(11) unsigned                    NOT NULL AUTO_INCREMENT,
    `group_id`     int(11) unsigned                    NOT NULL,
    `from_user_id` int(11) unsigned                    NOT NULL,
    `file_id`      int(11) unsigned                    NOT NULL,
    `text`         text,
    `created_at`   timestamp default CURRENT_TIMESTAMP not null,
    `updated_at`   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `group_id` (`group_id`),
    KEY `to_user_id` (`from_user_id`),
    KEY `file_id` (`file_id`),
    CONSTRAINT `group_messages_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
    CONSTRAINT `group_messages_ibfk_2` FOREIGN KEY (`from_user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `group_users`
(
    `id`       int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`  int(11) unsigned NOT NULL,
    `group_id` int(11) unsigned NOT NULL,
    `added_at` timestamp default current_timestamp not null ,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `group_users_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `group_users_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;








