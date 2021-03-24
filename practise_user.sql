-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: practise
-- ------------------------------------------------------
-- Server version	5.6.44-log

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'singhbhaskar209@gmail.com','bhaskar','singh','$2a$10$eLuMuIxz2AVc8qnlnJBtR.jAfG0lttvUjlKv5DNPwu1WHZjO/Fjn2','9768243197'),(5,'shivamsingh@gmail.com','shivam','singh','$2a$10$8.l8j5bwSIrekXnnVsMEIuoAYVrEG/MPQRYcP3icoZf8c7y7GiZ6K','9712345678'),(6,'raj@gmail.com','rajshekhar','singh','$2a$10$dE9DdKYaYgJIDOq6PX2VuOsviRmDdcwUhmG7IdK19h.DU1dekinwm','9768243197'),(7,'shivamsingh@gmail.com','shekhar','singh','$2a$10$ts4V8AwKFJAmzEi87cXNg.UscFD3V5pqIpRucWCIiGRj/neIV1UNu','9768243197'),(8,'aarti@gmail.com','aarti','singh','$2a$10$pMTW7uuqr7abLal3YLr5YeNogzf3vPkKX/6nyHT3kFSibmE2qFNoq','9768243197'),(9,'varsha@gmail.com','varsha','singh','$2a$10$Uehr4nsk.CeciBaIrMNvaeAuop5CqIC4ik8COb77avjX3HTm5C/F6','9768243197'),(10,'abhilashpathak@gmail.com','abhilash','pathak','$2a$10$RRTjB9UO9/HIig5CwgFVA.rA2PUirrZ6H/8Cck/0YQuiOckse5iaW','8108615982');
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

-- Dump completed on 2020-08-28 23:58:29
