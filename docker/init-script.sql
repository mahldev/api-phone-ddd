-- MySQL dump 10.13  Distrib 5.7.44, for Linux (x86_64)
--
-- Host: localhost    Database: eshop
-- ------------------------------------------------------
-- Server version       5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES
(1,'Apple'),
(2,'Samsung'),
(3,'Xiaomi'),
(4,'Huawei'),
(5,'Sony'),
(6,'LG'),
(7,'Nokia'),
(8,'Motorola');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES 
(1,0),
(2,1),
(3,2),
(4,3),
(5,4),
(6,5),
(7,6),
(8,7),
(9,8),
(10,9),
(11,10),
(12,11),
(13,12),
(14,13),
(15,14),
(16,15),
(17,16),
(18,17),
(19,18),
(20,19);
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `color` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `phone_id` bigint(20) DEFAULT NULL,
  `storage_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_items_color` (`color`),
  KEY `FK_order_items_storage_size` (`storage_size`),
  KEY `FK_order_items_order_id` (`order_id`),
  KEY `FK_order_items_phone_id` (`phone_id`),
  CONSTRAINT `FK_order_items_color` FOREIGN KEY (`color`) REFERENCES `colors` (`id`),
  CONSTRAINT `FK_order_items_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_order_items_phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`),
  CONSTRAINT `FK_order_items_storage_size` FOREIGN KEY (`storage_size`) REFERENCES `storages_sizes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_user_id` (`user_id`),
  CONSTRAINT `FK_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_colors`
--

DROP TABLE IF EXISTS `phone_colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_colors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color_commercial_name` varchar(255) DEFAULT NULL,
  `color_name` bigint(20) DEFAULT NULL,
  `phone_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phone_colors_phone_id` (`phone_id`),
  KEY `FK_phone_colors_color_name` (`color_name`),
  CONSTRAINT `FK_phone_colors_color_name` FOREIGN KEY (`color_name`) REFERENCES `colors` (`id`),
  CONSTRAINT `FK_phone_colors_phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_colors`
--

LOCK TABLES `phone_colors` WRITE;
/*!40000 ALTER TABLE `phone_colors` DISABLE KEYS */;
INSERT INTO `phone_colors` VALUES 
(1,'blue titanium',2,1),
(2,'white titanium',10,1),
(3,'natural titanium',11,1),
(4,'black titanium',9,1),
(5,'black',9,2),
(6,'meadow green',3,2),
(7,'alpine blue',2,2),
(8,'mint',3,3),
(10,'graphite',11,3),
(11,'lavander',7,3),
(12,'cream',13,3);
/*!40000 ALTER TABLE `phone_colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_images`
--

DROP TABLE IF EXISTS `phone_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(255) DEFAULT NULL,
  `phone_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phone_images_phone_id` (`phone_id`),
  CONSTRAINT `FK_phone_images_phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_images`
--

LOCK TABLES `phone_images` WRITE;
/*!40000 ALTER TABLE `phone_images` DISABLE KEYS */;
INSERT INTO `phone_images` VALUES
(1,'apple-iphone-15-pro-max-black.webp',1),
(2,'apple-iphone-15-pro-max-gray.webp',1),
(3,'apple-iphone-15-pro-max-white.webp',1),
(4,'apple-iphone-15-pro-max-blue.webp',1),
(5,'xiaomi-13t-pro-black.webp',2),
(6,'xiaomi-13t-pro-blue.webp',2),
(7,'xiaomi-13t-pro-green.webp',2),
(8,'samsung-galaxy-z-flip5-black.webp',3),
(9,'samsung-galaxy-z-flip5-green.webp',3),
(10,'samsung-galaxy-z-flip5-pink.webp',3),
(11,'samsung-galaxy-z-flip5-white.webp',3);
/*!40000 ALTER TABLE `phone_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_storage_sizes`
--

DROP TABLE IF EXISTS `phone_storage_sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_storage_sizes` (
  `phone_id` bigint(20) NOT NULL,
  `storage_size_id` bigint(20) NOT NULL,
  PRIMARY KEY (`phone_id`,`storage_size_id`),
  KEY `FK_phone_storage_sizes_storage_size_id` (`storage_size_id`),
  CONSTRAINT `FK_phone_storage_sizes_phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`),
  CONSTRAINT `FK_phone_storage_sizes_storage_size_id` FOREIGN KEY (`storage_size_id`) REFERENCES `storages_sizes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_storage_sizes`
--

LOCK TABLES `phone_storage_sizes` WRITE;
/*!40000 ALTER TABLE `phone_storage_sizes` DISABLE KEYS */;
INSERT INTO `phone_storage_sizes` VALUES 
(1,1),
(1,2),
(1,3),
(2,3),
(2,4),
(3,3),
(3,4);
/*!40000 ALTER TABLE `phone_storage_sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phones_brand_id` (`brand_id`),
  CONSTRAINT `FK_phones_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES
(1,'iPhone 15 Pro Max',1469.99,1),
(2,'Xiaomi 13T Pro 12',833.00,2),
(3,'Samsung Galaxy Z Flip5',687.07,3);
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storages_sizes`
--

DROP TABLE IF EXISTS `storages_sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storages_sizes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size_in_gb` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storages_sizes`
--

LOCK TABLES `storages_sizes` WRITE;
/*!40000 ALTER TABLE `storages_sizes` DISABLE KEYS */;
INSERT INTO `storages_sizes` VALUES
(1,1),
(2,2),
(3,3),
(4,4);
/*!40000 ALTER TABLE `storages_sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1, '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-06 21:28:48

