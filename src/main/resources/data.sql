-- Insertar datos para perfil 1
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (1, 'Perfil1', '1234567890', 100);

-- Insertar datos para perfil 2
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (2, 'Perfil2', '9876543210', 200);

-- Insertar datos para perfil 3
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (3, 'Perfil3', '5555555555', 150);

-- Insertar datos para perfil 4
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (4, 'Perfil4', '6666666666', 300);

-- Insertar datos para perfil 5
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (5, 'Perfil5', '7777777777', 250);

-- Insertar datos para perfil 6
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (6, 'Perfil6', '8888888888', 400);

-- Insertar datos para perfil 7
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (7, 'Perfil7', '9999999999', 350);

-- Insertar datos para perfil 8
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (8, 'Perfil8', '1111111111', 500);

-- Insertar datos para perfil 9
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (9, 'Perfil9', '2222222222', 450);

-- Insertar datos para perfil 10
INSERT INTO perfil (id_perfil, name, telefono, puntos_premios)
VALUES (10, 'Perfil10', '3333333333', 600);

-- Insertar datos para usuario 1
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (1, 'Usuario1', 'usuario1@example.com', '1990-01-01', 'password1', 1);

-- Insertar datos para usuario 2
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (2, 'Usuario2', 'usuario2@example.com', '1995-02-02', 'password2', 2);

-- Insertar datos para usuario 3
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (3, 'Usuario3', 'usuario3@example.com', '2000-03-03', 'password3', 3);

-- Insertar datos para usuario 4
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (4, 'Usuario4', 'usuario4@example.com', '2005-04-04', 'password4', 4);

-- Insertar datos para usuario 5
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (5, 'Usuario5', 'usuario5@example.com', '2010-05-05', 'password5', 5);

-- Insertar datos para usuario 6
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (6, 'Usuario6', 'usuario6@example.com', '2015-06-06', 'password6', 6);

-- Insertar datos para usuario 7
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (7, 'Usuario7', 'usuario7@example.com', '2020-07-07', 'password7', 7);

-- Insertar datos para usuario 8
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (8, 'Usuario8', 'usuario8@example.com', '2025-08-08', 'password8', 8);

-- Insertar datos para usuario 9
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (9, 'Usuario9', 'usuario9@example.com', '2030-09-09', 'password9', 9);

-- Insertar datos para usuario 10
INSERT INTO usuario (id_usuario, name, email, birthdate, password, id_perfil)
VALUES (10, 'Usuario10', 'usuario10@example.com', '2035-10-10', 'password10', 10);

INSERT INTO libro (id_libro, titulo, autor)
VALUES
    (1, 'El señor de los anillos', 'J.R.R. Tolkien'),
    (2, 'Cien años de soledad', 'Gabriel García Márquez'),
    (3, 'Harry Potter y la piedra filosofal', 'J.K. Rowling'),
    (4, '1984', 'George Orwell'),
    (5, 'Orgullo y prejuicio', 'Jane Austen'),
    (6, 'Don Quijote de la Mancha', 'Miguel de Cervantes'),
    (7, 'Crimen y castigo', 'Fyodor Dostoevsky'),
    (8, 'El principito', 'Antoine de Saint-Exupéry'),
    (9, 'Matar un ruiseñor', 'Harper Lee'),
    (10, 'El gran Gatsby', 'F. Scott Fitzgerald');