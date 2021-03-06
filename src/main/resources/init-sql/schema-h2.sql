DROP TABLE IF EXISTS `SE_TB_BRD` CASCADE;
DROP TABLE IF EXISTS `SE_TB_USR` CASCADE;
DROP SEQUENCE IF EXISTS BRD_SEQ;
DROP SEQUENCE IF EXISTS USR_SEQ;

CREATE TABLE `SE_TB_USR`
(
	`NO`         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`USER_ID`    VARCHAR(100) NOT NULL,
	`USER_PASSWORD`    VARCHAR(500) NOT NULL,
	`NICKNAME`   VARCHAR(100) NOT NULL,
	`CREATED_AT` DATE DEFAULT NOW()
);

CREATE TABLE `SE_TB_BRD`
(
	`NO`            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`VIEW_TYPE`     CHAR(1) DEFAULT '0',
	`CONTENT`       CLOB         NULL,
	`DEL_YN`        CHAR(1) DEFAULT 'N',
	`WRITE_USER_NO` INT          NOT NULL,
	`CREATED_AT`    CHAR(14)    DEFAULT FORMATDATETIME(NOW(), 'yyyyMMddHHmmss'),
	FOREIGN KEY (`WRITE_USER_NO`) REFERENCES `SE_TB_USR` (`NO`)
);

CREATE INDEX `IDX_BRD_1` ON `SE_TB_BRD` (`NO` DESC);
CREATE INDEX `IDX_USR_1` ON `SE_TB_USR` (`NO` DESC);

CREATE SEQUENCE `BRD_SEQ`
	INCREMENT BY 1;

CREATE SEQUENCE `USR_SEQ`
	INCREMENT BY 1;

