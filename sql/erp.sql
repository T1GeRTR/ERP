DROP DATABASE IF EXISTS erp;

CREATE DATABASE `erp`;

USE `erp`;

CREATE TABLE `department` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
    `deleted` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    KEY name (name),
    KEY deleted (deleted)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `position` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
    `departmentId` int(11) NOT NULL,
    `deleted` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    KEY name (name),
    FOREIGN KEY (departmentId) REFERENCES department (id) ON DELETE CASCADE,
    KEY deleted (deleted)
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
    `departmentId` int(11),
    `positionId` int(11),
    `userType` ENUM('EMPLOYEE', 'ADMIN', 'HEAD', 'DIRECTOR') NOT NULL,
    `deleted` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    UNIQUE KEY login (login),
    KEY password (password),
    KEY firstName (firstName),
    KEY lastName (lastName),
    KEY patronymic (patronymic),
    KEY number (number),
    KEY birthDay (birthDay),
	FOREIGN KEY (departmentId) REFERENCES department (id) ON DELETE CASCADE,
	FOREIGN KEY (positionId) REFERENCES position (id) ON DELETE CASCADE,
    KEY deleted (deleted)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `session` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sessionId` varchar(50) NOT NULL,
    `userId` int(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY sessionId (sessionId),
    FOREIGN KEY (userId) REFERENCES `user` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `department`(name) VALUES('ОПИСИ');

INSERT INTO `position`(name, departmentId) VALUES('Инженер АСУТП 2 категории', 1);