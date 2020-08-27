mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: scdcb
-- ------------------------------------------------------
-- Server version	8.0.21
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,ANSI' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
mysqldump: Error: 'Access denied; you need (at least one of) the PROCESS privilege(s) for this operation' when trying to dump tablespaces

--
-- Table structure for table "administradores"
--

DROP TABLE IF EXISTS "administradores";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "administradores" (
  "permissoes" int NOT NULL,
  "cd_usuario" int NOT NULL,
  PRIMARY KEY ("cd_usuario"),
  CONSTRAINT "FKnq1oihn3991k0k4egwsg10hqx" FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "administradores"
--

LOCK TABLES "administradores" WRITE;
/*!40000 ALTER TABLE "administradores" DISABLE KEYS */;
/*!40000 ALTER TABLE "administradores" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "bairros"
--

DROP TABLE IF EXISTS "bairros";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "bairros" (
  "id" int NOT NULL AUTO_INCREMENT,
  "nome" varchar(50) DEFAULT NULL,
  "cidade_id" int NOT NULL,
  PRIMARY KEY ("id"),
  KEY "FK3s14bep9kl8hgm37jyvrf77ni" ("cidade_id"),
  CONSTRAINT "FK3s14bep9kl8hgm37jyvrf77ni" FOREIGN KEY ("cidade_id") REFERENCES "cidades" ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "bairros"
--

LOCK TABLES "bairros" WRITE;
/*!40000 ALTER TABLE "bairros" DISABLE KEYS */;
/*!40000 ALTER TABLE "bairros" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "beneficiados"
--

DROP TABLE IF EXISTS "beneficiados";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "beneficiados" (
  "npf" int DEFAULT NULL,
  "num_casa" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  PRIMARY KEY ("cd_usuario"),
  KEY "FKtgihf48o08umkoh68qhgrt0aj" ("cd_bairro"),
  CONSTRAINT "FKmuscep6cww8ewl7tbomkj9kp7" FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario"),
  CONSTRAINT "FKtgihf48o08umkoh68qhgrt0aj" FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "beneficiados"
--

LOCK TABLES "beneficiados" WRITE;
/*!40000 ALTER TABLE "beneficiados" DISABLE KEYS */;
/*!40000 ALTER TABLE "beneficiados" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "cidades"
--

DROP TABLE IF EXISTS "cidades";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "cidades" (
  "id" int NOT NULL AUTO_INCREMENT,
  "nome" varchar(50) DEFAULT NULL,
  "uf_id" int NOT NULL,
  PRIMARY KEY ("id"),
  KEY "FK2l4ctxe8bqvgw2qg93np3pjyu" ("uf_id"),
  CONSTRAINT "FK2l4ctxe8bqvgw2qg93np3pjyu" FOREIGN KEY ("uf_id") REFERENCES "ufs" ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "cidades"
--

LOCK TABLES "cidades" WRITE;
/*!40000 ALTER TABLE "cidades" DISABLE KEYS */;
/*!40000 ALTER TABLE "cidades" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "doadores"
--

DROP TABLE IF EXISTS "doadores";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "doadores" (
  "cpf" varchar(50) DEFAULT NULL,
  "dt_nascimento" datetime(6) NOT NULL,
  "num_casa" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  PRIMARY KEY ("cd_usuario"),
  KEY "FKpyb7p6y0ovrtg1so3nny3adfh" ("cd_bairro"),
  CONSTRAINT "FK8qqf7xwpdgjhkc1kx2m7twk6q" FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario"),
  CONSTRAINT "FKpyb7p6y0ovrtg1so3nny3adfh" FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "doadores"
--

LOCK TABLES "doadores" WRITE;
/*!40000 ALTER TABLE "doadores" DISABLE KEYS */;
/*!40000 ALTER TABLE "doadores" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "entregas"
--

DROP TABLE IF EXISTS "entregas";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "entregas" (
  "cd_entrega" int NOT NULL AUTO_INCREMENT,
  "documento" varchar(150) NOT NULL,
  "dt_entrega" datetime(6) NOT NULL,
  "qtd_cestas" int DEFAULT NULL,
  "cd_solicitacao" int NOT NULL,
  PRIMARY KEY ("cd_entrega"),
  KEY "FKm572qlbh3exe4v41kb1estlal" ("cd_solicitacao"),
  CONSTRAINT "FKm572qlbh3exe4v41kb1estlal" FOREIGN KEY ("cd_solicitacao") REFERENCES "solicitacoes" ("cd_solicitacao")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "entregas"
--

LOCK TABLES "entregas" WRITE;
/*!40000 ALTER TABLE "entregas" DISABLE KEYS */;
/*!40000 ALTER TABLE "entregas" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "instituicoes"
--

DROP TABLE IF EXISTS "instituicoes";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "instituicoes" (
  "cnpj" varchar(50) DEFAULT NULL,
  "limite_doacoes" int NOT NULL,
  "num_imovel" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  PRIMARY KEY ("cd_usuario"),
  KEY "FKi2wloymhmdhkj23wjebav9vxf" ("cd_bairro"),
  CONSTRAINT "FKi2wloymhmdhkj23wjebav9vxf" FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id"),
  CONSTRAINT "FKm5jn4kbwf1be99ku8pskawutm" FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "instituicoes"
--

LOCK TABLES "instituicoes" WRITE;
/*!40000 ALTER TABLE "instituicoes" DISABLE KEYS */;
/*!40000 ALTER TABLE "instituicoes" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "intencoes"
--

DROP TABLE IF EXISTS "intencoes";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "intencoes" (
  "cd_intencao" int NOT NULL AUTO_INCREMENT,
  "codigo" varchar(8) DEFAULT NULL,
  "dt_intencao" date NOT NULL,
  "qtd_cestas" int NOT NULL,
  "status" int NOT NULL,
  "cd_doador" int NOT NULL,
  "cd_instituicao" int NOT NULL,
  "tp_cesta" int NOT NULL,
  PRIMARY KEY ("cd_intencao"),
  UNIQUE KEY "UK_btdot2lfnsm7m2v4dh0k405wy" ("codigo"),
  KEY "FKgnsw0c6sgg99qm0shypgy4h0" ("cd_doador"),
  KEY "FKhwhpu2pcmk1itd67u7lclg2es" ("cd_instituicao"),
  KEY "FKjn75uhbdgdtd80o307xb1dhei" ("tp_cesta"),
  CONSTRAINT "FKgnsw0c6sgg99qm0shypgy4h0" FOREIGN KEY ("cd_doador") REFERENCES "doadores" ("cd_usuario"),
  CONSTRAINT "FKhwhpu2pcmk1itd67u7lclg2es" FOREIGN KEY ("cd_instituicao") REFERENCES "instituicoes" ("cd_usuario"),
  CONSTRAINT "FKjn75uhbdgdtd80o307xb1dhei" FOREIGN KEY ("tp_cesta") REFERENCES "tipos_cesta" ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "intencoes"
--

LOCK TABLES "intencoes" WRITE;
/*!40000 ALTER TABLE "intencoes" DISABLE KEYS */;
/*!40000 ALTER TABLE "intencoes" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "recebimentos"
--

DROP TABLE IF EXISTS "recebimentos";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "recebimentos" (
  "cd_recebimento" int NOT NULL AUTO_INCREMENT,
  "dt_recebimento" datetime(6) NOT NULL,
  "nf_cesta" varchar(150) DEFAULT NULL,
  "qtd_cestas" int NOT NULL,
  "cd_intencao" int NOT NULL,
  PRIMARY KEY ("cd_recebimento"),
  KEY "FKg3bxyoanuwlvafb9f811hytfo" ("cd_intencao"),
  CONSTRAINT "FKg3bxyoanuwlvafb9f811hytfo" FOREIGN KEY ("cd_intencao") REFERENCES "intencoes" ("cd_intencao")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "recebimentos"
--

LOCK TABLES "recebimentos" WRITE;
/*!40000 ALTER TABLE "recebimentos" DISABLE KEYS */;
/*!40000 ALTER TABLE "recebimentos" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "solicitacoes"
--

DROP TABLE IF EXISTS "solicitacoes";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "solicitacoes" (
  "cd_solicitacao" int NOT NULL AUTO_INCREMENT,
  "codigo" varchar(8) DEFAULT NULL,
  "dt_solicitacao" date NOT NULL,
  "qtd_cestas" int NOT NULL,
  "status" int NOT NULL,
  "cd_beneficiado" int NOT NULL,
  "cd_instituicao" int NOT NULL,
  "cd_tp_cesta" int NOT NULL,
  PRIMARY KEY ("cd_solicitacao"),
  UNIQUE KEY "UK_d3ke701mo5qyiw1iu34ya1o1h" ("codigo"),
  KEY "FKt1wmx0bbvp496jm6dpj6fpor3" ("cd_beneficiado"),
  KEY "FK4h5xb8cvq8j1x0pc32jrxnie4" ("cd_instituicao"),
  KEY "FKogb94caycej51icb7l0ra2o80" ("cd_tp_cesta"),
  CONSTRAINT "FK4h5xb8cvq8j1x0pc32jrxnie4" FOREIGN KEY ("cd_instituicao") REFERENCES "instituicoes" ("cd_usuario"),
  CONSTRAINT "FKogb94caycej51icb7l0ra2o80" FOREIGN KEY ("cd_tp_cesta") REFERENCES "tipos_cesta" ("id"),
  CONSTRAINT "FKt1wmx0bbvp496jm6dpj6fpor3" FOREIGN KEY ("cd_beneficiado") REFERENCES "beneficiados" ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "solicitacoes"
--

LOCK TABLES "solicitacoes" WRITE;
/*!40000 ALTER TABLE "solicitacoes" DISABLE KEYS */;
/*!40000 ALTER TABLE "solicitacoes" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "telefones"
--

DROP TABLE IF EXISTS "telefones";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "telefones" (
  "numero" varchar(50) NOT NULL,
  "cd_usuario" int DEFAULT NULL,
  PRIMARY KEY ("numero"),
  KEY "FKeyjt2ue3o4907j97kqx459eaj" ("cd_usuario"),
  CONSTRAINT "FKeyjt2ue3o4907j97kqx459eaj" FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "telefones"
--

LOCK TABLES "telefones" WRITE;
/*!40000 ALTER TABLE "telefones" DISABLE KEYS */;
/*!40000 ALTER TABLE "telefones" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "tipos_cesta"
--

DROP TABLE IF EXISTS "tipos_cesta";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "tipos_cesta" (
  "id" int NOT NULL AUTO_INCREMENT,
  "descricao" varchar(150) NOT NULL,
  "nome" varchar(50) DEFAULT NULL,
  PRIMARY KEY ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "tipos_cesta"
--

LOCK TABLES "tipos_cesta" WRITE;
/*!40000 ALTER TABLE "tipos_cesta" DISABLE KEYS */;
/*!40000 ALTER TABLE "tipos_cesta" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "ufs"
--

DROP TABLE IF EXISTS "ufs";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "ufs" (
  "id" int NOT NULL AUTO_INCREMENT,
  "nome" varchar(50) DEFAULT NULL,
  "sigla" varchar(2) DEFAULT NULL,
  PRIMARY KEY ("id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "ufs"
--

LOCK TABLES "ufs" WRITE;
/*!40000 ALTER TABLE "ufs" DISABLE KEYS */;
/*!40000 ALTER TABLE "ufs" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "usuarios"
--

DROP TABLE IF EXISTS "usuarios";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "usuarios" (
  "cd_usuario" int NOT NULL AUTO_INCREMENT,
  "login" varchar(50) DEFAULT NULL,
  "nome" varchar(50) DEFAULT NULL,
  "senha" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "usuarios"
--

LOCK TABLES "usuarios" WRITE;
/*!40000 ALTER TABLE "usuarios" DISABLE KEYS */;
/*!40000 ALTER TABLE "usuarios" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "usuarios_telefones"
--

DROP TABLE IF EXISTS "usuarios_telefones";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE "usuarios_telefones" (
  "usuario_cd_usuario" int NOT NULL,
  "telefones_numero" varchar(50) NOT NULL,
  PRIMARY KEY ("usuario_cd_usuario","telefones_numero"),
  UNIQUE KEY "UK_mqqnhpvtyji0xm0uie2j2wt7k" ("telefones_numero"),
  CONSTRAINT "FK7udt6w3r2rg703m0ox4nc45d7" FOREIGN KEY ("telefones_numero") REFERENCES "telefones" ("numero"),
  CONSTRAINT "FKpa3mcbbfhm1pt5m24qho9euxa" FOREIGN KEY ("usuario_cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "usuarios_telefones"
--

LOCK TABLES "usuarios_telefones" WRITE;
/*!40000 ALTER TABLE "usuarios_telefones" DISABLE KEYS */;
/*!40000 ALTER TABLE "usuarios_telefones" ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-26 23:08:01
