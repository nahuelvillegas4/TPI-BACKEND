INSERT INTO ciudad (nombre, latitud, longitud) VALUES
  ('Córdoba', -31.4201, -64.1888),
  ('Mendoza', -32.8895, -68.8458),
  ('Rosario', -32.9468, -60.6393),
  ('Salta', -24.7821, -65.4232),
  ('La Plata', -34.9214, -57.9544),
  ('Mar del Plata', -38.0055, -57.5426),
  ('San Juan', -31.5433, -68.5364),
  ('Neuquén', -38.9516, -68.0590),
  ('Bahía Blanca', -38.7196, -62.2726),
  ('Posadas', -27.3628, -55.8960);


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