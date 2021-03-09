DROP DATABASE IF EXISTS erp;

CREATE DATABASE `erp`;

USE `erp`;

CREATE TABLE `departament` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
    `head` int(11) NOT NULL,
    PRIMARY KEY (id),
    KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `position` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
    PRIMARY KEY (id),
    KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `login` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `firstName` varchar(50) NOT NULL,
    `lastName` varchar(50) NOT NULL,
    `patronymic` varchar(50),
    `number` varchar(50),
    `birthDay` DATE,
    `departamentId` int(11),
    `positionId` int(11),
    `userType` ENUM('EMPLOYEE', 'ADMIN', 'HEAD', 'DIRECTOR') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY login (login),
    KEY password (password),
    KEY firstName (firstName),
    KEY lastName (lastName),
    KEY patronymic (patronymic),
    KEY number (number),
    KEY birthDay (birthDay),
	FOREIGN KEY (departamentId) REFERENCES departament (id) ON DELETE CASCADE,
	FOREIGN KEY (positionId) REFERENCES position (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE departament ADD CONSTRAINT fk_head FOREIGN KEY (head) REFERENCES user (id);

CREATE TABLE `session` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sessionId` varchar(50) NOT NULL,
    `userId` int(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY sessionId (sessionId),
    FOREIGN KEY (userId) REFERENCES `user` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;