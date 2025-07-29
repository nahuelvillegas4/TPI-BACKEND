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


INSERT INTO tarifa_km (vol_max, peso_max, tarifa) VALUES (5.0, 500.0, 120.0);
INSERT INTO tarifa_km (vol_max, peso_max, tarifa) VALUES (15.0, 2000.0, 100.0);
INSERT INTO tarifa_km (vol_max, peso_max, tarifa) VALUES (30.0, 5000.0, 85.0);
INSERT INTO tarifa_km (vol_max, peso_max, tarifa) VALUES (60.0, 10000.0, 70.0);

INSERT INTO Tarifa_Base (tarifa) VALUES
  (200.0),  -- Monto base de transporte
  (20.0);   -- Costo de estadía por día