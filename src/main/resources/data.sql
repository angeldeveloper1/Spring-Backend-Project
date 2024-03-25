-- Insertar perfiles
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios) VALUES (1, 'Estudiante', '1234567890', 100);
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios) VALUES (2, 'Profesor', '9876543210', 150);
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios) VALUES (3, 'Investigador', '5555555555', 200);

-- Insertar usuarios
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil) VALUES (1, 'Juan Perez', 'juan@example.com', '1990-05-15', 'password123', 1);
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil) VALUES (2, 'Maria Garcia', 'maria@example.com', '1995-08-20', 'password456', 2);
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil) VALUES (3, 'Carlos Rodriguez', 'carlos@example.com', '1985-03-10', 'password789', 3);

-- Insertar libros
INSERT INTO libro (id_libro, titulo, autor, id_usuario) VALUES (1, 'El Principito', 'Antoine de Saint-Exupéry', 1);
INSERT INTO libro (id_libro, titulo, autor, id_usuario) VALUES (2, 'Cien años de soledad', 'Gabriel García Márquez', 2);
INSERT INTO libro (id_libro, titulo, autor, id_usuario) VALUES (3, '1984', 'George Orwell', 3);

-- Insertar préstamos
INSERT INTO prestamo (id_prestamo, devuelto, fecha_devolucion, fecha_expiracion, id_usuario, id_libro) VALUES (1, false, NULL, '2024-03-20', 1, 1);
INSERT INTO prestamo (id_prestamo, devuelto, fecha_devolucion, fecha_expiracion, id_usuario, id_libro) VALUES (2, true, '2024-03-10', '2024-03-15', 2, 2);
INSERT INTO prestamo (id_prestamo, devuelto, fecha_devolucion, fecha_expiracion, id_usuario, id_libro) VALUES (3, false, NULL, '2024-03-25', 3, 3);