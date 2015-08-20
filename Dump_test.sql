-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: questionnaire
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(155) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'marcio@jamal.re','57b472bdf2cf967a8a9b862717be0cfccd5260121823076c9bcd3762bf1a0096','Márcio Jamal R'),(2,'email','senha','Novo'),(3,'asas','2b435c8d08520012872eaf3acdfd9709ddc7a5a27393f84d4754432f289d829c','novo 2');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_question` text,
  `questionnaire_id` bigint(20) NOT NULL,
  `user_answer_id` int(11) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_questionnaire1_idx` (`questionnaire_id`),
  KEY `fk_answer_user_answer1_idx` (`user_answer_id`),
  KEY `fk_answer_question1_idx` (`question_id`),
  CONSTRAINT `fk_answer_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_questionnaire1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_user_answer1` FOREIGN KEY (`user_answer_id`) REFERENCES `user_answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'ok1',8,1,'2015-08-19 07:10:29',27),(2,NULL,8,1,'2015-08-19 07:10:29',28),(3,'marcio@jamal.reaaa',7,1,'2015-08-19 07:34:22',26),(4,'Feito pelo teste@teste.com.\r\nEscolhe a adada do ns2.',8,3,'2015-08-19 11:44:59',27),(5,NULL,8,3,'2015-08-19 11:44:59',28),(6,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',31),(7,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',45),(8,NULL,2,1,'2015-08-19 14:20:42',12),(9,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',46),(10,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',30),(11,NULL,2,1,'2015-08-19 14:20:42',36),(12,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',33),(13,NULL,2,1,'2015-08-19 14:20:42',11),(14,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',38),(15,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',37),(16,NULL,2,1,'2015-08-19 14:20:42',8),(17,'marcio@jamal.re',2,1,'2015-08-19 14:20:42',32),(18,NULL,2,1,'2015-08-19 14:20:42',47);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_has_option`
--

