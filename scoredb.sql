-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: scoredb
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MajorId` int(11) DEFAULT NULL,
  `TeacherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `MajorId` (`MajorId`),
  KEY `TeacherId` (`TeacherId`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`MajorId`) REFERENCES `major` (`Id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`TeacherId`) REFERENCES `teacher` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'IT01',1,16),(2,'IT01CLC',2,17),(3,'IT01TX',3,11),(4,'CS01',4,1),(5,'IT02CLC',2,6),(6,'FIN01',5,9),(7,'FIN01CLC',6,14),(8,'FIN01TX',7,4),(9,'DF01',14,5),(10,'DF01CLC',15,10),(11,'DF01TX',16,15),(12,'EC01',10,3),(13,'EC01CLC',11,8),(14,'EC01TX',12,13),(15,'MK01',20,2),(16,'MK01CLC',21,12),(17,'MK01TX',22,7);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Tài chính ngân hàng'),(2,'Marketing'),(3,'Công nghệ thông tin'),(4,'Kinh tế'),(5,'Ngoại ngữ');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forum` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Content` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  `SubjectTeacherId` int(11) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SubjectTeacherId` (`SubjectTeacherId`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `forum_ibfk_1` FOREIGN KEY (`SubjectTeacherId`) REFERENCES `subjectteacher` (`Id`),
  CONSTRAINT `forum_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forumcomment`
--

DROP TABLE IF EXISTS `forumcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forumcomment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Content` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  `ForumId` int(11) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  `ParentCommentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ForumId` (`ForumId`),
  KEY `UserId` (`UserId`),
  KEY `ParentCommentId` (`ParentCommentId`),
  CONSTRAINT `forumcomment_ibfk_1` FOREIGN KEY (`ForumId`) REFERENCES `forum` (`Id`),
  CONSTRAINT `forumcomment_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`),
  CONSTRAINT `forumcomment_ibfk_3` FOREIGN KEY (`ParentCommentId`) REFERENCES `forumcomment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forumcomment`
--

LOCK TABLES `forumcomment` WRITE;
/*!40000 ALTER TABLE `forumcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `forumcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MajorName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DepartmentId` int(11) DEFAULT NULL,
  `TrainingTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `DepartmentId` (`DepartmentId`),
  KEY `TrainingTypeId` (`TrainingTypeId`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`DepartmentId`) REFERENCES `department` (`Id`),
  CONSTRAINT `major_ibfk_2` FOREIGN KEY (`TrainingTypeId`) REFERENCES `trainingtype` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'Công nghệ thông tin',3,1),(2,'Công nghệ thông tin',3,2),(3,'Công nghệ thông tin',3,3),(4,'Khoa học máy tính',3,1),(5,'Tài chính doanh nghiệp',1,1),(6,'Tài chính doanh nghiệp',1,2),(7,'Tài chính doanh nghiệp',1,3),(8,'Ngân hàng',1,1),(9,'Ngân hàng',1,2),(10,'Kinh tế quốc tế',4,1),(11,'Kinh tế quốc tế',4,2),(12,'Kinh tế quốc tế',4,3),(13,'Kinh tế-Luật',4,3),(14,'Ngôn ngữ Anh',5,1),(15,'Ngôn ngữ Anh',5,2),(16,'Ngôn ngữ Anh',5,3),(17,'Ngôn ngữ Trung',5,1),(18,'Ngôn ngữ Trung',5,2),(19,'Ngôn ngữ Trung',5,3),(20,'Digital Marketing',2,1),(21,'Digital Marketing',2,2),(22,'Digital Marketing',2,3);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` int(11) DEFAULT NULL,
  `SubjectTeacherId` int(11) DEFAULT NULL,
  `Message` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `StudentId` (`StudentId`),
  KEY `SubjectTeacherId` (`SubjectTeacherId`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`StudentId`) REFERENCES `student` (`Id`),
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`SubjectTeacherId`) REFERENCES `subjectteacher` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Teacher'),(3,'Student');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schoolyear`
--

DROP TABLE IF EXISTS `schoolyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schoolyear` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NameYear` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `YearStart` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `YearEnd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SemesterName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolyear`
--

LOCK TABLES `schoolyear` WRITE;
/*!40000 ALTER TABLE `schoolyear` DISABLE KEYS */;
INSERT INTO `schoolyear` VALUES (1,'2021-2022','2021-09-01','2022-01-30','Học kỳ 1'),(2,'2021-2022','2022-01-01','2022-06-31','Học kỳ 2'),(3,'2023-2024','2022-09-01','2023-01-28','Học kỳ 1'),(4,'2023-2024','2023-01-01','2023-06-31','Học kỳ 2');
/*!40000 ALTER TABLE `schoolyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SubjectTeacherID` int(11) DEFAULT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `SchoolYearId` int(11) DEFAULT NULL,
  `ScoreValue` float DEFAULT NULL,
  `ScoreType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IsDraft` tinyint(1) DEFAULT NULL,
  `IsLocked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SubjectTeacherID` (`SubjectTeacherID`),
  KEY `StudentID` (`StudentID`),
  KEY `SchoolYearId` (`SchoolYearId`),
  KEY `ScoreType` (`ScoreType`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`SubjectTeacherID`) REFERENCES `subjectteacher` (`Id`),
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `student` (`Id`),
  CONSTRAINT `score_ibfk_3` FOREIGN KEY (`SchoolYearId`) REFERENCES `schoolyear` (`Id`),
  CONSTRAINT `score_ibfk_4` FOREIGN KEY (`ScoreType`) REFERENCES `typescore` (`ScoreType`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,1,1,1,8.5,'Giữa kỳ',0,1),(2,1,1,1,9,'Cuối kỳ',0,1),(3,1,2,1,7.2,'Giữa kỳ',0,1),(4,1,2,1,8.3,'Cuối kỳ',0,1),(5,1,3,1,9.5,'Giữa kỳ',0,1),(6,1,3,1,7.7,'Cuối kỳ',0,1),(7,1,4,1,6.8,'Giữa kỳ',0,1),(8,1,4,1,9.2,'Cuối kỳ',0,1),(9,1,5,1,8.7,'Giữa kỳ',0,1),(10,1,5,1,7.5,'Cuối kỳ',0,1),(11,2,1,1,9.5,'Giữa kỳ',0,1),(12,2,1,1,7.7,'Cuối kỳ',0,1),(13,2,2,1,6.8,'Giữa kỳ',0,1),(14,2,2,1,9.2,'Cuối kỳ',0,1),(15,2,3,1,8.7,'Giữa kỳ',0,1),(16,2,3,1,7.5,'Cuối kỳ',0,1),(17,2,4,1,9.3,'Giữa kỳ',0,1),(18,2,4,1,7,'Cuối kỳ',0,1),(19,2,5,1,7.8,'Giữa kỳ',0,1),(20,2,5,1,8.4,'Cuối kỳ',0,1),(21,3,1,1,9.7,'Giữa kỳ',0,1),(22,3,1,1,6,'Cuối kỳ',0,1),(23,3,2,1,8.1,'Giữa kỳ',0,1),(24,3,2,1,8.8,'Cuối kỳ',0,1),(25,3,3,1,9.6,'Giữa kỳ',0,1),(26,3,3,1,7.3,'Cuối kỳ',0,1),(27,3,4,1,8,'Giữa kỳ',0,1),(28,3,4,1,6.5,'Cuối kỳ',0,1),(29,3,5,1,9.4,'Giữa kỳ',0,1),(30,3,5,1,7.9,'Cuối kỳ',0,1),(31,4,1,1,8.6,'Giữa kỳ',0,1),(32,4,1,1,7.4,'Cuối kỳ',0,1),(33,4,2,1,9.2,'Giữa kỳ',0,1),(34,4,2,1,6.7,'Cuối kỳ',0,1),(35,4,3,1,8.9,'Giữa kỳ',0,1),(36,4,3,1,7.8,'Cuối kỳ',0,1),(37,4,4,1,9.1,'Giữa kỳ',0,1),(38,4,4,1,7.2,'Cuối kỳ',0,1),(39,4,5,1,8.5,'Giữa kỳ',0,1),(40,4,5,1,6.9,'Cuối kỳ',0,1),(41,5,1,1,7.2,'Giữa kỳ',0,1),(42,5,1,1,8.9,'Cuối kỳ',0,1),(43,5,2,1,8.5,'Giữa kỳ',0,1),(44,5,2,1,9.6,'Cuối kỳ',0,1),(45,5,3,1,6.7,'Giữa kỳ',0,1),(46,5,3,1,7.8,'Cuối kỳ',0,1),(47,5,4,1,9,'Giữa kỳ',0,1),(48,5,4,1,6.5,'Cuối kỳ',0,1),(49,5,5,1,8.2,'Giữa kỳ',0,1),(50,5,5,1,9.3,'Cuối kỳ',0,1),(51,6,1,2,8.4,'Giữa kỳ',0,1),(52,6,1,2,7.8,'Cuối kỳ',0,1),(53,1,21,1,8.5,'Giữa kỳ',0,1),(54,1,21,1,9,'Cuối kỳ',0,1),(55,1,22,1,7.2,'Giữa kỳ',0,1),(56,1,22,1,8.3,'Cuối kỳ',0,1),(57,1,23,1,9.5,'Giữa kỳ',0,1),(58,1,23,1,7.7,'Cuối kỳ',0,1),(59,1,24,1,6.8,'Giữa kỳ',0,1),(60,1,24,1,9.2,'Cuối kỳ',0,1),(61,1,25,1,8.7,'Giữa kỳ',0,1),(62,1,25,1,7.5,'Cuối kỳ',0,1),(63,2,26,1,9.3,'Giữa kỳ',0,1),(64,2,26,1,7,'Cuối kỳ',0,1),(65,2,27,1,7.8,'Giữa kỳ',0,1),(66,2,27,1,8.4,'Cuối kỳ',0,1),(67,2,28,1,9.7,'Giữa kỳ',0,1),(68,2,28,1,6,'Cuối kỳ',0,1),(69,2,29,1,8.1,'Giữa kỳ',0,1),(70,2,29,1,9.9,'Cuối kỳ',0,1),(71,2,30,1,7.4,'Giữa kỳ',0,1),(72,2,30,1,8.8,'Cuối kỳ',0,1),(73,3,21,1,8,'Giữa kỳ',0,1),(74,3,21,1,7.2,'Cuối kỳ',0,1),(75,3,22,1,9.2,'Giữa kỳ',0,1),(76,3,22,1,8.5,'Cuối kỳ',0,1),(77,3,23,1,7.5,'Giữa kỳ',0,1),(78,3,23,1,9.3,'Cuối kỳ',0,1),(79,3,24,1,6.9,'Giữa kỳ',0,1),(80,3,24,1,8.7,'Cuối kỳ',0,1),(81,3,25,1,9.8,'Giữa kỳ',0,1),(82,3,25,1,7,'Cuối kỳ',0,1),(83,6,2,2,8.5,'Giữa kỳ',0,1),(84,6,2,2,9,'Cuối kỳ',0,1),(85,6,3,2,7.2,'Giữa kỳ',0,1),(86,6,3,2,8.3,'Cuối kỳ',0,1),(87,6,4,2,9.5,'Giữa kỳ',0,1),(88,6,4,2,7.7,'Cuối kỳ',0,1),(89,6,5,2,6.8,'Giữa kỳ',0,1),(90,6,5,2,9.2,'Cuối kỳ',0,1),(91,6,6,2,8.7,'Giữa kỳ',0,1),(92,6,6,2,7.5,'Cuối kỳ',0,1),(93,7,2,2,9.3,'Giữa kỳ',0,1),(94,7,2,2,7,'Cuối kỳ',0,1),(95,7,3,2,7.8,'Giữa kỳ',0,1),(96,7,3,2,8.4,'Cuối kỳ',0,1),(97,7,4,2,9.7,'Giữa kỳ',0,1),(98,7,4,2,6,'Cuối kỳ',0,1),(99,7,5,2,8.1,'Giữa kỳ',0,1),(100,7,5,2,9.9,'Cuối kỳ',0,1),(101,7,6,2,7.4,'Giữa kỳ',0,1),(102,7,6,2,8.8,'Cuối kỳ',0,1),(103,8,2,2,8,'Giữa kỳ',0,1),(104,8,2,2,7.2,'Cuối kỳ',0,1),(105,8,3,2,9.2,'Giữa kỳ',0,1),(106,8,3,2,8.5,'Cuối kỳ',0,1),(107,8,4,2,7.5,'Giữa kỳ',0,1),(108,8,4,2,9.3,'Cuối kỳ',0,1),(109,8,5,2,6.9,'Giữa kỳ',0,1),(110,8,5,2,8.7,'Cuối kỳ',0,1),(111,8,6,2,9.8,'Giữa kỳ',0,1),(112,8,6,2,7,'Cuối kỳ',0,1),(113,9,2,2,8.8,'Giữa kỳ',0,1),(114,9,2,2,7.5,'Cuối kỳ',0,1),(115,9,3,2,7.3,'Giữa kỳ',0,1),(116,9,3,2,8.9,'Cuối kỳ',0,1),(117,9,4,2,9.6,'Giữa kỳ',0,1),(118,9,4,2,6.2,'Cuối kỳ',0,1),(119,9,5,2,8.5,'Giữa kỳ',0,1),(120,9,5,2,9.7,'Cuối kỳ',0,1),(121,9,6,2,7.7,'Giữa kỳ',0,1),(122,9,6,2,8.4,'Cuối kỳ',0,1),(123,4,2,3,9,'Giữa kỳ',0,1),(124,4,2,3,9,'Cuối kỳ',0,1),(126,4,2,3,10,'Bonus3',0,1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FirstName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LastName` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT NULL,
  `IdentifyCard` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Hometown` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ClassId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `StudentCode` (`StudentCode`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `Phone_UNIQUE` (`Phone`),
  KEY `ClassId` (`ClassId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`ClassId`) REFERENCES `class` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'2051052010','Nguyễn','Thị Ánh',0,'123456789','Hà Nội','2002-01-15','ntanh10@dh.edu.com','0351234567','Active',1),(2,'2051052014','Trần','Văn Bình',1,'987654321','Hồ Chí Minh','2002-05-20','tvbinh@dh.edu.com','0382393678','Active',1),(3,'2051052020','Phạm','Thị Cúc',0,'456789123','Đà Nẵng','2002-09-10','ptcuc@dh.edu.com','0983163729','Active',1),(4,'2051052024','Lê','Thị Dung',1,'789123456','Hải Phòng','2002-11-30','ltdung@dh.edu.com','0354567890','Active',1),(5,'2051052026','Võ','Văn Dần',0,'654321987','Cần Thơ','2002-07-25','vvandan@dh.edu.com','0385678901','Active',1),(6,'2051052031','Nguyễn','Thị Hương',0,'135792468','Hồ Chí Minh','2002-03-15','nthuong31@dh.edu.com','0986789012','Active',1),(7,'2051052033','Trần','Văn Giang',1,'864209753','Hồ Chí Minh','2002-05-20','tvgiang@dh.edu.com','0381235123','Active',1),(8,'2051052029','Phạm','Thị Hà',0,'975310864','Đà Nẵng','2002-09-10','ptha@dh.edu.com','0358901789','Active',1),(9,'2051052041','Lê','Thị Mai',1,'864209753','Long An','2002-11-30','ltmai@dh.edu.com','0382345678','Active',1),(10,'2051052035','Võ','Văn Khiêm',0,'135792468','Cần Thơ','2002-07-25','vvankhiem@dh.edu.com','0354567123','Active',1),(11,'2051052009','Nguyễn','Thị An',0,'123456789','Hà Nội','2002-01-15','ntan@dh.edu.com','0357890123','Active',1),(12,'2051052012','Trần','Văn Bắc',1,'987654321','Hồ Chí Minh','2002-05-20','tvbac@dh.edu.com','0388901234','Active',1),(13,'2051052028','Phạm','Thị Đào',0,'456789123','Đà Nẵng','2002-09-10','ptdao@dh.edu.com','0350123456','Active',1),(14,'2051052088','Lê','Thị Trang',1,'789123456','Hải Phòng','2002-11-30','lttrang88@dh.edu.com','0989012345','Active',1),(15,'2051052090','Võ','Văn Thường',0,'654321987','Cần Thơ','2002-07-25','vvthuong@dh.edu.com','0381234567','Active',1),(16,'2051052030','Nguyễn','Thị Hào',0,'135792468','Hồ Chí Minh','2002-03-15','nthao@dh.edu.com','0352345679','Active',1),(17,'2051052032','Trần','Văn Hùng',1,'864209753','Hồ Chí Minh','2002-05-20','tvhung32@dh.edu.com','0389011678','Active',1),(18,'2051052027','Phạm','Thị Hằng',0,'975310864','Đà Nẵng','2002-09-10','pthang@dh.edu.com','0986789567','Active',1),(19,'2051052057','Lê','Thị Oanh',1,'864209753','Long An','2002-11-30','ltoanh57@dh.edu.com','0983456789','Active',1),(20,'2061052069','Võ','Văn Khánh',0,'135792468','Cần Thơ','2002-07-25','vvkhanh@dh.edu.com','0388902345','Active',1),(21,'2061052002','Nguyễn','Thị Anh',0,'123456789','Hà Nội','2002-01-15','ntanh02@dh.edu.com','0352678902','Active',3),(22,'2061052004','Trần','Văn Bảo',1,'987654321','Hồ Chí Minh','2002-05-20','tvbao@dh.edu.com','0980122789','Active',3),(23,'2061052008','Phạm','Thị Cẩm',0,'456789123','Đà Nẵng','2002-09-10','ptcam@dh.edu.com','0380123901','Active',3),(24,'2061052012','Lê','Thị Duyên',1,'789123456','Hải Phòng','2002-11-30','ltduyen@dh.edu.com','0982345678','Active',3),(25,'2061052016','Võ','Văn Em',0,'654321987','Cần Thơ','2002-07-25','vvem@dh.edu.com','0989013567','Active',3),(26,'2061052020','Nguyễn','Thị Gia',0,'135792468','Hồ Chí Minh','2002-03-15','ntgia@dh.edu.com','0389012346','Active',3),(27,'2061052024','Trần','Văn Hà',1,'864209753','Hồ Chí Minh','2002-05-20','tvha@dh.edu.com','0358902890','Active',3),(28,'2061052028','Phạm','Thị Hiền',0,'975310864','Đà Nẵng','2002-09-10','pthien@dh.edu.com','0982345012','Active',3),(29,'2061052032','Lê','Thị Lan',1,'864209753','Long An','2002-11-30','ltlan@dh.edu.com','0984567891','Active',3),(30,'2061052036','Võ','Văn Mạnh',0,'135792468','Cần Thơ','2002-07-25','vvm@dh.edu.com','0356784789','Active',3),(31,'2061052040','Nguyễn','Thị Ngọc',0,'123456789','Hà Nội','2002-01-15','ntngoc@dh.edu.com','0357890345','Active',3),(32,'2061052044','Trần','Văn Phát',1,'988654321','Hồ Chí Minh','2002-05-20','tvphat@dh.edu.com','0389013901','Active',3),(33,'2061052048','Phạm','Thị Quỳnh',0,'456789123','Đà Nẵng','2002-09-10','ptquynh@dh.edu.com','0356787123','Active',3),(34,'2061052052','Lê','Thị Sương',1,'789123456','Hải Phòng','2002-11-30','ltsuong@dh.edu.com','0355678902','Active',3),(35,'2061052056','Võ','Văn Tài',0,'654321987','Cần Thơ','2002-07-25','vvtai@dh.edu.com','0380125901','Active',3),(36,'2061052060','Nguyễn','Thị Uyên',0,'135792468','Hồ Chí Minh','2002-03-15','ntuyen@dh.edu.com','0388900456','Active',3),(37,'2061052064','Trần','Văn Vượng',1,'864209753','Hồ Chí Minh','2002-05-20','tvvuong@dh.edu.com','0980124012','Active',3),(38,'2061052068','Phạm','Thị Yến',0,'975310864','Đà Nẵng','2002-09-10','ptyen68@dh.edu.com','0388909345','Active',3),(39,'2061052072','Lê','Thị Trâm',1,'864209753','Long An','2002-11-30','lttram@dh.edu.com','0386789013','Active',3),(40,'2061052076','Võ','Văn Tâm',0,'135792468','Cần Thơ','2002-07-25','vvtam@dh.edu.com','0982346123','Active',3),(41,'2016052002','Nguyễn','Thị Huệ',0,'123456789','Hà Nội','2002-01-15','nthue1602@dh.edu.com','0989010567','Active',6),(42,'2016052004','Trần','Văn Minh',1,'987654321','Hồ Chí Minh','2002-05-20','tvminh@dh.edu.com','0351235123','Active',6),(43,'2016052008','Phạm','Thanh Hà',0,'456789123','Đà Nẵng','2002-09-10','pthha@dh.edu.com','0989011567','Active',6),(44,'2016052012','Lê','Thị Quỳnh',1,'789123456','Hải Phòng','2002-11-30','ltquynh@dh.edu.com','0987890124','Active',6),(45,'2016052016','Võ','Văn Sơn',0,'654321987','Cần Thơ','2002-07-25','vvson@dh.edu.com','0358907234','Active',6),(46,'2016052080','Nguyễn','Thị Ngân',0,'123456789','Hà Nội','2002-01-15','ntngan@dh.edu.com','0350121678','Active',6),(47,'2016052084','Trần','Văn Khải',1,'987654321','Hồ Chí Minh','2002-05-20','tvkhai@dh.edu.com','0382346234','Active',6),(48,'2016052088','Phạm','Thị Thu',0,'456789123','Đà Nẵng','2002-09-10','ptthu@dh.edu.com','0355678567','Active',6),(49,'2016052092','Lê','Thị Kim',1,'789123456','Hải Phòng','2002-11-30','ltkim@dh.edu.com','0358901235','Active',6),(50,'2016052096','Võ','Văn Lực',0,'654321987','Cần Thơ','2002-07-25','vvluc@dh.edu.com','0385678345','Active',6),(51,'2016052100','Nguyễn','Thị Hương',0,'123456789','Hà Nội','2002-01-15','nthuong00@dh.edu.com','0353674902','Active',6),(52,'2016052104','Trần','Văn Long',1,'987654321','Hồ Chí Minh','2002-05-20','tvlong@dh.edu.com','0983457345','Active',6),(53,'2016052108','Phạm','Thị Thuý',0,'456789123','Đà Nẵng','2002-09-10','ptthuy@dh.edu.com','0352534089','Active',6),(54,'2016052112','Lê','Thị Tâm',1,'789323456','Hải Phòng','2002-11-30','lttam@dh.edu.com','0351122334','Active',6),(55,'2016052116','Võ','Văn Đức',0,'654321987','Cần Thơ','2002-07-25','vvduc@dh.edu.com','0987899567','Active',6),(56,'2016052120','Nguyễn','Thị Hà',0,'123456789','Hà Nội','2002-01-15','ntha@dh.edu.com','0381232789','Active',6),(57,'2016052124','Trần','Văn Dũng',1,'987654321','Hồ Chí Minh','2002-05-20','tvdung@dh.edu.com','0354568456','Active',6),(58,'2016052128','Phạm','Thị Mai',0,'456789123','Đà Nẵng','2002-09-10','ptmai128@dh.edu.com','0388900890','Active',6),(59,'2016052132','Lê','Thị Trang',1,'789123456','Hải Phòng','2002-11-30','lttrang132@dh.edu.com','0385566778','Active',6),(60,'2016052136','Võ','Văn Tú',0,'654321987','Cần Thơ','2002-07-25','vvtu@dh.edu.com','0351231901','Active',6),(61,'2018052002','Nguyễn','Thị Huế',0,'123456789','Hà Nội','2002-01-15','nthue1802@dh.edu.com','0383456780','Active',8),(62,'2018052004','Trần','Văn Hùng',1,'987654321','Hồ Chí Minh','2002-05-20','tvhung04@dh.edu.com','0385679567','Active',8),(63,'2018052008','Phạm','Thị Lan',0,'456789123','Đà Nẵng','2002-09-10','ptlan@dh.edu.com','0980123123','Active',8),(64,'2018052012','Lê','Thị Ngọc',1,'789123456','Hải Phòng','2002-11-30','ltngoc@dh.edu.com','0983344556','Active',8),(65,'2018052016','Võ','Văn Nam',0,'654321987','Cần Thơ','2002-07-25','vvnam@dh.edu.com','0383453123','Active',8),(66,'2018052020','Nguyễn','Thị Phượng',0,'123456789','Hà Nội','2002-01-15','ntphuong@dh.edu.com','0982343890','Active',8),(67,'2018052024','Trần','Văn Quân',1,'987654321','Hồ Chí Minh','2002-05-20','tvquan@dh.edu.com','0986780678','Active',8),(68,'2018052028','Phạm','Thị Tú',0,'456789123','Đà Nẵng','2002-09-10','pttu@dh.edu.com','0351234345','Active',8),(69,'2018052032','Lê','Thị Hoa',1,'889123456','Hải Phòng','2002-11-30','lthoa@dh.edu.com','0352234709','Active',8),(70,'2018052036','Võ','Văn Đoàn',0,'654321987','Cần Thơ','2002-07-25','vvdoan@dh.edu.com','0352345123','Active',8),(71,'2018052040','Nguyễn','Thị Tuyết',0,'123556789','Hà Nội','2002-01-15','nttuyet@dh.edu.com','0352344901','Active',8),(72,'2018052044','Trần','Văn Thành',1,'987654321','Hồ Chí Minh','2002-05-20','tvthanh@dh.edu.com','0357891789','Active',8),(73,'2018052048','Phạm','Thị Hường',0,'456789123','Đà Nẵng','2002-09-10','pthuong@dh.edu.com','0382345567','Active',8),(74,'2018052052','Lê','Thị Loan',1,'789123456','Hải Phòng','2002-11-30','ltloan@dh.edu.com','0383232769','Active',8),(75,'2018052056','Võ','Văn Phú',0,'654321987','Cần Thơ','2002-07-25','vvphu@dh.edu.com','0986784345','Active',8),(76,'2018052060','Nguyễn','Thị Hồng',0,'123456789','Hà Nội','2002-01-15','nthong@dh.edu.com','0383455012','Active',8),(77,'2041052002','Nguyễn','Thị Hạnh',0,'223456789','Hà Nội','2002-01-15','nthanh@dh.edu.com','0984566123','Active',12),(78,'2041052004','Trần','Văn Khánh',1,'987654321','Hồ Chí Minh','2002-05-20','tvkhanh@dh.edu.com','0388902890','Active',12),(79,'2041052008','Phạm','Thị Mai',0,'456789123','Đà Nẵng','2002-09-10','ptmai08@dh.edu.com','0358901567','Active',12),(80,'2041052012','Lê','Thị Oanh',1,'789153456','Hải Phòng','2002-11-30','ltoanh12@dh.edu.com','0358901345','Active',12),(81,'2041052016','Võ','Văn Phương',0,'654321987','Cần Thơ','2002-07-25','vvphuong@dh.edu.com','0359014567','Active',12),(82,'2041052020','Nguyễn','Thị Quyên',0,'123456789','Hà Nội','2002-01-15','ntquyen@dh.edu.com','0355677234','Active',12),(83,'2041052024','Trần','Văn Long',1,'997654321','Hồ Chí Minh','2002-05-20','tvrong@dh.edu.com','0989013901','Active',12),(84,'2041052028','Phạm','Thị Sáng',0,'456789123','Đà Nẵng','2002-09-10','ptsang@dh.edu.com','0354567901','Active',12),(85,'2041052032','Lê','Thị Trang',1,'789123456','Hải Phòng','2002-11-30','lttrang32@dh.edu.com','0380122567','Active',12),(86,'2041052036','Võ','Văn Uyên',0,'654321987','Cần Thơ','2002-07-25','vvuyen@dh.edu.com','0382345789','Active',12),(87,'2041052040','Nguyễn','Thị Vân',0,'123456789','Hà Nội','2002-01-15','ntvan@dh.edu.com','0386788345','Active',12),(88,'2041052044','Trần','Văn Xuân',1,'987654321','Hồ Chí Minh','2002-05-20','tvxuan@dh.edu.com','0982346234','Active',12),(89,'2041052048','Phạm','Thị Yến',0,'456789123','Đà Nẵng','2002-09-10','ptyen48@dh.edu.com','0986789345','Active',12),(90,'2041052052','Lê','Thị Hoàng',1,'789123456','Hải Phòng','2002-11-30','lthoang@dh.edu.com','0982343789','Active',12),(91,'2041052056','Võ','Văn Trọng',0,'654321987','Cần Thơ','2002-07-25','vvtrong@dh.edu.com','0384567345','Active',12),(92,'2041052060','Nguyễn','Thị Hiếu',0,'123456789','Hà Nội','2002-01-15','nthieu@dh.edu.com','0987899456','Active',12),(93,'2041052064','Trần','Văn Hải',1,'927754321','Hồ Chí Minh','2002-05-20','tvhai@dh.edu.com','0350124012','Active',12),(94,'2041052068','Phạm','Thị Linh',0,'456789123','Đà Nẵng','2002-09-10','ptlinh@dh.edu.com','0385678123','Active',12),(95,'2041052072','Lê','Thị Trà',1,'789123456','Hải Phòng','2002-11-30','lttra@dh.edu.com','0356785901','Active',12),(96,'2041052076','Võ','Văn Tuấn',0,'654321987','Cần Thơ','2002-07-25','vvtuan@dh.edu.com','0984567123','Active',12),(97,'2041052080','Nguyễn','Thị Lâm',0,'123456689','Hà Nội','2002-01-15','ntlam@dh.edu.com','0358900567','Active',12);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentsubjectteacher`
--

DROP TABLE IF EXISTS `studentsubjectteacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentsubjectteacher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` int(11) DEFAULT NULL,
  `SubjectTeacherId` int(11) DEFAULT NULL,
  `SchoolYearId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `StudentId` (`StudentId`),
  KEY `SubjectTeacherId` (`SubjectTeacherId`),
  KEY `SchoolYearId` (`SchoolYearId`),
  CONSTRAINT `studentsubjectteacher_ibfk_1` FOREIGN KEY (`StudentId`) REFERENCES `student` (`Id`),
  CONSTRAINT `studentsubjectteacher_ibfk_2` FOREIGN KEY (`SubjectTeacherId`) REFERENCES `subjectteacher` (`Id`),
  CONSTRAINT `studentsubjectteacher_ibfk_3` FOREIGN KEY (`SchoolYearId`) REFERENCES `schoolyear` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentsubjectteacher`
--

LOCK TABLES `studentsubjectteacher` WRITE;
/*!40000 ALTER TABLE `studentsubjectteacher` DISABLE KEYS */;
INSERT INTO `studentsubjectteacher` VALUES (1,1,1,3),(2,1,2,3),(3,2,3,3),(4,2,4,3),(5,3,5,3),(6,3,6,3),(7,4,7,3),(8,4,8,3),(9,5,9,3),(10,5,1,3),(11,1,6,3),(12,2,6,3),(13,3,6,3),(14,4,6,2),(15,5,6,2),(16,6,6,2),(17,2,7,2),(18,3,7,2),(19,4,7,2),(20,5,7,2),(21,6,7,2),(22,2,8,2),(23,3,8,2),(24,4,8,2),(25,5,8,2),(26,6,8,2),(27,2,9,2),(28,3,9,2),(29,4,9,2),(30,5,9,2),(31,6,9,2);
/*!40000 ALTER TABLE `studentsubjectteacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SubjectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Credits` int(11) DEFAULT NULL,
  `NumberOfLessons` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Nhập môn lập trình',3,45),(2,'Cấu trúc dữ liệu và giải thuật',4,60),(3,'Quản trị hệ cơ sở dữ liệu',3,45),(4,'Mạng máy tính',3,45),(5,'Lập trình hướng đội tượng',4,60),(6,'Lập trình Web',3,45),(7,'Công nghệ phần mềm',3,45),(8,'Hệ điều hành',3,45),(9,'Kiến trúc máy tính',3,45),(10,'Văn học Anh',3,45),(11,'Ngôn ngữ học',3,45),(12,'Viết và diễn thuyết',3,45),(13,'Tâm lý ngôn ngữ học',4,60),(14,'Giao tiếp đa văn hóa',3,45),(15,'Nghiên cứu phiên dịch',3,45),(16,'Ngữ âm và ngữ âm học tiếng Anh',3,45),(17,' Cú pháp và ngữ pháp tiếng An',3,45),(18,'Kinh tế quốc tế',4,60),(19,'Thương mại và tài chính toàn cầu',3,45),(20,'Các công ty đa quốc gia',3,45),(21,'Hệ thống kinh tế so sánh',3,45),(22,'Luật kinh doanh quốc tế',3,45),(23,'Quản lý tài chính',4,60),(24,'Phân tích đầu tư',3,45),(25,'Tài chính doanh nghiệp',3,45),(26,'Quản lý rủi ro',3,45),(27,'Báo cáo tài chính',3,45);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectteacher`
--

DROP TABLE IF EXISTS `subjectteacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectteacher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SubjectId` int(11) DEFAULT NULL,
  `TeacherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SubjectId` (`SubjectId`),
  KEY `TeacherId` (`TeacherId`),
  CONSTRAINT `subjectteacher_ibfk_1` FOREIGN KEY (`SubjectId`) REFERENCES `subject` (`Id`),
  CONSTRAINT `subjectteacher_ibfk_2` FOREIGN KEY (`TeacherId`) REFERENCES `teacher` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectteacher`
--

LOCK TABLES `subjectteacher` WRITE;
/*!40000 ALTER TABLE `subjectteacher` DISABLE KEYS */;
INSERT INTO `subjectteacher` VALUES (1,1,16),(2,2,16),(3,3,17),(4,4,1),(5,5,16),(6,6,16),(7,7,16),(8,8,11),(9,9,11),(10,10,5),(11,11,5),(12,12,5),(13,23,14),(14,24,14),(15,25,14);
/*!40000 ALTER TABLE `subjectteacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TeacherName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PhoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `DepartmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `DepartmentId` (`DepartmentId`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`DepartmentId`) REFERENCES `department` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Trần Thị Bảo Trâm','tranbaotram@gmail.com','123456789','Hồ Chí Minh',0,'1995-03-10',3),(2,'Nguyễn Văn Hưng','nguyenvanhung@gmail.com','987654321','Long An',1,'1988-08-15',2),(3,'Lê Thị Mỹ Linh','lethimylinh@gmail.com','456789123','Bình Dương',0,'1992-06-25',4),(4,'Phạm Minh Tuấn','phamminhtuan@gmail.com','789123456','Hồ Chí Minh',1,'1980-11-30',1),(5,'Vũ Thị Hương Giang','vuthihuonggiang@gmail.com','654321987','Cần Thơ',0,'1993-09-05',5),(6,'Nguyễn Thanh Trúc','nguyenthanhtruc@gmail.com','234567890','Bình Phước',0,'1987-05-15',3),(7,'Hoàng Minh Tú','hoangminhtu@gmail.com','345678901','Hồ Chí Minh',1,'1991-09-20',2),(8,'Nguyễn Hà Anh','nguyenhaanh@gmail.com','456789012','Đồng Nai',0,'1984-12-10',4),(9,'Phan Hồng Quang','phanhongquang@gmail.com','567890123','TP. HCM',1,'1982-03-25',1),(10,'Ngô Trần Bảo Ngọc','ngotrannbaongoc@gmail.com','678901234','Cần Thơ',0,'1990-07-10',5),(11,'Tran Minh Phương','tranminhphuong@gmail.com','789012345','Long An',1,'1986-02-15',3),(12,'Lê Đình Hiếu','ledinhhieu@gmail.com','890123456','Hồ Chí Minh',1,'1994-06-20',2),(13,'Nguyễn Mai Anh','nguyenmaianh@gmail.com','901234567','Đồng Tháp',0,'1998-11-30',4),(14,'Võ Tuấn Khải','votuankhai@gmail.com','012345678','Hồ Chí Minh',1,'1989-09-05',1),(15,'Vũ Thanh Tâm','lythanhtam@gmail.com','123456789','Cần Thơ',0,'1993-04-10',5),(16,'Dương Hữu Thành','dhthanh@gmail.com','0383160779','Hồ Chí Minh',1,'1993-04-12',3),(17,'Nguyễn Thị Phương Trang','ntphuongtrang@gmail.com','0383160679','Hồ Chí Minh',0,'1993-05-12',3);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainingtype`
--

DROP TABLE IF EXISTS `trainingtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainingtype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TrainingTypeName` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainingtype`
--

LOCK TABLES `trainingtype` WRITE;
/*!40000 ALTER TABLE `trainingtype` DISABLE KEYS */;
INSERT INTO `trainingtype` VALUES (1,'Đại trà'),(2,'Chất lượng cao'),(3,'Đào tạo từ xa');
/*!40000 ALTER TABLE `trainingtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typescore`
--

DROP TABLE IF EXISTS `typescore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typescore` (
  `ScoreType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ScoreType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typescore`
--

LOCK TABLES `typescore` WRITE;
/*!40000 ALTER TABLE `typescore` DISABLE KEYS */;
INSERT INTO `typescore` VALUES ('Bonu4'),('Bonus3'),('Bonus4'),('Cuối kỳ'),('Giữa kỳ');
/*!40000 ALTER TABLE `typescore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT NULL,
  `IdentifyCard` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Hometown` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Active` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Phone_UNIQUE` (`Phone`),
  UNIQUE KEY `IdentifyCard_UNIQUE` (`IdentifyCard`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `RoleID` (`RoleID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Dương Hữu Thành',1,'0383160679','Hồ Chí Minh','1993-04-12','0123456789','dthanh','123456','profile_image_a.jpg','Active',1),(2,'Vũ Thanh Tâm',1,'987654321','Hồ Chí Minh','1985-05-20','0987654321','vthtam','123456','profile_image_b.jpg','Active',2),(3,'Nguyễn Quốc Kỳ',1,'035789123','Bình Định','2002-09-10','0345678912','nquocky','07062002','profile_image_c.jpg','Active',3),(4,'Võ Hữu Nghĩa',1,'036789123','Gia Lai','2002-01-10','0345673512','vhuunghia','11012002','profile_image_d.jpg','Active',3),(5,'Thị Ánh',0,'123456789','Hà Nội','2002-01-15','0351234567','tranbaotram@gmail.com','$2a$10$1BxUTHcNgKCQdtvuhMiH1u.y2fyT9.DPCjxapTG8WCUpiWBzbsheK','https://res.cloudinary.com/dup9wlfhh/image/upload/v1694341343/so2bxlsdbdgpmk03esyq.jpg','Active',2);
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

-- Dump completed on 2023-09-10 17:28:09
