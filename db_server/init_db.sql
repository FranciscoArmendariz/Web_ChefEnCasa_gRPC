CREATE DATABASE  IF NOT EXISTS `init_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `init_db`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: init_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `receta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc41wfmpfh25oy9ykiqs68wkcn` (`receta_id`),
  CONSTRAINT `FKc41wfmpfh25oy9ykiqs68wkcn` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto`
--

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
INSERT INTO `foto` VALUES (1,'https://pbs.twimg.com/media/EShb5zyXsAAx8G8.jpg',1),(2,'https://www.comodos.com.ar/wp-content/uploads/2020/09/Pollo-con-pure.jpg',2),(3,'https://cdn0.recetasgratis.net/es/posts/4/8/2/guiso_de_lentejas_74284_orig.jpg',3),(4,'https://www.cucinare.tv/wp-content/uploads/2018/11/Pastel-de-papas.jpg',4),(5,'https://www.cronista.com/files/image/307/307256/5ffe2f88d2a71_934_695!.jpg',5);
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingrediente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `receta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7o3bycckbp53t53tl84lsu9n8` (`receta_id`),
  CONSTRAINT `FK7o3bycckbp53t53tl84lsu9n8` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` VALUES (1,'1 unidad','carne',1),(2,'2 unidades','Tomate',1),(3,'1 unidad','queso',1),(4,'1kg','Pollo',2),(5,'250gr','Lentejas',3),(6,'500gr','Roast beef',3),(7,'1kg','Papa',4),(8,'1kg','Carne Picada',4),(9,'400g','dulde de leche',5),(10,'400g','crema',5),(11,'750g','galletitas de chocolate',5),(12,'50g','chocolate',5),(13,'50g','cacao en polvo',5);
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paso`
--

DROP TABLE IF EXISTS `paso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `numero` int NOT NULL,
  `receta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmm2234h6enp66ejnypt4b94fc` (`receta_id`),
  CONSTRAINT `FKmm2234h6enp66ejnypt4b94fc` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paso`
--

LOCK TABLES `paso` WRITE;
/*!40000 ALTER TABLE `paso` DISABLE KEYS */;
INSERT INTO `paso` VALUES (1,'cocinar la carne',1,1),(2,'colocar tomate y queso',2,1),(3,'meter al horno 10 minutos',3,1),(4,'Cocinar el pollo',1,2),(5,'Dejar en reposo las lentejas',1,3),(6,'Hervir la papa',1,4),(7,'Cocinar la carne',2,4),(8,'comenzar a batir el dulce de leche',1,5),(9,'agregar crema',2,5),(10,'juntar todos los ingredientes',3,5);
/*!40000 ALTER TABLE `paso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tiempo_aprox` int NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (1,'Almuerzo','Milanesa con salsa de tomate y queso',30,'Milanesa Napolitana'),(2,'Almuerzo','Pollo al limon y pure de papa',45,'Pollo con pure'),(3,'Almuerzo','Rico guiso con lentejas y carne de res',60,'Guiso de lentejas'),(4,'Almuerzo','Carne picada y pure de papa',100,'Pastel de papa'),(5,'Postre','Postre argentino',20,'chocotorta');
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetas_creadas`
--

DROP TABLE IF EXISTS `recetas_creadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetas_creadas` (
  `usuario_id` bigint NOT NULL,
  `receta_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`receta_id`),
  KEY `FK9dx3a5dkna5cjer6qe2amwg8u` (`receta_id`),
  CONSTRAINT `FK2n9gy1eddadwfcapfcn6o3vb` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK9dx3a5dkna5cjer6qe2amwg8u` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetas_creadas`
--

LOCK TABLES `recetas_creadas` WRITE;
/*!40000 ALTER TABLE `recetas_creadas` DISABLE KEYS */;
INSERT INTO `recetas_creadas` VALUES (1,1),(2,2),(2,3),(3,4),(4,5);
/*!40000 ALTER TABLE `recetas_creadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetas_favoritas`
--

DROP TABLE IF EXISTS `recetas_favoritas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetas_favoritas` (
  `usuario_id` bigint NOT NULL,
  `receta_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`receta_id`),
  KEY `FKio4fjy6fpsxe5l7ipsf2ws3gq` (`receta_id`),
  CONSTRAINT `FKi4bjf97r6i78t9vxxi88vksy7` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKio4fjy6fpsxe5l7ipsf2ws3gq` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetas_favoritas`
--

LOCK TABLES `recetas_favoritas` WRITE;
/*!40000 ALTER TABLE `recetas_favoritas` DISABLE KEYS */;
/*!40000 ALTER TABLE `recetas_favoritas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `clave` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKi02kr8ui5pqddyd7pkm3v4jbt` (`usuario`),
  UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Franco','franco@gmail.com','Franco','Usuario','Franco'),(2,'Francisco','francisco@gmail.com','Francisco','Usuario','Francisco'),(3,'Valentin','valentin@gmail.com','Valentin','Usuario','Valentin'),(4,'Nicolas','nicolas@gmail.com','Nicolas','Usuario','Nicolas');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_seguidos`
--

DROP TABLE IF EXISTS `usuarios_seguidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_seguidos` (
  `seguidor_id` bigint NOT NULL,
  `seguido_id` bigint NOT NULL,
  KEY `FK73ye5uv492tb1457ye7cfrdbh` (`seguido_id`),
  KEY `FKhll6dgs69hspvvtc7bns6vn4c` (`seguidor_id`),
  CONSTRAINT `FK73ye5uv492tb1457ye7cfrdbh` FOREIGN KEY (`seguido_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKhll6dgs69hspvvtc7bns6vn4c` FOREIGN KEY (`seguidor_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_seguidos`
--

LOCK TABLES `usuarios_seguidos` WRITE;
/*!40000 ALTER TABLE `usuarios_seguidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_seguidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'init_db'
--

--
-- Dumping routines for database 'init_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-03 17:45:36
