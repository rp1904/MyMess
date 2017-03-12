-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.20


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema mymess
--

CREATE DATABASE IF NOT EXISTS mymess;
USE mymess;

--
-- Definition of table `mymess`.`user_roles`
--

DROP TABLE IF EXISTS `mymess`.`user_roles`;
CREATE TABLE  `mymess`.`user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `UK_c519w0l613l023tayy895chpd` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mymess`.`user_roles`
--

/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `mymess`.`user_roles` (`user_role_id`,`role`) VALUES 
 (2,'ADMIN'),
 (3,'MEMBER'),
 (1,'SUPERADMIN');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;


--
-- Definition of table `mymess`.`users`
--

DROP TABLE IF EXISTS `mymess`.`users`;
CREATE TABLE  `mymess`.`users` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(60) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(60) NOT NULL,
  `user_info_id_fk` varchar(255) DEFAULT NULL,
  `user_role_id_fk` int(11) DEFAULT NULL,
  `mobileNumber` varchar(255) DEFAULT NULL,
  `confirmPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK_p3ketj7wg2ghsajghmlrwh6k3` (`user_info_id_fk`),
  KEY `FK_9m8t5x9krkb6ns9pevw27abjt` (`user_role_id_fk`),
  CONSTRAINT `FK_9m8t5x9krkb6ns9pevw27abjt` FOREIGN KEY (`user_role_id_fk`) REFERENCES `user_roles` (`user_role_id`),
  CONSTRAINT `FK_p3ketj7wg2ghsajghmlrwh6k3` FOREIGN KEY (`user_info_id_fk`) REFERENCES `users_info` (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mymess`.`users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `mymess`.`users` (`user_id`,`email`,`enabled`,`password`,`user_info_id_fk`,`user_role_id_fk`,`mobileNumber`,`confirmPassword`) VALUES 
 ('8c849e1f-2d4e-4673-b738-18bdc7505b61','roshan.patil@gmail.com','\0','123456','91e56063-2921-409c-bb81-66a47d22137a',NULL,'8308238754','123456'),
 ('f19f31de-510a-4bca-ad70-b4377f48c28c','roshanpatil@gmail.com','','$2a$06$eGFglz58iXlFtGdq5YuQgOgZgl/UxtCa2HnpJZm28lHNR4oow/as2','438cf6be-7c66-4fb6-8910-b97e6ae6e0c5',1,'8308238755','$06$eGFglz58iXlFtGdq5YuQgOgZgl/UxtCa2HnpJZm28lHNR4oow/as2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `mymess`.`users_info`
--

DROP TABLE IF EXISTS `mymess`.`users_info`;
CREATE TABLE  `mymess`.`users_info` (
  `user_info_id` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `registrationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mymess`.`users_info`
--

/*!40000 ALTER TABLE `users_info` DISABLE KEYS */;
INSERT INTO `mymess`.`users_info` (`user_info_id`,`firstName`,`imagePath`,`lastName`,`middleName`,`registrationDate`) VALUES 
 ('438cf6be-7c66-4fb6-8910-b97e6ae6e0c5','Roshan',NULL,'Patil',NULL,'2017-03-04 15:45:26'),
 ('91e56063-2921-409c-bb81-66a47d22137a','Roshan',NULL,'Patil',NULL,NULL);
/*!40000 ALTER TABLE `users_info` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
