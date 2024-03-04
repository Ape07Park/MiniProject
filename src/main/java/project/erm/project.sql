SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS BoardAdvice;
DROP TABLE IF EXISTS BoardAuction;
DROP TABLE IF EXISTS BoardBuy;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE BoardAdvice
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	title varchar(256) NOT NULL,
	content varchar(4000),
	modTime datetime DEFAULT CURRENT_TIMESTAMP,
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	replyCount int DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE BoardAuction
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	applTime datetime NOT NULL,
	nickName varchar(16),
	processTitle varchar(64),
	processContent varchar(256),
	avgPrice int DEFAULT 0,
	numOfCompany int DEFAULT 0,
	process int DEFAULT 0,
	isDeleted int DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE BoardBuy
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	applTime datetime NOT NULL,
	nickName varchar(16),
	processTitle varchar(64),
	processContent varchar(256),
	avgPrice int DEFAULT 0,
	numOfCompany int DEFAULT 0,
	process int DEFAULT 0,
	isDeleted int DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE equipment
(
	inum int NOT NULL AUTO_INCREMENT,
	category varchar(16) NOT NULL,
	ename varchar(64) NOT NULL,
	eContent varchar(256) NOT NULL,
	PRIMARY KEY (inum)
);


CREATE TABLE users
(
	uid varchar(12) NOT NULL,
	pwd char(60) NOT NULL,
	uname varchar(16) NOT NULL,
	email varchar(32),
	nickName varchar(16),
	regDate date DEFAULT (CURRENT_DATE),
	isDeleted int DEFAULT 0,
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE BoardAdvice
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardAuction
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardBuy
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



