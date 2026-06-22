-- PCer2 - Poblamiento de datos para defensa
-- IMPORTANTE:
-- 1. Ejecutar primero 00_crear_bases_pcer2.sql.
-- 2. Levantar una vez todos los microservicios para que JPA cree las tablas.
-- 3. Detener o dejar corriendo los servicios.
-- 4. Ejecutar este archivo en phpMyAdmin, DBeaver o consola MySQL.
--
-- Este script usa INSERT IGNORE para poder ejecutarlo mas de una vez.

-- =========================================================
-- service-auth / pc_seguridad
-- =========================================================
USE pc_seguridad;

INSERT IGNORE INTO roles (id, nombre_rol) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- No se inserta usuario por SQL porque la contrasena debe quedar con BCrypt.
-- Crear usuario admin por Postman:
-- POST http://localhost:8080/api/v1/auth/registrar
-- {
--   "nombreUsuario": "admin",
--   "contrasena": "1234",
--   "correo": "admin@pcer2.cl",
--   "roles": [{ "id": 1 }]
-- }

-- =========================================================
-- service-clientes / pc_clientes
-- =========================================================
USE pc_clientes;

INSERT IGNORE INTO cliente
(id, nombre, apellido, rut, telefono, email, fecha_registro, ordenes_totales)
VALUES
(1, 'Maria', 'Gonzalez', '18765432-1', 987654321, 'maria.gonzalez@gmail.com', '2026-05-15', 1),
(2, 'Carlos', 'Perez', '17654321-5', 912345678, 'carlos.perez@gmail.com', '2026-05-16', 1),
(3, 'Ana', 'Rojas', '16543210-9', 923456789, 'ana.rojas@gmail.com', '2026-05-17', 0);

-- =========================================================
-- service-equipo / pc_equipos
-- =========================================================
USE pc_equipos;

INSERT IGNORE INTO equipo
(id, tipo_equipo, marca, modelo_cpu, placa_madre, ram_cantidad, ram_frecuencia,
 almacen_cantidad, tipo_almacen, numeroserie, veces_reparado, cliente_id)
VALUES
(1, 'Notebook', 'Lenovo', 'Ryzen 5 8540U', 'NS7989', 32, 5600, 2048, 'SSD', '21CR0006US', 0, 1),
(2, 'Desktop', 'HP', 'Intel Core i5-12400', 'HP-B660', 16, 3200, 1024, 'SSD', 'HPDESK-2026-001', 1, 2),
(3, 'Notebook', 'Asus', 'Intel Core i7-1165G7', 'ASUS-X515', 16, 3200, 512, 'SSD', 'ASUSNB-2026-002', 2, 1);

-- =========================================================
-- service-servicio / pc_servicio
-- =========================================================
USE pc_servicio;

INSERT IGNORE INTO servicios
(id, nombre, descripcion, precio_base, activo)
VALUES
(1, 'Mantencion completa de equipo', 'Limpieza interna, cambio de pasta termica y revision general.', 35000, 1),
(2, 'Instalacion de software', 'Instalacion y activacion de software solicitado por el cliente.', 18000, 1),
(3, 'Diagnostico tecnico', 'Revision inicial para detectar fallas de hardware o software.', 12000, 1);

-- =========================================================
-- service-software / pc_software
-- =========================================================
USE pc_software;

INSERT IGNORE INTO licencias_software
(id, nombre, marca, version, serial)
VALUES
(1, 'Windows 11 Pro', 'Microsoft', '11 Pro', 'WIN11-PRO-ABC123'),
(2, 'Office', 'Microsoft', '2024 Pro Plus', '57892-27864-72484-33657'),
(3, 'WinRAR', 'RARLab', '7.22', 'A4A5W-33X5N-E845D');

-- =========================================================
-- service-mantenimiento / pc_mantenimiento
-- =========================================================
USE pc_mantenimiento;

