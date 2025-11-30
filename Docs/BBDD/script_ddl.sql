CREATE DATABASE IF NOT EXISTS armario_virtual;
USE armario_virtual;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE prenda (
    id_prenda INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    tipo VARCHAR(40) NOT NULL,
    color VARCHAR(30),
    temporada VARCHAR(20),
    imagen_url VARCHAR(255),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE outfit (
    id_outfit INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    ocasion VARCHAR(30),
    temporada VARCHAR(20),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE outfit_prenda (
    id_outfit INT NOT NULL,
    id_prenda INT NOT NULL,
    rol VARCHAR(30),
    PRIMARY KEY (id_outfit, id_prenda),
    FOREIGN KEY (id_outfit) REFERENCES outfit(id_outfit),
    FOREIGN KEY (id_prenda) REFERENCES prenda(id_prenda)
);

CREATE TABLE evento (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha DATE NOT NULL,
    ocasion VARCHAR(30),
    id_outfit INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_outfit) REFERENCES outfit(id_outfit)
);

CREATE TABLE favorito (
    id_favorito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_outfit INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_outfit) REFERENCES outfit(id_outfit)
);
