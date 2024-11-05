use mi_clinica;
CREATE TABLE mascotas
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    tipoAnimal VARCHAR(50)  NOT NULL,
    iddueño    BIGINT       NOT NULL,

    CONSTRAINT fk_mascotas_iddueño FOREIGN KEY (iddueño) REFERENCES usuarios (id)
);

