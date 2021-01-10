-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionbancaire
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `Id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(220) NOT NULL,
  `Prenom` varchar(220) NOT NULL,
  `Id_user` int(11) NOT NULL,
  PRIMARY KEY (`Id_admin`),
  KEY `fk_admin_Utilisateur` (`Id_user`),
  CONSTRAINT `fk_admin_Utilisateur` FOREIGN KEY (`Id_user`) REFERENCES `users_comptes` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agence`
--

DROP TABLE IF EXISTS `agence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agence` (
  `id_Agence` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(220) NOT NULL,
  `adresse` varchar(220) DEFAULT NULL,
  `telephone` int(220) DEFAULT NULL,
  PRIMARY KEY (`id_Agence`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agence`
--

LOCK TABLES `agence` WRITE;
/*!40000 ALTER TABLE `agence` DISABLE KEYS */;
INSERT INTO `agence` VALUES (1,'agence hay hassani 1','bd oued sbou',52234567);
/*!40000 ALTER TABLE `agence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `Id_Client` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(220) NOT NULL,
  `Prenom` varchar(220) NOT NULL,
  `Date_Naissance` date DEFAULT NULL,
  `telephone` varchar(220) DEFAULT NULL,
  `Email` varchar(220) DEFAULT NULL,
  `Id_agence` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  PRIMARY KEY (`Id_Client`),
  KEY `fk_Client_agence` (`Id_agence`),
  KEY `FKe7d8v59gengrll6pfld5xas4u` (`Id_user`),
  CONSTRAINT `FK3u9sksv24tkpd0wjmu7ujntsp` FOREIGN KEY (`Id_agence`) REFERENCES `agence` (`id_Agence`),
  CONSTRAINT `FKe7d8v59gengrll6pfld5xas4u` FOREIGN KEY (`Id_user`) REFERENCES `users_comptes` (`id_user`),
  CONSTRAINT `fk_Client_Utilisateur` FOREIGN KEY (`Id_user`) REFERENCES `users_comptes` (`id_user`),
  CONSTRAINT `fk_Client_agence` FOREIGN KEY (`Id_agence`) REFERENCES `agence` (`id_Agence`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Rouessi','Mohcine','1995-08-04','063109190','rouessi.mocine@gmail.com',1,1),(2,'Abdane','Rachid','1987-09-24','063101290','rachid.abdane@gmail.com',1,26),(3,'Hibat-ALLah','Mohamed','1997-09-24','0631891230','Hibat.mohamed@gmail.com',1,24),(4,'Rafik','rachid','1987-08-09','212345678','rachid.rafik@gmail.com',1,25),(6,'Yassine','bounou','1994-09-09','0631212121','bounou@gmail.com',1,10),(10,'bourass','Mohamed','1994-09-09','06312121212','BAROUSSA@gmail.com',1,15),(11,'Benchikha','Manal','1995-09-08','063123456','manal.ench@gmail.com',1,16),(12,'Sakhry','Hamza','1999-03-29','061233411','hamza.sakhry@gmail.com',1,17),(13,'Sakhry','Hamza','1999-09-09','061233411','hamza.sakhry@gmail.com',1,18),(14,'Camara','Sape','1993-08-06','056231323','rouzd@gmail.com',1,22),(15,'client','test','1999-03-29','0680081631','test@test.com',1,27),(16,'Dirar','Nabil','1993-08-07','0680081631','dirar.nabil@gmail.com',1,28);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compte_bancaire`
--

DROP TABLE IF EXISTS `compte_bancaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compte_bancaire` (
  `Id_Compte` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation` date DEFAULT NULL,
  `Solde` double(8,3) NOT NULL,
  `libelle` varchar(220) DEFAULT NULL,
  `Id_Client` int(11) NOT NULL,
  PRIMARY KEY (`Id_Compte`),
  KEY `FKreg6ro2mw7c2u2dnmifsod5sm` (`Id_Client`),
  CONSTRAINT `FKreg6ro2mw7c2u2dnmifsod5sm` FOREIGN KEY (`Id_Client`) REFERENCES `client` (`Id_Client`),
  CONSTRAINT `fk_compte_client` FOREIGN KEY (`Id_Client`) REFERENCES `client` (`Id_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compte_bancaire`
--

LOCK TABLES `compte_bancaire` WRITE;
/*!40000 ALTER TABLE `compte_bancaire` DISABLE KEYS */;
INSERT INTO `compte_bancaire` VALUES (1,'2020-04-08',4000.900,'compte personnel',1),(2,'2017-05-23',20000.900,'compte personnel',2),(6,'2020-03-31',14399.000,'compte personnel',10),(7,'2020-03-31',12000.000,'compte personnel',11),(8,'2020-03-31',1400.000,'compte famille',2),(9,'2020-03-24',3000.000,'Compte personnel',3),(10,'2020-03-24',3000.000,'Compte personnel',3),(11,'2020-03-24',23134.000,'Compte personnel',3),(12,'2020-03-24',24334.000,'Compte personnel',3),(13,'2020-03-31',1000.000,'Compte Famille',1),(14,'2020-03-31',1299.000,'Compte Professionnel',1),(16,'2020-03-27',1200.000,'Compte Famille',12),(17,'2020-03-31',12000.000,'compte personnel',10),(18,'2020-03-31',12000.000,'compte personnel',10),(19,'2020-03-31',12000.000,'compte personnel',11),(20,'2020-04-02',1100.000,'Compte d\'épargne',12),(22,'2020-04-08',100.000,'Compte Professionnel',14),(23,'2020-04-19',40487.000,'Compte Professionnel',15),(24,'2020-04-23',100.000,'Compte personnel',16);
/*!40000 ALTER TABLE `compte_bancaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit` (
  `Id_Credit` int(11) NOT NULL AUTO_INCREMENT,
  `Date_credit` date NOT NULL,
  `Montant_octroye` double(8,3) NOT NULL,
  `Montant_restant` double(8,3) NOT NULL,
  `Id_client` int(11) NOT NULL,
  PRIMARY KEY (`Id_Credit`),
  KEY `FKsempbuu4yfncpwq7qw44d49qv` (`Id_client`),
  CONSTRAINT `FKsempbuu4yfncpwq7qw44d49qv` FOREIGN KEY (`Id_client`) REFERENCES `client` (`Id_Client`),
  CONSTRAINT `fk_credit_client` FOREIGN KEY (`Id_client`) REFERENCES `client` (`Id_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
INSERT INTO `credit` VALUES (1,'2019-03-24',3000.000,0.000,1),(2,'2019-03-24',10000.000,3000.000,1),(6,'2020-04-02',30000.000,28800.000,1),(7,'2020-04-11',3500.000,3500.000,11),(8,'2020-04-19',14000.000,14000.000,1),(9,'2020-04-19',12000.000,12000.000,15),(10,'2020-04-20',12000.000,12000.000,10);
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_historique`
--

DROP TABLE IF EXISTS `credit_historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_historique` (
  `id_operation` int(11) NOT NULL AUTO_INCREMENT,
  `date_operation` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `id_credit` int(11) DEFAULT NULL,
  `montant` double NOT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `FK4dby3he7q80ffcqcbg913ht4m` (`id_credit`),
  CONSTRAINT `FK4dby3he7q80ffcqcbg913ht4m` FOREIGN KEY (`id_credit`) REFERENCES `credit` (`Id_Credit`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_historique`
--

LOCK TABLES `credit_historique` WRITE;
/*!40000 ALTER TABLE `credit_historique` DISABLE KEYS */;
INSERT INTO `credit_historique` VALUES (1,'2020-03-27','Traite Février',1,700);
/*!40000 ALTER TABLE `credit_historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demande_historique`
--

DROP TABLE IF EXISTS `demande_historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demande_historique` (
  `id_avancement` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_avancement` varchar(255) DEFAULT NULL,
  `etat_apres` varchar(255) DEFAULT NULL,
  `etat_avant` varchar(255) DEFAULT NULL,
  `id_demande` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_avancement`),
  KEY `FKog2sxwk5t4w872lwbcwdh4dfe` (`id_demande`),
  CONSTRAINT `FKog2sxwk5t4w872lwbcwdh4dfe` FOREIGN KEY (`id_demande`) REFERENCES `demandes_credit` (`id_demande`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demande_historique`
--

LOCK TABLES `demande_historique` WRITE;
/*!40000 ALTER TABLE `demande_historique` DISABLE KEYS */;
INSERT INTO `demande_historique` VALUES (5,'Redemander','2020-03-30','1','-1',4),(6,'Redemander','2020-03-30','1','1',4),(7,'Relancement de la demande','2020-03-31','1','-1',1),(8,'convocation pour entretien munis des justifs.','2020-03-31','2','1',1);
/*!40000 ALTER TABLE `demande_historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandes_credit`
--

DROP TABLE IF EXISTS `demandes_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demandes_credit` (
  `Id_demande` int(11) NOT NULL AUTO_INCREMENT,
  `Date_demande` date NOT NULL,
  `Montant_demande` double(8,3) NOT NULL,
  `Etat_demande` varchar(220) NOT NULL,
  `Id_client` int(11) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_demande`),
  KEY `FKfjv16obdqjiyk9bqndy8wnmnp` (`Id_client`),
  CONSTRAINT `FKfjv16obdqjiyk9bqndy8wnmnp` FOREIGN KEY (`Id_client`) REFERENCES `client` (`Id_Client`),
  CONSTRAINT `fk_demande_client` FOREIGN KEY (`Id_client`) REFERENCES `client` (`Id_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandes_credit`
--

LOCK TABLES `demandes_credit` WRITE;
/*!40000 ALTER TABLE `demandes_credit` DISABLE KEYS */;
INSERT INTO `demandes_credit` VALUES (1,'2020-03-04',20000.000,'-1',1,NULL),(3,'2019-03-13',30000.000,'10',1,NULL),(4,'2017-03-28',14000.000,'10',1,NULL),(51,'2020-04-03',3500.000,'10',11,'Je vous prie'),(52,'2020-04-03',12000.000,'10',10,'Merci de prendre note '),(53,'2020-04-03',12000.000,'3',10,'Merci de prendre note '),(54,'2020-04-05',35000.000,'2',11,'Crédit Consommation'),(55,'2020-04-05',50000.000,'2',13,'Credit Voyage'),(56,'2020-04-05',45000.000,'1',12,'Credit Voyage'),(57,'2020-04-05',4500.000,'2',2,'je vous prie'),(58,'2020-04-05',23456.000,'1',13,'WAlo juste un test'),(59,'2020-04-05',32000.000,'1',10,'Walo akhi'),(60,'2020-04-05',34500.000,'2',4,'walo'),(61,'2020-04-05',12000.000,'1',3,'Walo'),(62,'2020-04-08',15000.000,'1',14,'Crédit consommation'),(63,'2020-04-08',30000.000,'2',6,'Crédit pour pèlerinage'),(64,'2020-04-18',20000.000,'1',15,'Credit consommation'),(68,'2020-04-19',777.000,'-1',15,'walo'),(69,'2020-04-19',20000.000,'2',16,'Credit consommation ...'),(70,'2020-04-19',12000.000,'10',15,'Credit voyage'),(71,'2020-04-20',30000.000,'1',2,'Pour voyage'),(72,'2020-04-20',2000.000,'2',2,'credit'),(73,'2020-04-20',1500.000,'1',15,'Walo'),(74,'2020-04-20',3000.000,'2',14,'Voyage'),(75,'2020-04-23',10000.000,'1',1,'Voyage');
/*!40000 ALTER TABLE `demandes_credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employe` (
  `id_employe` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(220) DEFAULT NULL,
  `prenom` varchar(220) DEFAULT NULL,
  `adresse` varchar(220) DEFAULT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(230) DEFAULT NULL,
  `date_embauche` date DEFAULT NULL,
  `num_agence` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_employe`),
  KEY `fk_employe_agence` (`num_agence`),
  KEY `fk_employe_Utilisateur` (`id_user`),
  CONSTRAINT `fk_employe_Utilisateur` FOREIGN KEY (`id_user`) REFERENCES `users_comptes` (`id_user`),
  CONSTRAINT `fk_employe_agence` FOREIGN KEY (`num_agence`) REFERENCES `agence` (`id_Agence`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employe`
--

LOCK TABLES `employe` WRITE;
/*!40000 ALTER TABLE `employe` DISABLE KEYS */;
INSERT INTO `employe` VALUES (1,'Karim','Mourad','Db el warda Bloc 632','645327812','karim.mourad@ensahBanque.com','2001-12-09',1,3),(3,'Taouil','Zineb','oujda Db N324','676345612','zineb.taouil@gmail.com','1993-09-02',1,20),(4,'Faghloul','Khalid','Beni bouaeich Db N444','672345612','khalid.faghloul@gmail.com','1989-02-09',1,21),(5,'hsini','oumaima','je sais pas oujda','6564312','hsini@gmail.com','2019-09-09',1,6),(6,'Asmaa','omari','je sais pas oujda','6564312','omari@gmail.com','2019-09-09',1,5),(7,'rachid','rafik','je sais pas Casa','6564312','rachid.rafik@gmail.com','2019-09-09',1,7);
/*!40000 ALTER TABLE `employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique` (
  `Id_Operation` int(11) NOT NULL AUTO_INCREMENT,
  `Date_Operation` date NOT NULL,
  `Libelle` varchar(220) NOT NULL,
  `Debit` double(8,3) DEFAULT NULL,
  `Credit` double(8,3) DEFAULT NULL,
  `Id_compte` int(11) NOT NULL,
  PRIMARY KEY (`Id_Operation`),
  KEY `FK9hwju0am8hfj3f0k5gvdkbxdd` (`Id_compte`),
  CONSTRAINT `FK9hwju0am8hfj3f0k5gvdkbxdd` FOREIGN KEY (`Id_compte`) REFERENCES `compte_bancaire` (`Id_Compte`),
  CONSTRAINT `fk_historique_compte` FOREIGN KEY (`Id_compte`) REFERENCES `compte_bancaire` (`Id_Compte`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique`
--

LOCK TABLES `historique` WRITE;
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;
INSERT INTO `historique` VALUES (1,'2020-02-20','Virement de la part de IAM',0.000,2000.000,1),(2,'2020-03-27','virement depuis Badi aouk',0.000,3000.000,1),(3,'2020-03-27','Transfére depuis Bank of America',0.000,20000.000,2),(4,'2020-03-27','virement depuis Soufiane Kachkach',0.000,1000.000,14),(5,'2020-03-31','virement de Hamid Karim',0.000,123.000,6),(6,'2020-04-02','virement de Hamid Karim',0.000,1000.000,2),(7,'2020-04-02','virement de Abdelhamid',0.000,1000.000,2),(8,'2020-04-19','virement de Hamid Karim',0.000,1000.000,20),(9,'2020-04-19','virement de Hamid Karim',0.000,10000.000,23),(10,'2020-04-19','virement depuis CLIENT TEST',0.000,270.000,1),(11,'2020-04-19','virement vers Compte N°: 1',270.000,0.000,23),(12,'2020-04-19','virement de Hamid Karim',0.000,30000.000,23),(13,'2020-04-19','virement de Karim Mourad',0.000,268.000,1),(14,'2020-04-19','virement depuis CLIENT TEST',0.000,953.000,6),(15,'2020-04-19','virement vers Compte N°: 6 de bourass Mohamed',953.000,0.000,23),(16,'2020-04-19','virement depuis ABDANE RACHID',0.000,1910.000,23),(17,'2020-04-19','virement vers Compte N°: 23 de client test',1910.000,0.000,2),(18,'2020-04-23','Depot personnel',0.000,300.000,1),(19,'2020-04-23','virement depuis ROUESSI MOHCINE',0.000,300.000,14),(20,'2020-04-23','virement vers Compte N°: 14 de Rouessi Mohcine',300.000,0.000,1);
/*!40000 ALTER TABLE `historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique_credit`
--

DROP TABLE IF EXISTS `historique_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique_credit` (
  `id_operation` int(11) NOT NULL AUTO_INCREMENT,
  `date_operation` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `id_credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `FKg62nf2m0e39cmcoyraarrvj9m` (`id_credit`),
  CONSTRAINT `FKg62nf2m0e39cmcoyraarrvj9m` FOREIGN KEY (`id_credit`) REFERENCES `credit` (`Id_Credit`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique_credit`
--

LOCK TABLES `historique_credit` WRITE;
/*!40000 ALTER TABLE `historique_credit` DISABLE KEYS */;
INSERT INTO `historique_credit` VALUES (1,'2020-03-31','Traite Janvier',1200,1),(2,'2020-03-31','Traite Fevrier',700,1),(3,'2020-03-31','Traite Mars',1200,2),(4,'2020-03-31','Traite Janvier',900,2),(5,'2020-04-02','Traite Juin',400,2),(6,'2020-04-02','Traite Finale',1100,1),(7,'2020-04-02','Traite Janvier',1200,6),(8,'2020-04-19','Traite Mai',1000,2),(9,'2020-04-19','Traite Juin',1200,2),(10,'2020-04-19','Traite Juillet',800,2),(11,'2020-04-23','Traite juin',1000,2);
/*!40000 ALTER TABLE `historique_credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique_demande`
--

DROP TABLE IF EXISTS `historique_demande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique_demande` (
  `id_avancement` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_avancement` varchar(255) DEFAULT NULL,
  `etat_apres` varchar(255) DEFAULT NULL,
  `etat_avant` varchar(255) DEFAULT NULL,
  `id_demande` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_avancement`),
  KEY `FKanjuv3k2i9vgafc4n4jqocj6j` (`id_demande`),
  CONSTRAINT `FKanjuv3k2i9vgafc4n4jqocj6j` FOREIGN KEY (`id_demande`) REFERENCES `demandes_credit` (`Id_demande`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique_demande`
--

LOCK TABLES `historique_demande` WRITE;
/*!40000 ALTER TABLE `historique_demande` DISABLE KEYS */;
INSERT INTO `historique_demande` VALUES (1,'Votre demande est entrain d\'etre traiter','2020-04-02','3','2',1),(2,'convocation pour un entretien','2020-04-02','2','1',3),(3,'Manque de justifs','2020-04-02','-1','3',1),(4,'le dossier sera examiné le 29-04-2020','2020-04-02','3','2',3),(5,'Demande Accepté','2020-04-02','10','3',3),(6,'Convocation pour un entretien 29-04-2020','2020-04-02','2','1',4),(7,'Convocation pour la date 2020-04-09','2020-04-03','2','1',51),(8,'Vous etes convoqué à un entretien 20-04-2020','2020-04-08','2','1',52),(9,'Vous etes convoqués pour un entretien le 23-09-2020','2020-04-08','2','1',53),(10,'Convocation pour entretien le 23-09-2020','2020-04-11','2','1',63),(11,'Vtre dossier est en cours d\'étude','2020-04-11','3','2',4),(12,'Votre dossier est en cours d\'étude','2020-04-11','3','2',51),(13,'Convocation pour entretien le 23-09-2020','2020-04-11','2','1',54),(14,'Convocation pour entretien le 23-06-2020','2020-04-11','2','1',55),(15,'','2020-04-11','10','3',51),(16,'vous etes concoqués le 24-06-2020','2020-04-16','2','1',60),(17,'','2020-04-19','-1','1',68),(18,'','2020-04-19','10','3',4),(19,'Convocation pour entretien','2020-04-19','2','1',69),(20,'vous etes convoqué pour le test !','2020-04-19','2','1',70),(21,'en test !','2020-04-19','3','2',70),(22,'Accepter ! ','2020-04-19','10','3',70),(23,'Votre dossier est en cours d\'etude','2020-04-19','3','2',52),(24,'Vous etes convoqué pour entretien le 23-06-2020','2020-04-19','2','1',57),(25,'Convocation pour entretien le 23-06-2020','2020-04-20','2','1',72),(26,'Demande Accepter','2020-04-20','10','3',52),(27,'Convocation pour entretien le 23-08-2020','2020-04-20','2','1',74),(28,'Encours d\'étude ','2020-04-23','3','2',53);
/*!40000 ALTER TABLE `historique_demande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_client`
--

DROP TABLE IF EXISTS `notification_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_client` (
  `id_notification` int(11) NOT NULL AUTO_INCREMENT,
  `date_notifcations` varchar(255) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_demande` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `FKn8sisg0lakel6x74r4xy3o5lu` (`id_client`),
  KEY `FK8kvk4qxtkgh81hqgm6rp57j0v` (`id_demande`),
  CONSTRAINT `FK8kvk4qxtkgh81hqgm6rp57j0v` FOREIGN KEY (`id_demande`) REFERENCES `demandes_credit` (`Id_demande`),
  CONSTRAINT `FKn8sisg0lakel6x74r4xy3o5lu` FOREIGN KEY (`id_client`) REFERENCES `client` (`Id_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_client`
--

LOCK TABLES `notification_client` WRITE;
/*!40000 ALTER TABLE `notification_client` DISABLE KEYS */;
INSERT INTO `notification_client` VALUES (1,'2020-04-03',_binary '\0',11,51),(2,'2020-04-08',_binary '',10,52),(3,'2020-04-08',_binary '',10,53),(4,'2020-04-11',_binary '\0',6,63),(5,'2020-04-11',_binary '',1,4),(6,'2020-04-11',_binary '\0',11,51),(7,'2020-04-11',_binary '\0',11,54),(8,'2020-04-11',_binary '\0',13,55),(9,'2020-04-11',_binary '\0',11,51),(10,'2020-04-16',_binary '\0',4,60),(11,'2020-04-19',_binary '',15,68),(12,'2020-04-19',_binary '',1,4),(13,'2020-04-19',_binary '',16,69),(14,'2020-04-19',_binary '',15,70),(15,'2020-04-19',_binary '',15,70),(16,'2020-04-19',_binary '',15,70),(17,'2020-04-19',_binary '',10,52),(18,'2020-04-19',_binary '',2,57),(19,'2020-04-20',_binary '\0',2,72),(20,'2020-04-20',_binary '',10,52),(21,'2020-04-20',_binary '',14,74),(22,'2020-04-23',_binary '\0',10,53);
/*!40000 ALTER TABLE `notification_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_employe`
--

DROP TABLE IF EXISTS `notification_employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_employe` (
  `id_notification` int(11) NOT NULL AUTO_INCREMENT,
  `date_notifcations` varchar(255) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `id_demande` int(11) DEFAULT NULL,
  `id_employe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `FKrudphem3xxhwdwg1m3kr7q0jo` (`id_demande`),
  KEY `FKlgrlicv2cf1vjwdw896vx8y3l` (`id_employe`),
  CONSTRAINT `FKlgrlicv2cf1vjwdw896vx8y3l` FOREIGN KEY (`id_employe`) REFERENCES `employe` (`id_employe`),
  CONSTRAINT `FKrudphem3xxhwdwg1m3kr7q0jo` FOREIGN KEY (`id_demande`) REFERENCES `demandes_credit` (`Id_demande`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_employe`
--

LOCK TABLES `notification_employe` WRITE;
/*!40000 ALTER TABLE `notification_employe` DISABLE KEYS */;
INSERT INTO `notification_employe` VALUES (1,'2020-04-03',_binary '',53,1),(2,'2020-04-03',_binary '\0',53,3),(3,'2020-04-03',_binary '',53,4),(4,'2020-04-03',_binary '',53,7),(29,'2020-04-05',_binary '',58,1),(30,'2020-04-05',_binary '\0',58,3),(31,'2020-04-05',_binary '',58,4),(32,'2020-04-05',_binary '',58,5),(33,'2020-04-05',_binary '',58,6),(34,'2020-04-05',_binary '',58,7),(35,'2020-04-05',_binary '',59,1),(36,'2020-04-05',_binary '\0',59,3),(37,'2020-04-05',_binary '',59,4),(38,'2020-04-05',_binary '',59,5),(39,'2020-04-05',_binary '',59,6),(40,'2020-04-05',_binary '',59,7),(41,'2020-04-05',_binary '',60,1),(42,'2020-04-05',_binary '\0',60,3),(43,'2020-04-05',_binary '',60,4),(44,'2020-04-05',_binary '',60,5),(45,'2020-04-05',_binary '',60,6),(46,'2020-04-05',_binary '',60,7),(47,'2020-04-05',_binary '',61,1),(48,'2020-04-05',_binary '\0',61,3),(49,'2020-04-05',_binary '',61,4),(50,'2020-04-05',_binary '',61,5),(51,'2020-04-05',_binary '',61,6),(52,'2020-04-05',_binary '',61,7),(53,'2020-04-08',_binary '',62,1),(54,'2020-04-08',_binary '\0',62,3),(55,'2020-04-08',_binary '',62,4),(56,'2020-04-08',_binary '',62,5),(57,'2020-04-08',_binary '',62,6),(58,'2020-04-08',_binary '',62,7),(59,'2020-04-08',_binary '',63,1),(60,'2020-04-08',_binary '\0',63,3),(61,'2020-04-08',_binary '',63,4),(62,'2020-04-08',_binary '',63,5),(63,'2020-04-08',_binary '',63,6),(64,'2020-04-08',_binary '',63,7),(65,'2020-04-18',_binary '',64,1),(66,'2020-04-18',_binary '\0',64,3),(67,'2020-04-18',_binary '',64,4),(68,'2020-04-18',_binary '',64,5),(69,'2020-04-18',_binary '',64,6),(70,'2020-04-18',_binary '',64,7),(89,'2020-04-19',_binary '',68,1),(90,'2020-04-19',_binary '\0',68,3),(91,'2020-04-19',_binary '',68,4),(92,'2020-04-19',_binary '',68,5),(93,'2020-04-19',_binary '',68,6),(94,'2020-04-19',_binary '',68,7),(95,'2020-04-19',_binary '\0',69,1),(96,'2020-04-19',_binary '\0',69,3),(97,'2020-04-19',_binary '',69,4),(98,'2020-04-19',_binary '',69,5),(99,'2020-04-19',_binary '',69,6),(100,'2020-04-19',_binary '',69,7),(101,'2020-04-19',_binary '\0',70,1),(102,'2020-04-19',_binary '\0',70,3),(103,'2020-04-19',_binary '',70,4),(104,'2020-04-19',_binary '',70,5),(105,'2020-04-19',_binary '',70,6),(106,'2020-04-19',_binary '',70,7),(107,'2020-04-20',_binary '\0',71,1),(108,'2020-04-20',_binary '\0',71,3),(109,'2020-04-20',_binary '',71,4),(110,'2020-04-20',_binary '',71,5),(111,'2020-04-20',_binary '\0',71,6),(112,'2020-04-20',_binary '',71,7),(113,'2020-04-20',_binary '\0',72,1),(114,'2020-04-20',_binary '\0',72,3),(115,'2020-04-20',_binary '',72,4),(116,'2020-04-20',_binary '',72,5),(117,'2020-04-20',_binary '\0',72,6),(118,'2020-04-20',_binary '',72,7),(119,'2020-04-20',_binary '\0',73,1),(120,'2020-04-20',_binary '\0',73,3),(121,'2020-04-20',_binary '',73,4),(122,'2020-04-20',_binary '',73,5),(123,'2020-04-20',_binary '\0',73,6),(124,'2020-04-20',_binary '',73,7),(125,'2020-04-20',_binary '\0',74,1),(126,'2020-04-20',_binary '\0',74,3),(127,'2020-04-20',_binary '\0',74,4),(128,'2020-04-20',_binary '',74,5),(129,'2020-04-20',_binary '\0',74,6),(130,'2020-04-20',_binary '',74,7),(131,'2020-04-23',_binary '\0',75,1),(132,'2020-04-23',_binary '\0',75,3),(133,'2020-04-23',_binary '\0',75,4),(134,'2020-04-23',_binary '\0',75,5),(135,'2020-04-23',_binary '\0',75,6),(136,'2020-04-23',_binary '\0',75,7);
/*!40000 ALTER TABLE `notification_employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_comptes`
--

DROP TABLE IF EXISTS `users_comptes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_comptes` (
  `Id_User` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(220) NOT NULL,
  `Password` varchar(220) NOT NULL,
  `Role` varchar(220) NOT NULL,
  PRIMARY KEY (`Id_User`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_comptes`
--

LOCK TABLES `users_comptes` WRITE;
/*!40000 ALTER TABLE `users_comptes` DISABLE KEYS */;
INSERT INTO `users_comptes` VALUES (1,'rouessi.mohcine@gmail.com','mohcine22','ROLE_CLIENT'),(3,'karim.mourad@gmail.com','123456','ROLE_EMPLOYE'),(4,'admin@gmail.com','admin404','ROLE_ADMIN'),(5,'omari@gmail.com','omari22','ROLE_EMPLOYE'),(6,'hsini@gmail.com','hsini','ROLE_EMPLOYE'),(7,'rachid.rafik@gmail.com','5555','ROLE_EMPLOYE'),(10,'bounou','bounou444','ROLE_CLIENT'),(15,'barroussa','baroussa444','ROLE_CLIENT'),(16,'Manal','1111','ROLE_CLIENT'),(17,'hamza.sakhry@gmail.com','sakhry22','ROLE_CLIENT'),(18,'hamza.sakhry@gmail.com','sakhry22','ROLE_CLIENT'),(20,'zineb.rouessi@gmail.com','zineb99','ROLE_EMPLOYE'),(21,'khalid.faghloul@gmail.com','khalid89','ROLE_EMPLOYE'),(22,'Sape','12345','ROLE_CLIENT'),(24,'tmira44@gmail.com','Tmira232','ROLE_CLIENT'),(25,'rachid@gmail.com','rachid1234','ROLE_CLIENT'),(26,'abdane@gmail.com','rachid1234','ROLE_CLIENT'),(27,'client','test','ROLE_CLIENT'),(28,'nabilDirar','nabil123','ROLE_CLIENT');
/*!40000 ALTER TABLE `users_comptes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-29  0:29:56
