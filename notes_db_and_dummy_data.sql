-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: notes
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

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
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_user_id` int(11) NOT NULL,
  `created` varchar(45) DEFAULT NULL,
  `modified` date DEFAULT NULL,
  `content` text,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`note_id`),
  UNIQUE KEY `note_id_UNIQUE` (`note_id`),
  KEY `fk_notes_users_idx` (`users_user_id`),
  CONSTRAINT `fk_notes_users` FOREIGN KEY (`users_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,3,'2016-02-03','2016-02-03','Note for user2','Title A'),(2,3,'2016-02-03','2016-02-03','2nd Note for User2','Title B'),(3,2,'2016-02-02 00:00:00','2016-02-02','La Note for User1 asawd 222\n44','Title A S'),(4,2,'2016-02-02 00:00:00','2016-02-02','as 2nd Note for user1 sasa SDFDSSDSDFSDFSDFSDFSDSDFSDFSDFSDFSDFSDFSDFSDFSDF\nSDFSDFSDFSDFSDF\nsfsdf\nsdf asdasd\nsdf\nsd\nfsdf\nsdf\nsd\nfsd\nfsdfsdfsdfsdf\nsdf','Title B JOJO'),(5,2,'2016-03-09 10:47:14.115','2016-03-09','FOFOFOFffs fc sd\nModified','My Tile'),(28,1,'2016-03-08 16:09:03.953','2016-03-08','555 mmmmm','5th'),(30,1,'2016-03-08 16:36:50.746','2016-03-08','kjkjnkjnjk\n>>>','LLL'),(31,2,'2016-03-09 09:44:47.843','2016-03-09',' errvevre','dfzxczxczxczxczxczxczczxczxczxczxczxczczxcxc'),(32,2,'2016-03-09 11:46:30.471','2016-03-09','11','111'),(33,2,'2016-03-09 11:46:36.116','2016-03-09','222','222'),(34,2,'2016-03-09 11:48:31.242','2016-03-09',NULL,'333'),(35,2,'2016-03-09 11:50:11.961','2016-03-09','555 666','3333'),(36,2,'2016-03-09 11:54:36.823','2016-03-09','fffffffffffff','FF'),(37,3,'2016-03-09 13:02:23.271','2016-03-09','Fas','Blah');
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idusers_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@test.com','qwerty'),(2,'user1','user1@test.com','qwerty1'),(3,'user2','user2@test.com','qwerty2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-10 10:33:54
