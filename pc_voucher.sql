-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2026 a las 02:15:25
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
-- Base de datos: `pc_voucher`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voucher_trabajo`
--

CREATE TABLE `voucher_trabajo` (
  `id` bigint(20) NOT NULL,
  `cantidad_servicios` int(11) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `orden_id` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `voucher_trabajo`
--

INSERT INTO `voucher_trabajo` (`id`, `cantidad_servicios`, `estado`, `fecha_emision`, `metodo_pago`, `observacion`, `orden_id`, `total`) VALUES
(1, 1, 'Pagado', '2026-05-15', 'Efectivo', 'Voucher generado por mantención completa de equipo. Incluye limpieza de hardware y cambio de pasta térmica.', 1, 35000),
(2, 1, 'Pagado', '2026-05-15', 'Transferencia', 'Voucher generado por cambio de pieza. Servicio asociado a instalación de disco duro para arreglo RAID 0.', 2, 20000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `voucher_trabajo`
--
ALTER TABLE `voucher_trabajo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `voucher_trabajo`
--
ALTER TABLE `voucher_trabajo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
