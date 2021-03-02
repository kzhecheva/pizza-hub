-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for pizza_db
CREATE DATABASE IF NOT EXISTS `pizza_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pizza_db`;

-- Dumping structure for table pizza_db.cart_order
CREATE TABLE IF NOT EXISTS `cart_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `checked_out` tinyint(1) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Dumping data for table pizza_db.cart_order: ~23 rows (approximately)
/*!40000 ALTER TABLE `cart_order` DISABLE KEYS */;
INSERT INTO `cart_order` (`order_id`, `checked_out`, `order_date`) VALUES
	(1, 1, '2021-02-24 18:02:38'),
	(2, 1, '2021-02-22 18:02:52'),
	(3, 1, '2021-03-01 14:26:45'),
	(6, 1, '2021-03-01 15:24:25'),
	(7, 1, '2021-03-01 15:47:47'),
	(8, 1, '2021-03-01 15:50:32'),
	(9, 1, '2021-03-01 16:30:32'),
	(10, 1, '2021-03-01 16:35:36'),
	(11, 1, '2021-03-01 16:36:06'),
	(12, 1, '2021-03-01 16:39:40'),
	(13, 1, '2021-03-01 16:40:10'),
	(14, 1, '2021-03-01 16:42:21'),
	(15, 1, '2021-03-01 16:43:00'),
	(16, 1, '2021-03-01 16:44:41'),
	(17, 1, '2021-03-01 16:48:29'),
	(18, 1, '2021-03-01 16:54:38'),
	(22, 1, '2021-03-01 17:09:31'),
	(27, 1, '2021-03-01 17:30:35'),
	(28, 1, '2021-03-01 18:20:02'),
	(29, 1, '2021-03-01 18:20:34'),
	(30, 1, '2021-03-01 18:37:27'),
	(36, 1, '2021-03-02 09:30:33'),
	(37, 0, NULL);
/*!40000 ALTER TABLE `cart_order` ENABLE KEYS */;

-- Dumping structure for table pizza_db.order_pizzas
CREATE TABLE IF NOT EXISTS `order_pizzas` (
  `order_pizzas_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `pizza_id` int(11) DEFAULT NULL,
  `pizza_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_pizzas_id`),
  KEY `cart_pizza_pizza_id_fk` (`pizza_id`),
  KEY `cart_order_order_id_fk` (`order_id`),
  CONSTRAINT `cart_order_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `cart_order` (`order_id`),
  CONSTRAINT `cart_pizza_pizza_id_fk` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

-- Dumping data for table pizza_db.order_pizzas: ~23 rows (approximately)
/*!40000 ALTER TABLE `order_pizzas` DISABLE KEYS */;
INSERT INTO `order_pizzas` (`order_pizzas_id`, `order_id`, `pizza_id`, `pizza_amount`) VALUES
	(1, 1, 1, 2),
	(2, 1, 2, 3),
	(3, 2, 1, 2),
	(4, 2, 2, 4),
	(5, 3, 1, 5),
	(6, 3, 1, 1),
	(16, 6, 1, 1),
	(17, 7, 1, 2),
	(18, 7, 2, 2),
	(19, 8, 1, 2),
	(20, 8, 2, 3),
	(21, 9, 1, 2),
	(22, 9, 2, 3),
	(23, 10, 1, 2),
	(24, 10, 2, 2),
	(25, 11, 1, 2),
	(26, 11, 2, 2),
	(27, 12, 1, 2),
	(28, 12, 2, 2),
	(29, 13, 1, 1),
	(30, 13, 2, 1),
	(62, 36, 1, 4),
	(63, 36, 2, 3);
/*!40000 ALTER TABLE `order_pizzas` ENABLE KEYS */;

-- Dumping structure for table pizza_db.pizza
CREATE TABLE IF NOT EXISTS `pizza` (
  `pizza_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `picture` mediumblob NOT NULL,
  PRIMARY KEY (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table pizza_db.pizza: ~2 rows (approximately)
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` (`pizza_id`, `name`, `description`, `picture`) VALUES
INSERT INTO `pizza` (`pizza_id`, `name`, `description`, `picture`) VALUES
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;