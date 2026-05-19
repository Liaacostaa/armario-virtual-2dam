-- creacion de la base de datos
CREATE DATABASE armario_virtual_oficial;

USE armario_virtual_oficial;

-- creacion del usuario
CREATE USER 'user_armario_of'@'localhost' IDENTIFIED BY '1111';
GRANT ALL PRIVILEGES ON armario_virtual_oficial.* TO 'user_armario_of'@'localhost';
FLUSH PRIVILEGES;

-- creacion de tablas

CREATE TABLE usuarios (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre            VARCHAR(100) NOT NULL,
  email             VARCHAR(150) NOT NULL UNIQUE,
  contrasena        VARCHAR(255) NOT NULL,
  foto_perfil       VARCHAR(500) DEFAULT NULL,
  fecha_creacion    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE prendas (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_usuario      BIGINT       NOT NULL,
  nombre          VARCHAR(150) NOT NULL,
  tipo            ENUM('camiseta','camisa','pantalon','falda','vestido','chaqueta','abrigo','zapatos','zapatillas','accesorio','otro') NOT NULL,
  color           VARCHAR(50)  NOT NULL,
  ocasion         ENUM('casual','formal','deporte','fiesta','trabajo') NOT NULL,
  temporada       ENUM('primavera','verano','otono','invierno','todas') NOT NULL DEFAULT 'todas',
  foto            VARCHAR(500) DEFAULT NULL,
  fecha_creacion  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_prenda_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE outfits (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_usuario      BIGINT       NOT NULL,
  nombre          VARCHAR(150) NOT NULL,
  ocasion         ENUM('casual','formal','deporte','fiesta','trabajo') NOT NULL,
  favorito        BOOLEAN      NOT NULL DEFAULT FALSE,
  notas           TEXT         DEFAULT NULL,
  fecha_creacion  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_outfit_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE outfit_prendas (
  id_outfit  BIGINT NOT NULL,
  id_prenda  BIGINT NOT NULL,
  PRIMARY KEY (id_outfit, id_prenda),
  CONSTRAINT fk_op_outfit FOREIGN KEY (id_outfit) REFERENCES outfits(id) ON DELETE CASCADE,
  CONSTRAINT fk_op_prenda FOREIGN KEY (id_prenda) REFERENCES prendas(id) ON DELETE CASCADE
);

CREATE TABLE calendario (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_usuario      BIGINT       NOT NULL,
  id_outfit       BIGINT       NOT NULL,
  fecha           DATE         NOT NULL,
  nota            VARCHAR(300) DEFAULT NULL,
  CONSTRAINT fk_cal_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE,
  CONSTRAINT fk_cal_outfit  FOREIGN KEY (id_outfit)  REFERENCES outfits(id)  ON DELETE CASCADE
);

CREATE INDEX idx_prendas_usuario  ON prendas(id_usuario);
CREATE INDEX idx_prendas_tipo     ON prendas(tipo);
CREATE INDEX idx_prendas_ocasion  ON prendas(ocasion);
CREATE INDEX idx_outfits_usuario  ON outfits(id_usuario);
CREATE INDEX idx_outfits_favorito ON outfits(favorito);
CREATE INDEX idx_calendario_fecha ON calendario(fecha);

SHOW TABLES;
