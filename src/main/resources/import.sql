/* Populate tabla clientes */



INSERT INTO users (email, username, password, enabled) VALUES ('admin@admin.com', 'matiasadmin', '$2a$10$q7.uUSPRO8Ke4gHGTmM6qeMPHaR15j.Fzjh6MebRDPz7uJof6E2KC', 1);
INSERT INTO users (email, username, password, enabled) VALUES ('user@user.com', 'matiasuser', '$2a$10$efcOZo.t0JTbmhQghpP.hO//0rLGm39T.Y4GphJc.wgnlkLGLQ5m6', 1);



INSERT INTO authorities (authority) VALUES ('ROLE_USER');
INSERT INTO authorities (authority) VALUES ('ROLE_ADMIN');

INSERT INTO users_authorities (user_id, role_id) VALUES (1, 1);
INSERT INTO users_authorities (user_id, role_id) VALUES (1, 2);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 1);


INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at, user_id) VALUES ('Raul', 'Gomez', 'ra1ulgomez@gmail.com', 666554233, '2000-08-12', '2022-03-03', 1);
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at, user_id) VALUES ('Pedro', 'Gil', 'pedro2gil@gmail.com', 665433222, '2012-03-12', '2022-03-03', 2);



INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ( 'vaso',  null, 5, true, null, 15.50);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('plato', null, 5, true, null, 20);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('tenedor', null, 5, true, null, 5);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('cuchillo', null, 5, true, null, 6);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('cuchara', null, 5, true, null, 4.5);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('olla', null, 5, true, null, 55.50);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('secaplatos', null, 5, true, null, 4.20);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('sarten', null, 5, true, null, 32.15);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('colador', null, 5, true, null, 12.80);


INSERT INTO carrito (cantidad, cliente_id, producto_id) VALUES (2, 1, 1);
INSERT INTO carrito (cantidad, cliente_id, producto_id) VALUES (3, 1, 2);
INSERT INTO carrito (cantidad, cliente_id, producto_id) VALUES (1, 1, 3);