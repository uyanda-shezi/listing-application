-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table listingdb.applicants: ~2 rows (approximately)
/*!40000 ALTER TABLE `applicants` DISABLE KEYS */;
INSERT INTO `applicants` (`email`, `name`, `surname`, `phone`) VALUES
	('123@w.com', 'tim', 'tam', '2'),
	('yyy@33.com', 'kyle', 'bohn', '34567');
/*!40000 ALTER TABLE `applicants` ENABLE KEYS */;

-- Dumping data for table listingdb.applications: ~0 rows (approximately)
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` (`applicationid`, `applicant`, `listingid`) VALUES
	(1, '123@w.com', 1);
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;

-- Dumping data for table listingdb.companies: ~1 rows (approximately)
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` (`companyid`, `name`, `address`) VALUES
	(1, 'vol', '12 eddna');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;

-- Dumping data for table listingdb.listings: ~3 rows (approximately)
/*!40000 ALTER TABLE `listings` DISABLE KEYS */;
INSERT INTO `listings` (`listingid`, `companyid`, `roleid`, `closingdate`) VALUES
	(1, 1, 1, '2022-06-05'),
	(3, 1, 1, '2022-09-09'),
	(5, 1, 1, '2022-09-09');
/*!40000 ALTER TABLE `listings` ENABLE KEYS */;

-- Dumping data for table listingdb.roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`roleid`, `companyid`, `title`, `description`) VALUES
	(1, 1, 'dev', 'developer');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
