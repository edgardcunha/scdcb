DROP TABLE IF EXISTS entregas;
DROP TABLE IF EXISTS solicitacoes;
DROP TABLE IF EXISTS recebimentos;
DROP TABLE IF EXISTS intencoes;
DROP TABLE IF EXISTS tipos_cestas;
DROP TABLE IF EXISTS telefones_usuarios;
DROP TABLE IF EXISTS telefones;
DROP TABLE IF EXISTS instituicoes;
DROP TABLE IF EXISTS doadores;
DROP TABLE IF EXISTS beneficiados;
DROP TABLE IF EXISTS administradores;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS bairros;
DROP TABLE IF EXISTS cidades;
DROP TABLE IF EXISTS ufs;

CREATE TABLE ufs (
  sigla VARCHAR(2) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  CONSTRAINT pk_ufs
    PRIMARY KEY(sigla)
);

CREATE TABLE cidades (
   cdCidade SERIAL NOT NULL,
   nome VARCHAR(50) NOT NULL,
   sigla VARCHAR(2) NOT NULL,
   CONSTRAINT pk_cidades
      PRIMARY KEY(cdCidade),
   CONSTRAINT fk_cidades_ufs
      FOREIGN KEY(sigla)
      REFERENCES ufs(sigla)
);

CREATE TABLE bairros (
   cdBairro SERIAL NOT NULL,
   nome VARCHAR(50) NOT NULL,
   cdCidade int NOT NULL,
   CONSTRAINT pk_bairros
      PRIMARY KEY(cdBairro),
   CONSTRAINT fk_bairros_cidades
      FOREIGN KEY(cdCidade)
      REFERENCES cidades(cdCidade)
);

CREATE TABLE usuarios (
  cdUsuario SERIAL NOT NULL,
  nome VARCHAR(50) NOT NULL,
  login VARCHAR(50) NOT NULL,
  senha VARCHAR(50) NOT NULL,
  CONSTRAINT pk_usuarios
    PRIMARY KEY(cdUsuario)
);

CREATE TABLE administradores (
  cdAdmin INTEGER NOT NULL,
  permissoes INTEGER NOT NULL,
  cdUsuario INTEGER NOT NULL,
  CONSTRAINT pk_administradores
    PRIMARY KEY(cdAdmin, cdUsuario),
  CONSTRAINT fk_administradores_usuarios
    FOREIGN KEY(cdUsuario)
    REFERENCES usuarios(cdUsuario)
);

CREATE TABLE beneficiados (
  cdBeneficiado SERIAL NOT NULL UNIQUE,
  qtd_moradores INTEGER NOT NULL,
  num_casa INTEGER,
  rua VARCHAR(50),
  cdBairro INTEGER NOT NULL,
  cdUsuario INTEGER NOT NULL,
  CONSTRAINT pk_beneficiados
    PRIMARY KEY(cdBeneficiado, cdUsuario),
  CONSTRAINT fk_beneficiados_bairros
    FOREIGN KEY(cdBairro)
    REFERENCES bairros(cdBairro),
  CONSTRAINT fk_beneficiados_usuarios
    FOREIGN KEY(cdUsuario)
    REFERENCES usuarios(cdUsuario)
);

CREATE TABLE doadores (
  cdDoador SERIAL NOT NULL UNIQUE,
  cpf VARCHAR(14) NOT NULL,
  dtNascimento date NOT NULL,
  telefone VARCHAR(50) NOT NULL,
  rua VARCHAR(50),
  numero INTEGER,
  cdBairro INTEGER NOT NULL,
  cdUsuario INTEGER NOT NULL,
  CONSTRAINT pk_doador
    PRIMARY KEY(cdDoador, cdUsuario),
  CONSTRAINT fk_doadores_bairros
    FOREIGN KEY(cdBairro)
    REFERENCES bairros(cdBairro),
  CONSTRAINT fk_doadores_usuarios
    FOREIGN KEY(cdUsuario)
    REFERENCES usuarios(cdUsuario)
);

