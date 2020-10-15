DROP TABLE IF EXISTS `SE_TB_BRD` CASCADE ;
DROP TABLE IF EXISTS `SE_TB_USR` CASCADE ;

CREATE TABLE `SE_TB_USR`
(
	`NO`         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`USER_ID`    VARCHAR(100) NOT NULL,
	`NICKNAME`   VARCHAR(100) NOT NULL,
	`CREATED_AT` DATE DEFAULT NOW(),
	`UPDATED_AT` DATE         NULL
);

CREATE TABLE `SE_TB_BRD`
(
	`NO`            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`VIEW_TYPE`     CHAR(1) DEFAULT '0',
	`TITLE`         VARCHAR(100) NOT NULL,
	`CONTENT`       BLOB         NOT NULL,
	`DEL_YN`        CHAR(1) DEFAULT 'N',
	`WRITE_USER_NO` INT          NOT NULL,
	`CREATED_AT`    DATE    DEFAULT NOW(),
	`UPDATED_AT`    DATE         NULL,
	FOREIGN KEY (`WRITE_USER_NO`) REFERENCES `SE_TB_USR` (`NO`)
);

CREATE INDEX IDX_BRD_1 ON `SE_TB_BRD` (`NO` DESC);

