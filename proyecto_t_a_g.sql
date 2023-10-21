-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 21-10-2023 a las 19:17:30
-- Versión del servidor: 5.6.13
-- Versión de PHP: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `proyecto_t_a_g`
--
CREATE DATABASE IF NOT EXISTS `proyecto_t_a_g` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `proyecto_t_a_g`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `id_reserva` int(11) DEFAULT NULL,
  `tipo_estancia` text COLLATE latin1_spanish_ci,
  `coste_dia` int(11) DEFAULT NULL,
  `metros` float DEFAULT NULL,
  `reservado` int(11) DEFAULT NULL,
  `direccion` text COLLATE latin1_spanish_ci,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas_usuarios`
--

CREATE TABLE IF NOT EXISTS `reservas_usuarios` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `fecha_solicitud` datetime DEFAULT NULL,
  `fecha_fin_reserva` datetime DEFAULT NULL,
  `coste_reserva` float DEFAULT NULL,
  PRIMARY KEY (`id_reserva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` text COLLATE latin1_spanish_ci NOT NULL,
  `Apellido1` text COLLATE latin1_spanish_ci,
  `Apellido2` text COLLATE latin1_spanish_ci,
  `Email` text COLLATE latin1_spanish_ci NOT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `DNI` text COLLATE latin1_spanish_ci NOT NULL,
  `Password` text COLLATE latin1_spanish_ci NOT NULL,
  `Fecha_alta` datetime DEFAULT NULL,
  `Fecha_baja` datetime DEFAULT NULL,
  `Foto` longtext COLLATE latin1_spanish_ci,
  `Credito` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id`, `Nombre`, `Apellido1`, `Apellido2`, `Email`, `Telefono`, `DNI`, `Password`, `Fecha_alta`, `Fecha_baja`, `Foto`, `Credito`) VALUES
(1, 'test', 'testApellido', 'testApellido2', 'testEmail@gmail.com', NULL, '49758631D', 'passTest', NULL, NULL, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
