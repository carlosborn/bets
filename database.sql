CREATE DATABASE bets;
USE DATABASE bets;

CREATE TABLE crash_rounds (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	hash varchar(100) NOT NULL,
	status varchar(30) NOT NULL,
	crash_point DOUBLE DEFAULT 0 NOT NULL,
	total_eur_bet DOUBLE PRECISION UNSIGNED DEFAULT 0 NOT NULL,
	total_bets_placed INT UNSIGNED DEFAULT 0 NOT NULL,
	total_eur_won DOUBLE PRECISION UNSIGNED DEFAULT 0 NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT crash_rounds_pk PRIMARY KEY (id),
	CONSTRAINT crash_rounds_unique UNIQUE KEY (hash)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE crash_bets (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	hash varchar(100) NOT NULL,
	cashed_out_at DOUBLE UNSIGNED NULL,
	amount DOUBLE UNSIGNED DEFAULT 0 NOT NULL,
	currency_type varchar(10) NOT NULL,
	auto_cashout_at DOUBLE UNSIGNED DEFAULT 0 NOT NULL,
	win_amount DOUBLE PRECISION UNSIGNED DEFAULT 0 NOT NULL,
	status varchar(50) NOT NULL,
	CONSTRAINT crash_bets_pk PRIMARY KEY (id),
	CONSTRAINT crash_bets_unique UNIQUE KEY (hash)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE users (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	user_id_platform BIGINT UNSIGNED NOT NULL,
	id_str varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	`rank` varchar(100) NOT NULL,
	created_at DATETIME NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

ALTER TABLE crash_bets ADD user_id BIGINT UNSIGNED NOT NULL;
ALTER TABLE crash_bets ADD CONSTRAINT crash_bets_users_fk FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE crash_bets ADD round_id BIGINT UNSIGNED NOT NULL;

ALTER TABLE crash_bets ADD CONSTRAINT crash_bets_crash_rounds_fk FOREIGN KEY (round_id) REFERENCES crash_rounds(id);

ALTER TABLE crash_rounds MODIFY COLUMN crash_point double DEFAULT 0 NULL;
ALTER TABLE crash_rounds MODIFY COLUMN crash_point double NULL;
ALTER TABLE crash_rounds MODIFY COLUMN total_eur_won double unsigned NULL;

ALTER TABLE crash_rounds MODIFY COLUMN total_bets_placed int(10) unsigned NULL;
ALTER TABLE crash_rounds MODIFY COLUMN total_eur_bet double unsigned NULL;





