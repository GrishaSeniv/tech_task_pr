CREATE TABLE `user`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Primary key, auto-incremented unique identifier for each user',
    `login`      VARCHAR(50)  NOT NULL UNIQUE COMMENT 'Unique login username for the user',
    `password`   VARCHAR(255) NOT NULL COMMENT 'Hashed password for user authentication',
    `roles`      JSON COMMENT 'JSON array of user roles (e.g., admin, user)',
    `first_name` VARCHAR(100) NOT NULL COMMENT 'First name of the user',
    `last_name`  VARCHAR(100) NOT NULL COMMENT 'Last name of the user',
    `surname`    VARCHAR(100) NOT NULL COMMENT 'Surname or middle name of the user',
    PRIMARY KEY (`id`)
) COMMENT = 'Table storing user information for authentication and authorization';

CREATE TABLE client
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Primary key, auto-incremented unique identifier for each client',
    `first_name`     VARCHAR(100) NOT NULL COMMENT 'First name of the client',
    `last_name`      VARCHAR(100) NOT NULL COMMENT 'Last name of the client',
    `first_name_lat` VARCHAR(100) NOT NULL COMMENT 'First name of the client in Latin script',
    `last_name_lat`  VARCHAR(100) NOT NULL COMMENT 'Last name of the client in Latin script',
    `birthday`       DATE COMMENT 'Client birthday',
    `phone`          VARCHAR(20) COMMENT 'Phone number, may include country code and symbols',
    `email`          VARCHAR(255) UNIQUE COMMENT 'Client email address',
    `created_by`     BIGINT COMMENT 'Operator ID (who created this client record)',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
) COMMENT = 'Table storing client information';

CREATE TABLE card
(
    `id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Primary key, auto-incremented unique identifier for each card',
    `name`      VARCHAR(100) NOT NULL COMMENT 'Card name',
    `bin`       VARCHAR(6)   NOT NULL COMMENT 'Card BIN (Bank Identification Number) used for card number generation',
    `is_active` BOOLEAN DEFAULT TRUE COMMENT 'Is card active or not',
    `edited_by` BIGINT COMMENT 'ID of the user who created or edited this card',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`edited_by`) REFERENCES `user` (`id`)
) COMMENT = 'Table storing card information';

CREATE TABLE `order`
(
    `id`          BIGINT    NOT NULL AUTO_INCREMENT COMMENT 'Primary key, auto-incremented unique identifier for each order',
    `created_at`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'When the order was created',
    `client_id`   BIGINT    NOT NULL COMMENT 'Client ID, for which this card will be ordered',
    `created_by`  BIGINT    NOT NULL COMMENT 'Operator ID (who created this order)',
    `card_id`     BIGINT    NOT NULL COMMENT 'Card ID for the order',
    `card_number` VARCHAR(16) UNIQUE COMMENT 'Card number for the order',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`client_id`) REFERENCES client (`id`),
    FOREIGN KEY (`card_id`) REFERENCES card (`id`),
    FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
) COMMENT = 'Table storing order information';
