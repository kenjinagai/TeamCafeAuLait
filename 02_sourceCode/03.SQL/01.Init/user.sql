-- MySQL dump 10.13  Distrib 5.7.18, for Win32 (AMD64)
--
-- Host: localhost    Database: user
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `extended_authentication`
--

DROP TABLE IF EXISTS `extended_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extended_authentication` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `extended_authentication_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `extended_authentication_value_UNIQUE` (`extended_authentication_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extended_authentication`
--

LOCK TABLES `extended_authentication` WRITE;
/*!40000 ALTER TABLE `extended_authentication` DISABLE KEYS */;
INSERT INTO `extended_authentication` VALUES (1,'admin','1'),(2,'guest','2'),(3,'user','3');
/*!40000 ALTER TABLE `extended_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'登録権限','create'),(2,'参照権限','read'),(3,'管理権限','manageUser');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理者','ROLE_ADMIN'),(2,'一般ユーザ','ROLE_USER'),(3,'ゲスト','ROLE_GUEST');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_has_permission`
--

DROP TABLE IF EXISTS `role_has_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_has_permission` (
  `role_name` varchar(255) NOT NULL,
  `permission_id` int(11) NOT NULL,
  KEY `FK2h8xukv5c6o207f1iyj555146` (`permission_id`),
  CONSTRAINT `FK2h8xukv5c6o207f1iyj555146` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_has_permission`
--

LOCK TABLES `role_has_permission` WRITE;
/*!40000 ALTER TABLE `role_has_permission` DISABLE KEYS */;
INSERT INTO `role_has_permission` VALUES ('ROLE_ADMIN',1),('ROLE_ADMIN',2),('ROLE_ADMIN',3),('ROLE_USER',1),('ROLE_USER',2),('ROLE_GUEST',1);
/*!40000 ALTER TABLE `role_has_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `encoded_password` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `extended_authentication` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`),
  UNIQUE KEY `extended_authentication_UNIQUE` (`extended_authentication`),
  CONSTRAINT `FKtos75qhl08dib2gbyxk6hypf` FOREIGN KEY (`extended_authentication`) REFERENCES `extended_authentication` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','鈴木一朗',1),('guest','guest','佐藤花子',2),('user','user','田中太郎',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_extended_authentication`
--

DROP TABLE IF EXISTS `user_has_extended_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_extended_authentication` (
  `user_user_id` varchar(255) NOT NULL,
  `user_has_extended_authenticationcol` varchar(255) DEFAULT NULL,
  `extended_authentication_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_user_id`),
  UNIQUE KEY `user_has_extended_authenticationcol_UNIQUE` (`user_has_extended_authenticationcol`),
  KEY `FKpufdvf1rcrdxh34yajv8k44o1` (`extended_authentication_id`),
  KEY `FKgl6rb85stc49s6eh09c53147p` (`user_id`),
  CONSTRAINT `FKgl6rb85stc49s6eh09c53147p` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKpufdvf1rcrdxh34yajv8k44o1` FOREIGN KEY (`extended_authentication_id`) REFERENCES `extended_authentication` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_extended_authentication`
--

LOCK TABLES `user_has_extended_authentication` WRITE;
/*!40000 ALTER TABLE `user_has_extended_authentication` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_extended_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_role`
--

DROP TABLE IF EXISTS `user_has_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_role` (
  `user_name` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKc1m07gjgx777ukpfw6wa94dfh` (`role_id`),
  CONSTRAINT `FKc1m07gjgx777ukpfw6wa94dfh` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_role`
--

LOCK TABLES `user_has_role` WRITE;
/*!40000 ALTER TABLE `user_has_role` DISABLE KEYS */;
INSERT INTO `user_has_role` VALUES ('佐藤花子',3),('鈴木一朗',1),('田中太郎',2);
/*!40000 ALTER TABLE `user_has_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-22 18:39:28
