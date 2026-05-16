-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2026 a las 00:23:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pc_orden`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes_trabajo`
--

CREATE TABLE `ordenes_trabajo` (
  `id` bigint(20) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `descripcion_problema` varchar(255) DEFAULT NULL,
  `equipo_id` bigint(20) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_entrega_estimada` date DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `precio_total` double DEFAULT NULL,
  `servicio_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ordenes_trabajo`
--

INSERT INTO `ordenes_trabajo` (`id`, `cliente_id`, `descripcion_problema`, `equipo_id`, `estado`, `fecha_entrega_estimada`, `fecha_ingreso`, `precio_total`, `servicio_id`) VALUES
(1, 1, 'Equipo muy sucio. Presenta estrangulamiento térmico en GPU y CPU.', 1, 'Estética 8/10  -  Funcionalidad 4/10', '2026-05-18', '2026-05-15', 35000, 3),
(2, 2, 'Requiere agregar un disco duro para un arreglo en RAID 0.', 3, 'Estética 9/10  -  Funcionalidad 9/10', '2026-05-16', '2026-05-12', 20000, 2),
(3, 1, 'Notebook presenta lentitud y requiere mantención completa', 2, 'Ingresada', '2026-05-18', '2026-05-15', 35000, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ordenes_trabajo`
--
ALTER TABLE `ordenes_trabajo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ordenes_trabajo`
--
ALTER TABLE `ordenes_trabajo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
