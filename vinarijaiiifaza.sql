/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.21-MariaDB : Database - vinarijaiiifaza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vinarijaiiifaza` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `vinarijaiiifaza`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `AdministratorID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `administrator` */

insert  into `administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) values 
(1,'Milena','Maric','milena','milena123'),
(2,'Darko','Petronijevic','darko','darko123');

/*Table structure for table `faktura` */

DROP TABLE IF EXISTS `faktura`;

CREATE TABLE `faktura` (
  `FakturaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `DatumVreme` datetime NOT NULL,
  `CenaBezPDV` decimal(10,2) NOT NULL,
  `PoreskaStopa` decimal(10,2) NOT NULL,
  `CenaSaPDV` decimal(10,2) NOT NULL,
  `KupacID` bigint(10) unsigned NOT NULL,
  `AdministratorID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`FakturaID`),
  KEY `fk2_kupac_id` (`KupacID`),
  KEY `fk2_admin_id` (`AdministratorID`),
  CONSTRAINT `fk2_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`),
  CONSTRAINT `fk2_kupac_id` FOREIGN KEY (`KupacID`) REFERENCES `kupac` (`KupacID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `faktura` */

/*Table structure for table `figurica` */

DROP TABLE IF EXISTS `figurica`;

CREATE TABLE `figurica` (
  `figuricaID` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) NOT NULL,
  `boja` varchar(30) NOT NULL,
  PRIMARY KEY (`figuricaID`,`naziv`,`boja`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `figurica` */

insert  into `figurica`(`figuricaID`,`naziv`,`boja`) values 
(1,'Naziv1','Boja1'),
(3,'Naziv333','Boja3333'),
(5,'Naziv3','Boja3');

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `KupacID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `PIB` varchar(30) NOT NULL,
  `NazivKupca` varchar(30) NOT NULL,
  `Adresa` varchar(50) NOT NULL,
  `BrojTelefona` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  PRIMARY KEY (`KupacID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kupac` */

insert  into `kupac`(`KupacID`,`PIB`,`NazivKupca`,`Adresa`,`BrojTelefona`,`Email`) values 
(1,'372837287','De Vinos','Kaleniceva 3','0631231234','devinos@gmail.com'),
(2,'283728192','Invej','Aleksandra Dupceka 14','0654645434','invej@gmail.com'),
(3,'182736859','Restoran Plava Ruza','Dunavska Obala bb','0641235153','plavaruza@gmail.com');

/*Table structure for table `stavkafakture` */

DROP TABLE IF EXISTS `stavkafakture`;

CREATE TABLE `stavkafakture` (
  `FakturaID` bigint(10) unsigned NOT NULL,
  `RbStavke` int(11) NOT NULL,
  `Kolicina` decimal(10,2) unsigned NOT NULL,
  `CenaStavke` decimal(10,2) unsigned NOT NULL,
  `VinarijaID` bigint(10) unsigned NOT NULL,
  `RbVinaVinarije` int(7) NOT NULL,
  PRIMARY KEY (`FakturaID`,`RbStavke`),
  KEY `fk2_vino_id` (`VinarijaID`,`RbVinaVinarije`),
  CONSTRAINT `fk2_vino_id` FOREIGN KEY (`VinarijaID`, `RbVinaVinarije`) REFERENCES `vino` (`VinarijaID`, `RbVinaVinarije`),
  CONSTRAINT `fk_faktura_id` FOREIGN KEY (`FakturaID`) REFERENCES `faktura` (`FakturaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkafakture` */

/*Table structure for table `vinarija` */

DROP TABLE IF EXISTS `vinarija`;

CREATE TABLE `vinarija` (
  `VinarijaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `NazivVinarije` varchar(50) NOT NULL,
  `Adresa` varchar(50) NOT NULL,
  PRIMARY KEY (`VinarijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `vinarija` */

insert  into `vinarija`(`VinarijaID`,`NazivVinarije`,`Adresa`) values 
(3,'Kovacevic','Milutina Milankovica 132'),
(5,'Lastar','Sekuric bb'),
(6,'Erdevik','Milin do 16');

/*Table structure for table `vino` */

DROP TABLE IF EXISTS `vino`;

CREATE TABLE `vino` (
  `VinarijaID` bigint(10) unsigned NOT NULL,
  `RbVinaVinarije` int(7) NOT NULL,
  `NazivVina` varchar(50) NOT NULL,
  `Mililitraza` decimal(10,2) NOT NULL,
  `Opis` varchar(200) NOT NULL,
  `Cena` decimal(10,2) NOT NULL,
  `VrstaVinaID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`VinarijaID`,`RbVinaVinarije`),
  KEY `fk2_vrstaVina_id` (`VrstaVinaID`),
  CONSTRAINT `fk2_vrstaVina_id` FOREIGN KEY (`VrstaVinaID`) REFERENCES `vrstavina` (`VrstaVinaID`),
  CONSTRAINT `fk_vinarija_id` FOREIGN KEY (`VinarijaID`) REFERENCES `vinarija` (`VinarijaID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `vino` */

insert  into `vino`(`VinarijaID`,`RbVinaVinarije`,`NazivVina`,`Mililitraza`,`Opis`,`Cena`,`VrstaVinaID`) values 
(3,1,'Pinot Grigio',750.00,'Jedan od najkvalitetnijih Pinot Grigia.',996.00,1),
(3,2,'Credo Roze',1000.00,'Jedan od najkvalitetnijih Credo Rozea.',890.00,3),
(5,1,'Pinot Noir',750.00,'Sjajno',1600.00,1),
(5,2,'Chardonnay',750.00,'Odlicno',1400.00,1),
(6,1,'Grand Trianon',750.00,'Shiraz, Cabernet Sauvignon, Merlot',2400.00,1),
(6,2,'Omnibus Lector',750.00,'Chardonnay',6000.00,2);

/*Table structure for table `vrstavina` */

DROP TABLE IF EXISTS `vrstavina`;

CREATE TABLE `vrstavina` (
  `VrstaVinaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `NazivVrsteVina` varchar(50) NOT NULL,
  PRIMARY KEY (`VrstaVinaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `vrstavina` */

insert  into `vrstavina`(`VrstaVinaID`,`NazivVrsteVina`) values 
(1,'Crveno vino'),
(2,'Belo vino'),
(3,'Roze');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