DROP TABLE IF EXISTS `answer_has_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_has_option` (
  `answer_id` bigint(20) NOT NULL,
  `option_id` bigint(20) NOT NULL,
  PRIMARY KEY (`answer_id`,`option_id`),
  KEY `fk_answer_has_option_option1_idx` (`option_id`),
  KEY `fk_answer_has_option_answer1_idx` (`answer_id`),
  CONSTRAINT `fk_answer_has_option_answer1` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_has_option_option1` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_has_option`
--

LOCK TABLES `answer_has_option` WRITE;
/*!40000 ALTER TABLE `answer_has_option` DISABLE KEYS */;
INSERT INTO `answer_has_option` VALUES (16,10),(13,31),(8,34),(2,42),(5,44),(11,55),(18,58),(18,59);
/*!40000 ALTER TABLE `answer_has_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `option_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_option_question1_idx` (`question_id`),
  CONSTRAINT `fk_option_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (10,'2a',8,0),(11,'4',8,1),(13,'5',8,2),(14,'6',8,3),(15,'3',8,4),(16,'1',8,5),(27,'2',11,0),(28,'1',11,0),(29,'5',11,0),(30,'6',11,0),(31,'3',11,0),(32,'4',11,0),(33,'1',12,0),(34,'4',12,3),(35,'5',12,4),(36,'2',12,1),(37,'3',12,2),(38,'6',12,5),(39,'7',12,6),(42,' adada do ns2 b',28,1),(43,'adada do ns2 c',28,2),(44,'adada do ns2',28,0),(54,'a',8,6),(55,'a',36,1),(56,'n',36,0),(57,'UMA..UMA..UMA..UMA..UMA.. UMA..UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..UMA.. UMA..',47,0),(58,'3',47,2),(59,'2',47,1),(60,'4',47,3),(61,'Sim',48,0),(62,'Não',48,1),(63,'Não',49,1),(64,'Sim',49,0),(65,'Não',50,1),(66,'Talvez',50,2),(67,'Sim',50,0),(68,'Talvez',51,2),(69,'Sim',51,0),(70,'Não',51,1),(71,'Não',52,1),(72,'Sim',52,0),(73,'Talvez',53,2),(74,'Sim',53,0),(75,'Não',53,1),(76,'1993',55,0),(77,'2000',55,1),(78,'2015',55,2);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_type_id` int(11) NOT NULL,
  `questionnaire_id` bigint(20) NOT NULL,
  `question` text NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_question_type_idx` (`question_type_id`),
  KEY `fk_question_questionnaire1_idx` (`questionnaire_id`),
  CONSTRAINT `fk_question_question_type` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_questionnaire1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (8,2,2,'313','2015-08-18 03:18:14'),(11,2,2,'wwwaaa','2015-08-18 03:42:41'),(12,2,2,'novo teste','2015-08-18 04:03:28'),(24,1,5,'wqw','2015-08-18 07:03:11'),(25,1,6,'1','2015-08-18 07:10:33'),(26,1,7,'ds1','2015-08-18 07:14:33'),(27,1,8,'asdasd','2015-08-18 07:16:30'),(28,2,8,'asdasd','2015-08-18 08:18:45'),(30,1,2,'aberta','2015-08-18 19:01:43'),(31,1,2,'\\1','2015-08-18 19:49:47'),(32,1,2,'a2','2015-08-18 19:49:57'),(33,1,2,'a3','2015-08-18 19:50:02'),(36,2,2,'n222222','2015-08-18 20:51:51'),(37,1,2,'an2','2015-08-18 20:20:57'),(38,1,2,'an2','2015-08-18 20:20:57'),(45,1,2,'an2aa','2015-08-18 20:42:53'),(46,1,2,'an2aabbbbbCCCC','2015-08-18 20:51:33'),(47,3,2,'Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... Selecione vaias opções... ','2015-08-19 01:51:01'),(48,2,9,'Qual o sistema de apuração de IRPJ e CSLL utilizado pela empresa?','2015-08-19 02:18:50'),(49,2,9,' A empresa compensou ou vem compensando seu prejuízo fiscal acumulado com 30% do lucro líquido ajustado, conforme legislação  em vigor?','2015-08-19 02:19:07'),(50,2,9,'Em caso de estar compensando 100% está amparada por medida judicial?','2015-08-19 02:19:28'),(51,2,9,'A empresa vem realizando lucro inflacionário?','2015-08-19 02:19:46'),(52,2,9,'A empresa foi ou está sendo tributada por efeito de saldo credor de correção monetária?','2015-08-19 02:20:00'),(53,2,9,'A empresa está impugnando a retenção do IRPJ, CSLL, PIS e COFINS nos pagamentos que os órgãos federais lhe fazem (prevista na Lei n.º 9.430/96)?','2015-08-19 02:20:20'),(54,1,9,'A empresa efetua pagamento a prestador de serviços estrangeiro com retenção de IRRF?','2015-08-19 02:20:31'),(55,3,9,'A empresa vem considerando o faturamento “cheio” (desde abril/1992)?','2015-08-19 02:20:53');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (1,'Aberta'),(2,'1 Opção'),(3,'Varias opções');
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionnaire` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `administrator_id` int(11) NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_questionnaire_administrator1_idx` (`administrator_id`),
  CONSTRAINT `fk_questionnaire_administrator1` FOREIGN KEY (`administrator_id`) REFERENCES `administrator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (2,1,1,'teste 2','2015-08-18 00:14:56'),(5,1,1,'n23','2015-08-18 07:03:06'),(6,1,0,'n3','2015-08-18 07:10:27'),(7,1,1,'ns','2015-08-18 07:14:24'),(8,1,1,'ns2','2015-08-18 07:16:25'),(9,1,1,'Avaliação para recuperação fiscal','2015-08-19 02:18:28');
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_answer`
--

DROP TABLE IF EXISTS `user_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(155) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_answer`
--

LOCK TABLES `user_answer` WRITE;
/*!40000 ALTER TABLE `user_answer` DISABLE KEYS */;
INSERT INTO `user_answer` VALUES (1,'marcio@jamal.re','2015-08-19 05:02:08'),(2,'marcio@jama.re','2015-08-19 06:11:27'),(3,'teste@teste.com','2015-08-19 11:44:15');
/*!40000 ALTER TABLE `user_answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-20  1:23:00
