-- Datos de demo (SQL Server). Fechas RELATIVAS A HOY con DATEADD + GETDATE(),
-- ancladas a la medianoche de hoy:  CAST(CAST(GETDATE() AS DATE) AS DATETIME2)
-- Asi la demo de las reglas funciona cualquier dia.
--
-- NO precargamos usuarios: las personas se crean al inscribirse desde el formulario.

-- 1) Conferencia IA: dentro de 2 dias 10:00-12:00, inscripcion ABIERTA y con cupos -> SE PUEDE inscribir.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (1, 'Conferencia IA', 'Auditorio Monsenor Flavio Zapata',
        DATEADD(HOUR, 10, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(HOUR, 12, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(DAY, -5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        DATEADD(HOUR, 9, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        30);

-- 2) Fiesta de la UCO: MISMO horario que la Conferencia, abierto y con cupos -> demuestra CHOQUE DE HORARIO.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (2, 'Fiesta de la UCO', 'Sala multimedia',
        DATEADD(HOUR, 10, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(HOUR, 12, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(DAY, -5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        DATEADD(HOUR, 9, DATEADD(DAY, 2, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        30);

-- 3) Celebracion Diseno Orientado a Objetos: capacidad 2 -> sirve para demostrar SIN CUPOS.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (3, 'Celebracion Diseno Orientado a Objetos', 'Salon EDC',
        DATEADD(HOUR, 14, DATEADD(DAY, 3, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(HOUR, 16, DATEADD(DAY, 3, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(DAY, -5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        DATEADD(HOUR, 13, DATEADD(DAY, 3, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        2);

-- 4) Hackathon: inscripcion YA CERRADA (cerro ayer) -> NO VIGENTE.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (4, 'Hackathon', 'Biblioteca',
        DATEADD(HOUR, 9, DATEADD(DAY, 5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(HOUR, 17, DATEADD(DAY, 5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(DAY, -10, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        DATEADD(DAY, -1, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        50);

-- 5) Bootcamp Robotica: dentro de 4 dias, horario distinto, abierto y con cupos -> SE PUEDE inscribir.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (5, 'Bootcamp Robotica', 'Laboratorio de productividad',
        DATEADD(HOUR, 9, DATEADD(DAY, 4, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(HOUR, 13, DATEADD(DAY, 4, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        DATEADD(DAY, -5, CAST(CAST(GETDATE() AS DATE) AS DATETIME2)),
        DATEADD(HOUR, 8, DATEADD(DAY, 4, CAST(CAST(GETDATE() AS DATE) AS DATETIME2))),
        20);
