-- Se ejecuta al arrancar (SQL Server). Borramos las tablas para empezar siempre limpio.
DROP TABLE IF EXISTS inscripcion;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS usuario;

-- Usuarios de la plataforma. El id lo genera SQL Server (IDENTITY) para el registro en vivo.
-- documento: numerico, obligatorio y unico (sirve para identificarse al entrar).
CREATE TABLE usuario (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    documento BIGINT NOT NULL UNIQUE
);

-- Eventos a los que la gente se inscribe.
CREATE TABLE evento (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    lugar VARCHAR(150) NOT NULL,
    fecha_inicio DATETIME2 NOT NULL,          -- inicio del evento
    fecha_fin DATETIME2 NOT NULL,             -- fin del evento
    inscripcion_inicio DATETIME2 NOT NULL,    -- cuando ABREN las inscripciones
    inscripcion_fin DATETIME2 NOT NULL,       -- cuando CIERRAN las inscripciones
    capacidad INT NOT NULL
);

-- Inscripciones: relaciona un usuario con un evento (id se genera solo con IDENTITY).
CREATE TABLE inscripcion (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    evento_id BIGINT NOT NULL,
    CONSTRAINT fk_inscripcion_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_inscripcion_evento FOREIGN KEY (evento_id) REFERENCES evento(id)
);
