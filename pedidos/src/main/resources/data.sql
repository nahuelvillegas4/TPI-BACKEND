INSERT INTO CAMION (capacidad_peso, capacidad_volumen, disponible) VALUES
  (10000, 40, TRUE),
  (8000, 35, TRUE),
  (12000,50, FALSE),
  (9000, 45, TRUE),
  (11000,48, TRUE),
  (7000, 30, FALSE),
  (9500, 42, TRUE),
  (10500,47, TRUE),
  (8500, 38, FALSE),
  (11500,49, TRUE);

INSERT INTO cliente (nombre, email, password) VALUES
  ('Juan Pérez','juan.perez@example.com','pass123'),
  ('María Gómez','maria.gomez@example.com','pwd456'),
  ('Luis Rodríguez','luis.rodriguez@example.com','qwerty789'),
  ('Ana Fernández','ana.fernandez@example.com','abc12345'),
  ('Carlos Díaz','carlos.diaz@example.com','securepwd'),
  ('Lucía Martínez','lucia.martinez@example.com','marti2025'),
  ('Pedro Sánchez','pedro.sanchez@example.com','psanchez'),
  ('Sofía López','sofia.lopez@example.com','lopez!@#'),
  ('Diego Torres','diego.torres@example.com','torres321'),
  ('Valentina Ruiz','valentina.ruiz@example.com','ruizRuiz');


INSERT INTO deposito (ciudad_id, direccion, latitud, longitud) VALUES
  (1, 'Av. Paz 123', -31.4201, -64.1888),
  (2, 'Calle Chile 456', -32.8895, -68.8458),
  (3, 'Belgrano 789', -32.9468, -60.6393),
  (4, 'Libertad 101', -24.7821, -65.4232),
  (5, 'Dorrego 202', -34.9214, -57.9544),
  (6, 'San Martín 303', -38.0055, -57.5426),
  (7, 'Rivadavia 404', -31.5433, -68.5364),
  (8, 'Mitre 505', -38.9516, -68.0590),
  (9, 'Brown 606', -38.7196, -62.2726),
  (10,'9 de Julio 707', -27.3628, -55.8960);



INSERT INTO contenedor (peso, volumen, estado, cliente_id) VALUES
  ( 500.0,  5.0, 'en_espera_solicitud', 1),
  (1200.5, 12.3, 'en_espera_solicitud', 2),
  ( 800.0,  8.7, 'en_espera_solicitud', 3),
  (1500.2, 15.0, 'en_espera_solicitud', 4),
  (2000.0, 20.5, 'en_espera_solicitud', 5),
  ( 950.0,  9.5, 'en_espera_solicitud', 6),
  (1750.8, 18.2, 'en_espera_solicitud', 7),
  (1100.0, 11.1, 'en_espera_solicitud', 8),
  ( 670.4,  6.7, 'en_espera_solicitud', 9),
  (2200.0, 22.0, 'en_espera_solicitud', 10);


INSERT INTO solicitud (
  contenedor_id,
  ciudad_origen_id,
  ciudad_destino_id,
  deposito_id,
  camion_id,
  costo_estimado,
  tiempo_estimado_horas
) VALUES
  (1,  1,  2,  2,  NULL, NULL, NULL),
  (2,  3,  5,  5,  NULL, NULL, NULL),
  (3,  2,  4,  4,  NULL, NULL, NULL),
  (4,  6,  1,  1,  NULL, NULL, NULL),
  (5,  7,  3,  3,  NULL, NULL, NULL),
  (6,  4,  8,  8,  NULL, NULL, NULL),
  (7,  9, 10, 10,  NULL, NULL, NULL),
  (8,  5,  7,  7,  NULL, NULL, NULL),
  (9, 10,  9,  9,  NULL, NULL, NULL),
  (10, 8,  6,  6,  NULL, NULL, NULL);
