INSERT INTO `usuarios` (`nombre`, `email`, `password`, `numero_telefono`, `roles`) VALUES
('Carlos Martínez', 'carlos.martinez@example.com', '333', '123456789', 'USER'),
('Ana López', 'ana.lopez@example.com', '111', '987654321', 'USER'),
('Elia', 'elia@a.com', '123', '567890123', 'ADMIN');

INSERT INTO `artistas` (`nombre`, `especialidad`) VALUES
('Jorge Pérez', 'Realismo'),
('María Gómez', 'Acuarela'),
('Luis Fernández', 'Minimalismo');

INSERT INTO `citas` (`id_usuario`, `id_artista`, `fecha`, `descripcion`) VALUES
(1, 1, '2024-12-15 10:00:00', 'Diseño de tatuaje de realismo en brazo'),
(2, 2, '2024-12-16 14:00:00', 'Tatuaje de acuarela en la espalda'),
(3, 3, '2024-12-17 09:00:00', 'Tatuaje minimalista en la muñeca'),
(1, 3, '2024-12-18 11:30:00', 'Tatuaje pequeño de minimalismo en el tobillo'),
(2, 1, '2024-12-19 15:00:00', 'Diseño de tatuaje realista en el pecho');