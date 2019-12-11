# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: printgo
# Generation Time: 2019-12-09 10:32:19 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `sso_id`, `password`, `first_name`, `last_name`, `email`, `status`)
VALUES
	(2,'vanhuu','$2a$10$RSiPycV7Joxfhn4EecDczue1/ccNAF50zk6K0aYHBZ2iXVStKrX4K','van','huu','bonphuonglanha@gmail.com',1);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_link_profile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_link_profile`;

CREATE TABLE `user_link_profile` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_link_profile` WRITE;
/*!40000 ALTER TABLE `user_link_profile` DISABLE KEYS */;

INSERT INTO `user_link_profile` (`user_id`, `user_profile_id`)
VALUES
	(2,1);

/*!40000 ALTER TABLE `user_link_profile` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_permision
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_permision`;

CREATE TABLE `user_permision` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) NOT NULL,
  `roles` varchar(255) NOT NULL DEFAULT 'hasRole(''USER'')',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_permision` WRITE;
/*!40000 ALTER TABLE `user_permision` DISABLE KEYS */;

INSERT INTO `user_permision` (`id`, `action`, `roles`)
VALUES
	(3,'/admin/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(6,'/admin/banner/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(7,'/admin/page/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(8,'/admin/block-home/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(9,'/admin/config/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(10,'/admin/menu/**','hasRole(\'USER\') or hasRole(\'ADMIN\') or hasRole(\'DBA\')'),
	(11,'/admin/add-user','hasRole(\'ADMIN\')'),
	(12,'/admin/delete-user-*','hasRole(\'ADMIN\')'),
	(13,'/admin/edit-user-*','hasRole(\'ADMIN\')');

/*!40000 ALTER TABLE `user_permision` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_profile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;

INSERT INTO `user_profile` (`id`, `type`)
VALUES
	(2,'ROLE_ADMIN'),
	(3,'ROLE_DBA'),
	(1,'ROLE_USER');

/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
