DROP TABLE IF EXISTS "entregas";
DROP TABLE IF EXISTS "solicitacoes";
DROP TABLE IF EXISTS "recebimentos";
DROP TABLE IF EXISTS "intencoes";
DROP TABLE IF EXISTS "tipos_cestas";
DROP TABLE IF EXISTS "telefones_usuarios";
DROP TABLE IF EXISTS "telefones";
DROP TABLE IF EXISTS "instituicoes";
DROP TABLE IF EXISTS "doadores";
DROP TABLE IF EXISTS "beneficiados";
DROP TABLE IF EXISTS "administradores";
DROP TABLE IF EXISTS "usuarios";
DROP TABLE IF EXISTS "bairros";
DROP TABLE IF EXISTS "cidades";
DROP TABLE IF EXISTS "ufs";

CREATE TABLE "tipos_cestas" (
  "id" SERIAL,
  "descricao" varchar(150) NOT NULL,
  "nome" varchar(50) DEFAULT NULL,
  CONSTRAINT "pk_tipos_cestas"
    PRIMARY KEY ("id")
);

CREATE TABLE "ufs" (
  "id" SERIAL,
  "nome" varchar(50) DEFAULT NULL,
  "sigla" varchar(2) DEFAULT NULL,
  CONSTRAINT "pk_ufs"
    PRIMARY KEY ("id")
);

CREATE TABLE "cidades" (
  "id" SERIAL,
  "nome" varchar(50) DEFAULT NULL,
  "uf_id" int NOT NULL,
  CONSTRAINT "pk_cidades"
    PRIMARY KEY ("id"),
  CONSTRAINT "fk_cidades_ufs"
    FOREIGN KEY ("uf_id") REFERENCES "ufs" ("id")
);

CREATE TABLE "bairros" (
  "id" SERIAL,
  "nome" varchar(50) DEFAULT NULL,
  "cidade_id" int NOT NULL,
  CONSTRAINT "pk_bairros"
    PRIMARY KEY ("id"),
  CONSTRAINT "fk_bairros_cidades"
    FOREIGN KEY ("cidade_id") REFERENCES "cidades" ("id")
);

CREATE TABLE "telefones" (
  "numero" varchar(50) NOT NULL,
  CONSTRAINT "pk_telefones"
    PRIMARY KEY ("numero")
);

CREATE TABLE "usuarios" (
  "cd_usuario" SERIAL,
  "login" varchar(50) DEFAULT NULL,
  "nome" varchar(50) DEFAULT NULL,
  "senha" varchar(20) DEFAULT NULL,
  CONSTRAINT "pk_usuarios"
    PRIMARY KEY ("cd_usuario")
);

CREATE TABLE "administradores" (
  "cd_usuario" int NOT NULL,
  "permissoes" int NOT NULL,
  CONSTRAINT "pk_administradores"
    PRIMARY KEY ("cd_usuario"),
  CONSTRAINT "fk_administradores_usuarios"
    FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);

CREATE TABLE "doadores" (
  "cpf" varchar(50) DEFAULT NULL,
  "dt_nascimento" TIMESTAMP NOT NULL,
  "num_casa" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  CONSTRAINT "pk_doadores"
    PRIMARY KEY ("cd_usuario"),
  CONSTRAINT "fk_doadores_usuarios"
    FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario"),
  CONSTRAINT "fk_doadores_bairros"
    FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id")
);

CREATE TABLE "beneficiados" (
  "npf" int DEFAULT NULL,
  "num_casa" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  CONSTRAINT "pk_beneficiados"
    PRIMARY KEY ("cd_usuario"),
  CONSTRAINT "fk_beneficiados_usuarios"
    FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario"),
  CONSTRAINT "fk_beneficiados_bairros"
    FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id")
);

CREATE TABLE "instituicoes" (
  "cnpj" varchar(50) DEFAULT NULL,
  "limite_doacoes" int NOT NULL,
  "num_imovel" int DEFAULT NULL,
  "rua" varchar(50) DEFAULT NULL,
  "cd_usuario" int NOT NULL,
  "cd_bairro" int NOT NULL,
  CONSTRAINT "pk_instituicoes"
    PRIMARY KEY ("cd_usuario"),
  CONSTRAINT "fk_instituicoes_bairros"
    FOREIGN KEY ("cd_bairro") REFERENCES "bairros" ("id"),
  CONSTRAINT "fk_instituicoes_usuarios"
    FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);

CREATE TABLE "intencoes" (
  "cd_intencao" SERIAL,
  "codigo" varchar(8) DEFAULT NULL,
  "dt_intencao" date NOT NULL,
  "qtd_cestas" int NOT NULL,
  "status" int NOT NULL,
  "cd_doador" int NOT NULL,
  "cd_instituicao" int NOT NULL,
  "tp_cesta" int NOT NULL,
  CONSTRAINT "pk_intencoes"
    PRIMARY KEY ("cd_intencao"),
  CONSTRAINT "fk_intencoes_doadores"
    FOREIGN KEY ("cd_doador") REFERENCES "doadores" ("cd_usuario"),
  CONSTRAINT "fk_intencoes_instituicoes"
    FOREIGN KEY ("cd_instituicao") REFERENCES "instituicoes" ("cd_usuario"),
  CONSTRAINT "fk_intencoes_tipos_cestas"
    FOREIGN KEY ("tp_cesta") REFERENCES "tipos_cestas" ("id")
);