CREATE TABLE instituicoes (
  cdInstituicao SERIAL NOT NULL UNIQUE,
  cnpj VARCHAR(50) NOT NULL,
  cdUsuario INTEGER NOT NULL,
  CONSTRAINT pk_instituicoes
    PRIMARY KEY(cdInstituicao, cdUsuario),
  CONSTRAINT fk_instituicoes_usuarios
    FOREIGN KEY(cdUsuario)
    REFERENCES usuarios(cdUsuario)
);

CREATE TABLE telefones (
  cdTelefone SERIAL NOT NULL,
  numero VARCHAR(50),
  CONSTRAINT pk_telefones
    PRIMARY KEY(cdTelefone)
);

CREATE TABLE telefones_usuarios (
  cdTelefone INTEGER NOT NULL,
  cdUsuario INTEGER NOT NULL,
  CONSTRAINT pk_telefones_usuarios
    PRIMARY KEY(cdTelefone, cdUsuario),
  CONSTRAINT fk_telefones_usuarios
    FOREIGN KEY(cdUsuario)
    REFERENCES usuarios(cdUsuario)
);

CREATE TABLE tipos_cestas (
  cdTipoCesta SERIAL NOT NULL,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  CONSTRAINT pk_tipos_cestas
    PRIMARY KEY(cdTipoCesta)
);

CREATE TABLE intencoes (
  cdIntencao SERIAL NOT NULL,
  dtIntencao DATE NOT NULL,
  status INTEGER NOT NULL,
  cdTipoCesta INTEGER NOT NULL,
  cdDoador INTEGER NOT NULL,
  cdInstituicao INTEGER NOT NULL,
  CONSTRAINT pk_intencoes
    PRIMARY KEY(cdIntencao),
  CONSTRAINT fk_intencoes_tipos_cestas
    FOREIGN KEY(cdTipoCesta)
    REFERENCES tipos_cestas(cdTipoCesta),
  CONSTRAINT fk_intencoes_doadores
    FOREIGN KEY(cdDoador)
    REFERENCES doadores(cdDoador),
  CONSTRAINT fk_intencoes_instituicoes
      FOREIGN KEY(cdInstituicao)
      REFERENCES instituicoes(cdInstituicao)
);

CREATE TABLE recebimentos (
   cdRecebimento SERIAL NOT NULL,
   dtRecebimento DATE NOT NULL,
   qtdCestas INTEGER NOT NULL,
   nfCestas VARCHAR(100) NULL,
   cdIntencao INTEGER NOT NULL,
   CONSTRAINT pk_recebimentos
      PRIMARY KEY(cdRecebimento),
   CONSTRAINT fk_recebimentos_intencoes
      FOREIGN KEY(cdIntencao)
      REFERENCES intencoes(cdIntencao)
);

CREATE TABLE solicitacoes (
  cdSolicitacao SERIAL NOT NULL,
  dtSolicitacao DATE NOT NULL,
  qtdCestas INTEGER NOT NULL,
  status INTEGER NOT NULL,
  cdTipoCesta INTEGER NOT NULL,
  cdInstituicao INTEGER NOT NULL,
  cdbeneficiado INTEGER NOT NULL,
  CONSTRAINT pk_solicitacoes
    PRIMARY KEY(cdSolicitacao),
  CONSTRAINT fk_solicitacoes_tipos_cestas
    FOREIGN KEY(cdTipoCesta)
    REFERENCES tipos_cestas(cdTipoCesta),
  CONSTRAINT fk_solicitacoes_beneficiados
    FOREIGN KEY(cdBeneficiado)
    REFERENCES beneficiados(cdBeneficiado),
  CONSTRAINT fk_solicitacoes_instituicoes
    FOREIGN KEY(cdInstituicao)
    REFERENCES instituicoes(cdInstituicao)
);

CREATE TABLE entregas (
  cdEntrega SERIAL NOT NULL,
  dtEntrega DATETIME NOT NULL,
  documento VARCHAR(100) NOT NULL,
  cdSolicitacao INTEGER NOT NULL,
  CONSTRAINT pk_entregas
    PRIMARY KEY(cdEntrega),
  CONSTRAINT fk_entregas_solicitacoes
    FOREIGN KEY(cdSolicitacao)
    REFERENCES solicitacoes(cdSolicitacao)
);