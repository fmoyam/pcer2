-- PCer2 - Crear bases de datos desde cero
-- Ejecutar en MySQL/phpMyAdmin antes de levantar los microservicios.
-- Este archivo solo crea las bases. Las tablas las puede crear Spring Boot/JPA
-- porque los servicios tienen spring.jpa.hibernate.ddl-auto=update.

CREATE DATABASE IF NOT EXISTS pc_clientes
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_equipos
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_servicio
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_orden
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_voucher
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_estadisticas
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_hardware
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_software
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_mantenimiento
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_descuento
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS pc_seguridad
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

