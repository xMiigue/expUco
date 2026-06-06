-- Datos de demo. Usamos DATEADD sobre CURRENT_DATE para que las fechas sean
-- RELATIVAS A HOY y la demo funcione cualquier dia.
--   DATEADD('DAY', n, CURRENT_DATE)  -> hoy a medianoche + n dias
--   DATEADD('HOUR', h, ...)          -> le sumamos la hora del dia

-- 3 usuarios.
INSERT INTO usuario (id, nombre) VALUES (1, 'Miguel');
INSERT INTO usuario (id, nombre) VALUES (2, 'Edwin');
INSERT INTO usuario (id, nombre) VALUES (3, 'Farid');

-- 1) Conferencia IA: dentro de 2 dias 10:00-12:00, inscripcion ABIERTA y con cupos -> SE PUEDE inscribir.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (1, 'Conferencia IA', 'Auditorio Principal',
        DATEADD('HOUR', 10, DATEADD('DAY', 2, CURRENT_DATE)),
        DATEADD('HOUR', 12, DATEADD('DAY', 2, CURRENT_DATE)),
        DATEADD('DAY', -5, CURRENT_DATE),
        DATEADD('HOUR', 9, DATEADD('DAY', 2, CURRENT_DATE)),
        30);

-- 2) Webinar Datos: MISMO horario que la Conferencia, abierto y con cupos.
--    Sirve para demostrar CHOQUE DE HORARIO despues de inscribir la Conferencia.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (2, 'Webinar Datos', 'Sala Virtual',
        DATEADD('HOUR', 10, DATEADD('DAY', 2, CURRENT_DATE)),
        DATEADD('HOUR', 12, DATEADD('DAY', 2, CURRENT_DATE)),
        DATEADD('DAY', -5, CURRENT_DATE),
        DATEADD('HOUR', 9, DATEADD('DAY', 2, CURRENT_DATE)),
        30);

-- 3) Taller Cloud: capacidad 2 y lo dejamos LLENO (Edwin y Farid) -> SIN CUPOS.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (3, 'Taller Cloud', 'Laboratorio 2',
        DATEADD('HOUR', 14, DATEADD('DAY', 3, CURRENT_DATE)),
        DATEADD('HOUR', 16, DATEADD('DAY', 3, CURRENT_DATE)),
        DATEADD('DAY', -5, CURRENT_DATE),
        DATEADD('HOUR', 13, DATEADD('DAY', 3, CURRENT_DATE)),
        2);

-- 4) Hackathon: inscripcion YA CERRADA (cerro ayer) -> NO VIGENTE.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (4, 'Hackathon', 'Coworking',
        DATEADD('HOUR', 9, DATEADD('DAY', 5, CURRENT_DATE)),
        DATEADD('HOUR', 17, DATEADD('DAY', 5, CURRENT_DATE)),
        DATEADD('DAY', -10, CURRENT_DATE),
        DATEADD('DAY', -1, CURRENT_DATE),
        50);

-- 5) Bootcamp Robotica: dentro de 4 dias, horario distinto a la Conferencia, abierto y con cupos -> SE PUEDE inscribir.
INSERT INTO evento (id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad)
VALUES (5, 'Bootcamp Robotica', 'Laboratorio 1',
        DATEADD('HOUR', 9, DATEADD('DAY', 4, CURRENT_DATE)),
        DATEADD('HOUR', 13, DATEADD('DAY', 4, CURRENT_DATE)),
        DATEADD('DAY', -5, CURRENT_DATE),
        DATEADD('HOUR', 8, DATEADD('DAY', 4, CURRENT_DATE)),
        20);

-- Dejamos LLENO el Taller Cloud (capacidad 2) con Edwin y Farid -> demuestra SIN CUPOS.
INSERT INTO inscripcion (usuario_id, evento_id) VALUES (2, 3);
INSERT INTO inscripcion (usuario_id, evento_id) VALUES (3, 3);
