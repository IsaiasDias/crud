CREATE DATABASE cadastro;

CREATE TABLE postagem (
  id int(11) NOT NULL AUTO_INCREMENT,
  conteudo text NOT NULL,
  idUsuario int(80) NOT NULL,
  primary key (id)
);

CREATE TABLE usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  login varchar(80) NOT NULL,
  senha varchar(8) NOT NULL,
  primary key (id)
);