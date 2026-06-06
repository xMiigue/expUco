-- Se ejecuta al arrancar la app. Borramos las tablas para empezar siempre limpio.
DROP TABLE IF EXISTS inscripcion;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS usuario;

-- Usuarios de la plataforma.
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Eventos a los que la gente se inscribe.
CREATE TABLE evento (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    lugar VARCHAR(150) NOT NULL,
    fecha_inicio TIMESTAMP NOT NULL,          -- inicio del evento
    fecha_fin TIMESTAMP NOT NULL,             -- fin del evento
    inscripcion_inicio TIMESTAMP NOT NULL,    -- cuando ABREN las inscripciones
    inscripcion_fin TIMESTAMP NOT NULL,       -- cuando CIERRAN las inscripciones
    capacidad INT NOT NULL
);

-- Inscripciones: relaciona un usuario con un evento (id se genera solo).
CREATE TABLE inscripcion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    evento_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (evento_id) REFERENCES evento(id)
);
