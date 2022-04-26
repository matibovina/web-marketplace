/* Populate tabla clientes */


INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Raul', 'Gomez', 'ra1ulgomez@gmail.com', 666554233, '2000-08-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Pedro', 'Gil', 'pedro2gil@gmail.com', 665433222, '2012-03-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Claudia', 'Pala', 'cla3udiapala@gmail.com', 667744552, '2011-01-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Antonio', 'Ahumada', 'an4tonioahumada@gmail.com', 456347123, '2001-04-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Mariano', 'Velez', 'marian1ovelez@gmail.com', 67456123, '2002-12-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Juan', 'Jimenez', 'juanj1imenez@gmail.com', 698123678, '2004-01-12', '2022-03-03' );
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Florencia', 'Freinz', 'flo1renciafreinz@gmail.com', 612745190, '1988-09-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Lucila', 'Gonzalez', 'luci1lagonzalez@gmail.com', 609088077, '1998-09-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Franco', 'Bovina', 'franco1bovina@gmail.com', 766646590, '1976-10-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Santiago', 'Perez', 'santi1agoperez@gmail.com', 900765888, '1990-11-12', '2022-03-03'); 
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Raul2', 'Gomez', 'raulgomez@gmail.com', 666554233, '2000-08-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Pedro2', 'Gil', 'pedrogil@gmail.com', 665433222, '2012-03-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Claudia3', 'Pala', 'claudiapala@gmail.com', 667744552, '2011-01-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Antonio1', 'Ahumada', 'antonioahumada@gmail.com', 456347123, '2001-04-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Mariano2', 'Velez', 'marianovelez@gmail.com', 67456123, '2002-12-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Juan1', 'Jimenez', 'juanjimenez@gmail.com', 698123678, '2004-01-12', '2022-03-03' );
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Florencia2', 'Freinz', 'florenciafreinz@gmail.com', 612745190, '1988-09-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Lucila1', 'Gonzalez', 'lucilagonzalez@gmail.com', 609088077, '1998-09-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Franco2', 'Bovina', 'francobovina@gmail.com', 766646590, '1976-10-12', '2022-03-03');
INSERT INTO clientes (nombre, apellido, email, telefono, fecha_nacimiento, create_at) VALUES ('Santiago1', 'Perez', 'santiagoperez@gmail.com', 900765888, '1990-11-12', '2022-03-03'); 

INSERT INTO users (email, username, password, enabled) VALUES ('admin@admin.com', 'matiasadmin', '$2a$10$q7.uUSPRO8Ke4gHGTmM6qeMPHaR15j.Fzjh6MebRDPz7uJof6E2KC', 1);
INSERT INTO users (email, username, password, enabled) VALUES ('user@user.com', 'matiasuser', '$2a$10$efcOZo.t0JTbmhQghpP.hO//0rLGm39T.Y4GphJc.wgnlkLGLQ5m6', 1);


INSERT INTO authorities (authority) VALUES ('ROLE_USER');
INSERT INTO authorities (authority) VALUES ('ROLE_ADMIN');

INSERT INTO users_authorities (user_id, role_id) VALUES (1, 1);
INSERT INTO users_authorities (user_id, role_id) VALUES (1, 2);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 1);



INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ( 'vaso',  null, 5, true, null, 15.50);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('plato', null, 5, true, null, 20);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('tenedor', null, 5, true, null, 5);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('cuchillo', null, 5, true, null, 6);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('cuchara', null, 5, true, null, 4.5);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('olla', null, 5, true, null, 55.50);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('secaplatos', null, 5, true, null, 4.20);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('sarten', null, 5, true, null, 32.15);
INSERT INTO productos (nombre, codigo, existencias, is_disponible, imagen, precio) VALUES ('colador', null, 5, true, null, 12.80);