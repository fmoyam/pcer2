-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2026 a las 00:24:06
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
-- Base de datos: `pc_equipos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` bigint(20) NOT NULL,
  `almacen_cantidad` int(11) NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo_cpu` varchar(255) DEFAULT NULL,
  `numero_serie` varchar(255) DEFAULT NULL,
  `placa_madre` varchar(255) DEFAULT NULL,
  `ram_cantidad` int(11) NOT NULL,
  `ram_frecuencia` int(11) NOT NULL,
  `veces_reparado` int(11) NOT NULL,
  `tipo_almacen_id` bigint(20) DEFAULT NULL,
  `tipo_equipo_id` bigint(20) DEFAULT NULL,
  `numeroserie` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `almacen_cantidad`, `marca`, `modelo_cpu`, `numero_serie`, `placa_madre`, `ram_cantidad`, `ram_frecuencia`, `veces_reparado`, `tipo_almacen_id`, `tipo_equipo_id`, `numeroserie`, `cliente_id`) VALUES
(1, 2000, 'MSI', 'Intel Core i9-13900K', NULL, 'MSI Z790', 32, 6000, 0, 1, 1, 'MSI-DESKTOP-001', 1),
(2, 512, 'Apple', 'Apple M3 Pro', NULL, 'Apple Custom', 18, 6400, 0, 1, 2, 'APPL-NOTE-002', 1),
(3, 8000, 'Dell', 'Intel Xeon W-3400', NULL, 'Dell Workstation', 128, 4800, 2, 2, 5, 'PRECISION-7865', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_almacen`
--

CREATE TABLE `tipo_almacen` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_almacen`
--

INSERT INTO `tipo_almacen` (`id`, `nombre`) VALUES
(1, 'HDD'),
(2, 'SSD'),
(3, 'eMMC'),
(4, 'SSHDD'),
(5, 'microSD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_equipo`
--

CREATE TABLE `tipo_equipo` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_equipo`
--

INSERT INTO `tipo_equipo` (`id`, `nombre`) VALUES
(1, 'PC / Desktop'),
(2, 'Notebook / Laptop'),
(3, 'All In One'),
(4, 'MiniPC / TinyClient'),
(5, 'Servidor');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKfjxv2lnw1kxp54pfvpoiixc8k` (`numero_serie`),
  ADD UNIQUE KEY `UKr0ovw1xnt2p241laucxv7v5i7` (`numeroserie`),
  ADD KEY `FKawrajgkd6a4le4bs84qo2kwya` (`tipo_almacen_id`),
  ADD KEY `FKtdseewpyhkudv1ft4hgrbbwtv` (`tipo_equipo_id`);

--
-- Indices de la tabla `tipo_almacen`
--
ALTER TABLE `tipo_almacen`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_equipo`
--
ALTER TABLE `tipo_equipo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo_almacen`
--
ALTER TABLE `tipo_almacen`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_equipo`
--
ALTER TABLE `tipo_equipo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `FKawrajgkd6a4le4bs84qo2kwya` FOREIGN KEY (`tipo_almacen_id`) REFERENCES `tipo_almacen` (`id`),
  ADD CONSTRAINT `FKtdseewpyhkudv1ft4hgrbbwtv` FOREIGN KEY (`tipo_equipo_id`) REFERENCES `tipo_equipo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