CREATE TABLE "solicitacoes" (
  "cd_solicitacao" SERIAL,
  "codigo" varchar(8) DEFAULT NULL,
  "dt_solicitacao" date NOT NULL,
  "qtd_cestas" int NOT NULL,
  "status" int NOT NULL,
  "cd_beneficiado" int NOT NULL,
  "cd_instituicao" int NOT NULL,
  "cd_tp_cesta" int NOT NULL,
  CONSTRAINT "pk_solicitacoes"
    PRIMARY KEY ("cd_solicitacao"),
  CONSTRAINT "fk_solicitacoes_instituicoes"
    FOREIGN KEY ("cd_instituicao") REFERENCES "instituicoes" ("cd_usuario"),
  CONSTRAINT "fk_solicitacoes_tipos_cestas"
    FOREIGN KEY ("cd_tp_cesta") REFERENCES "tipos_cestas" ("id"),
  CONSTRAINT "fk_solicitacoes_beneficiados"
    FOREIGN KEY ("cd_beneficiado") REFERENCES "beneficiados" ("cd_usuario")
);

CREATE TABLE "entregas" (
  "cd_entrega" SERIAL,
  "documento" varchar(150) NOT NULL,
  "dt_entrega" TIMESTAMP NOT NULL,
  "qtd_cestas" int DEFAULT NULL,
  "cd_solicitacao" int NOT NULL,
  CONSTRAINT "pk_entregas"
    PRIMARY KEY ("cd_entrega"),
  CONSTRAINT "fk_entregas_instituicoes"
    FOREIGN KEY ("cd_solicitacao") REFERENCES "solicitacoes" ("cd_solicitacao")
);

CREATE TABLE "recebimentos" (
  "cd_recebimento" SERIAL,
  "dt_recebimento" TIMESTAMP NOT NULL,
  "nf_cesta" varchar(150) DEFAULT NULL,
  "qtd_cestas" int NOT NULL,
  "cd_intencao" int NOT NULL,
  CONSTRAINT "pk_recebimentos"
    PRIMARY KEY ("cd_recebimento"),
  CONSTRAINT "fk_recebimentos_intencoes"
    FOREIGN KEY ("cd_intencao") REFERENCES "intencoes" ("cd_intencao")
);

CREATE TABLE "telefones_usuarios" (
  "cd_usuario" int NOT NULL,
  "numero" varchar(50) NOT NULL,
  CONSTRAINT "pk_telefones_usuarios"
    PRIMARY KEY ("cd_usuario","numero"),
  CONSTRAINT "fk_telefones_usuarios_telefones"
    FOREIGN KEY ("numero") REFERENCES "telefones" ("numero"),
  CONSTRAINT "fk_telefones_usuarios_usuarios"
    FOREIGN KEY ("cd_usuario") REFERENCES "usuarios" ("cd_usuario")
);

INSERT INTO "ufs" VALUES (1,'Espírito Santo','ES'),(2,'Minas Gerais','MG'),(3,'São Paulo','SP');

INSERT INTO "cidades" VALUES (1,'Alegre',1),(2,'Cachoeiro de Itapemirim',1),(3,'Belo Horizonte',2),(4,'Lavras',2);

INSERT INTO "bairros" VALUES (1,'Vila do Sul',1),(2,'Guararema',1),(3,'Maria Ortiz',2),(4,'Centro',2);

INSERT INTO "tipos_cestas" VALUES (1,'Cesta com 15 itens','Cesta A'),(2,'Cesta com 20 itens','Cesta B'),(3,'Cesta com 30 itens','Cesta C');

INSERT INTO "usuarios" VALUES (1,'maria','Maria','123'),(2,'julia','Julia','123'),(3,'joao','João','123'),(4,'jose','José','123'),(5,'anamaria','Ana Maria','123'),(6,'flavia','Flávia Helena','123'),(7,'manoel','Manoel','123'),(8,'valeria','Valeria','123'),(9,'inst1','Instituicao 1','123'),(10,'inst2','Instituicao 2','123');

INSERT INTO "administradores" VALUES (1,1),(2,2);

INSERT INTO "doadores" VALUES ('111.111.111-11','1990-01-01 00:00:00.000000',101,'Rua Dr. Brício Mesquita',7,3),('222.222.222-22','1990-01-02 00:00:00.000000',102,'Rua Dr. Brício Mesquita',8,4);

INSERT INTO "beneficiados" VALUES (2,20,'Rua Eng. Laura Lane',3,1),(4,40,'Rua Dr. Brício Mesquita',4,2),(10,5,'Rua 25 de Março',5,4),(7,53,'Rua 25 de Março',6,4);

INSERT INTO "instituicoes" VALUES ('11.111.111/1111-11',15,21,'Rua Maria Madalena',9,3),('22.222.222/2222-11',10,35,'Rua Eng. Enésimo Moraes',10,3);

INSERT INTO "intencoes" VALUES (1,'sZ96AA1q','2020-08-27',2,1,7,9,1),(2,'4ctN0y4P','2020-08-27',2,1,8,10,2);

INSERT INTO "solicitacoes" VALUES (1,'XOt9RE8e','2020-06-27',5,1,6,10,3),(2,'FlwrA41t','2020-06-27',7,1,5,9,1),(3,'yZryAMb9','2020-07-27',5,0,4,10,3),(4,'nivYvLP3','2020-07-27',7,0,3,9,3);

INSERT INTO "recebimentos" VALUES (1,'2020-08-27 13:54:00.288000','nf1',2,1),(2,'2020-08-27 13:54:00.288000','nf2',2,2);

INSERT INTO "entregas" VALUES (1,'Documento do Beneficiado 1','2020-08-27 13:54:00.288000',2,1),(2,'Documento do Beneficiado 2','2020-08-27 13:54:00.288000',2,2);