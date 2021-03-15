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
    `firstName` varchar(50) NOT NULL,
    `lastName` varchar(50) NOT NULL,
    `patronymic` varchar(50),
    `deleted` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    UNIQUE KEY login (login),
    KEY firstName (firstName),
    KEY lastName (lastName),
    KEY patronymic (patronymic),
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

CREATE TABLE `project` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `gipId` int(11) NOT NULL,
    `userId` int(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY name (name),
    FOREIGN KEY (gipId) REFERENCES `user` (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES `user` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `task` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `gipId` int(11) NOT NULL,
    `userId` int(11) NOT NULL,
    `projectId` int(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY name (name),
    FOREIGN KEY (gipId) REFERENCES `user` (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES `user` (id) ON DELETE CASCADE,
    FOREIGN KEY (projectId) REFERENCES `project` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `user_hours` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userId` int(11) NOT NULL,
    `date` date NOT NULL,
    `hours` int(11) NOT NULL,
    `taskId` int(11) NOT NULL,
    `projectId` int(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES `user` (id) ON DELETE CASCADE,
    KEY `date` (date),
    KEY `hours` (date),
    FOREIGN KEY (taskId) REFERENCES `task` (id) ON DELETE CASCADE,
    FOREIGN KEY (projectId) REFERENCES `project` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `department`(name) VALUES('ОПИСИ');

INSERT INTO `position`(name, departmentId) VALUES('Инженер АСУТП 2 категории', 1);