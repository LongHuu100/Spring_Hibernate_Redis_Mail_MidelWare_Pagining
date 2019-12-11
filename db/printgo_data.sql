# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: printgo
# Generation Time: 2019-12-09 10:33:15 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `amazon` bigint(20) unsigned DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `percent` int(2) DEFAULT NULL,
  `icon` varchar(255) DEFAULT '',
  `is_display_icon` int(1) unsigned DEFAULT '1',
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  `faqs_id` varchar(255) DEFAULT NULL,
  `seo_title` varchar(255) DEFAULT '',
  `seo_description` varchar(255) DEFAULT '',
  `seo_keyword` varchar(255) DEFAULT '',
  `count_items` int(10) DEFAULT '100',
  `seo_content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT '0',
  `provider_code` varchar(45) DEFAULT NULL,
  `giftcode` varchar(50) DEFAULT NULL,
  `image` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT '',
  `created_at` int(11) unsigned DEFAULT '0',
  `updated_at` int(11) unsigned DEFAULT '0',
  `published_at` int(10) unsigned DEFAULT '0',
  `weight` decimal(5,3) DEFAULT '0.000',
  `content` text,
  `social` varchar(200) DEFAULT NULL,
  `search` varchar(200) DEFAULT NULL,
  `price_ref` int(10) unsigned DEFAULT '0',
  `price_design` int(10) DEFAULT '0',
  `status` int(1) unsigned DEFAULT '0',
  `status_image` int(1) unsigned DEFAULT '0',
  `status_image_updated_at` int(10) unsigned DEFAULT '0',
  `status_content` int(1) unsigned DEFAULT '0',
  `status_content_updated_at` int(10) unsigned DEFAULT '0',
  `unit` varchar(50) DEFAULT '',
  `count_sold` int(10) DEFAULT '0',
  `tags` text,
  `pages_id` varchar(255) DEFAULT NULL,
  `faq_id` varchar(255) DEFAULT '',
  `file_print` varchar(255) DEFAULT NULL,
  `size_extend` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `product_provider_code_id` (`provider_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