INSERT IGNORE INTO productos_mantenimiento
(id, nombre, categoria, descripcion, stock_actual, precio_unitario)
VALUES
(1, 'Pasta Termica Arctic Silver 5', 'Solucion Termica', 'Alta densidad, 2.7 gr. Pasta disipadora.', 3, 5990),
(2, 'Alcohol Isopropilico', 'Limpieza', 'Alcohol para limpieza de componentes electronicos.', 20, 3990),
(3, 'Kit Destornilladores Precision', 'Herramienta', 'Kit de herramientas para notebooks y desktop.', 8, 12990);

-- =========================================================
-- service-hardware / pc_hardware
-- =========================================================
USE pc_hardware;

INSERT IGNORE INTO hardware
(id, nombre, tipo, marca, cantidad, detalles, estado, precio)
VALUES
(1, 'Memoria RAM DDR4 16GB', 'RAM', 'Kingston', 10, 'Modulo DDR4 3200 MHz.', 'Nuevo', 29990),
(2, 'SSD NVMe 1TB', 'Almacenamiento', 'Western Digital', 6, 'Unidad NVMe Gen3.', 'Nuevo', 54990),
(3, 'Adaptador PCI-X1 a NVME', 'Adaptador', 'Ugreen', 4, 'Adaptador para expansion de almacenamiento.', 'Nuevo', 24990);

-- =========================================================
-- service-descuento / pc_descuento
-- =========================================================
USE pc_descuento;

INSERT IGNORE INTO codigo_descuento
(id, codigo, descripcion, porcentaje_descuento, fecha_inicio, fecha_fin, activo)
VALUES
(1, 'PROMO2026', 'Descuento de bienvenida para el anio 2026', 15, '2026-01-01', '2026-12-31', 1),
(2, 'DIADELPADRE2026', 'Promocion especial de junio', 20, '2026-06-01', '2026-06-30', 1),
(3, 'VENCIDO2025', 'Cupon vencido para probar validacion', 10, '2025-01-01', '2025-12-31', 1);

-- =========================================================
-- service-orden / pc_orden
-- =========================================================
USE pc_orden;

INSERT IGNORE INTO ordenes_trabajo
(id, cliente_id, equipo_id, servicio_id, software_id, producto_mantenimiento_id,
 descripcion_problema, fecha_ingreso, fecha_entrega_estimada, estado, precio_total)
VALUES
(1, 1, 1, 1, 1, 1, 'Equipo muy sucio. Presenta estrangulamiento termico en GPU y CPU.', '2026-05-15', '2026-05-18', 'Estetica 8/10 - Funcionalidad 4/10', 35000),
(2, 2, 2, 2, 2, 2, 'Cliente solicita instalacion y activacion de software de oficina.', '2026-05-16', '2026-05-19', 'Ingresada', 18000),
(3, 1, 3, 3, 3, 3, 'Equipo presenta lentitud y requiere diagnostico general.', '2026-05-17', '2026-05-20', 'En revision', 12000);

-- =========================================================
-- service-voucher / pc_voucher
-- =========================================================
USE pc_voucher;

INSERT IGNORE INTO voucher_trabajo
(id, orden_id, fecha_emision, metodo_pago, total, cantidad_servicios, estado, observacion, codigo_descuento)
VALUES
(1, 1, '2026-05-18', 'Transferencia', 29750, 1, 'Finalizado', 'Servicio finalizado con descuento aplicado.', 'PROMO2026'),
(2, 2, '2026-05-19', 'Tarjeta debito', 14400, 1, 'Finalizado', 'Voucher generado con promocion de junio.', 'DIADELPADRE2026');

-- =========================================================
-- service-estadisticas / pc_estadisticas
-- =========================================================
USE pc_estadisticas;

INSERT IGNORE INTO estadisticas
(id, categoria, fecha_generacion, reporte_json)
VALUES
(1, 'CLIENTES', '2026-06-20 20:00:00', '{"totalClientes":3,"clientesConOrdenes":2}'),
(2, 'EQUIPOS', '2026-06-20 20:05:00', '{"totalEquipos":3,"equiposReparados":2}'),
(3, 'VOUCHERS', '2026-06-20 20:10:00', '{"totalVouchers":2,"totalFacturado":44150}');

