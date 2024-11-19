CREATE TABLE Consultas (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          fecha DATE NOT NULL,
                          hora TIME NOT NULL,
                          veterinario_id BIGINT NOT NULL,
                          mascota_id BIGINT NOT NULL,
                          cliente_id BIGINT NOT NULL,
                          activo VARCHAR(50) DEFAULT true,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id),
                          FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
                          FOREIGN KEY (cliente_id) REFERENCES usuarios(id)
);
