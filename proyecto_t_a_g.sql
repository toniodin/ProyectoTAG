-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-11-2023 a las 15:09:06
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
  `id_reserva` int(11) NOT NULL DEFAULT '0',
  `tipo_estancia` text COLLATE latin1_spanish_ci,
  `coste_dia` int(11) DEFAULT NULL,
  `metros` float DEFAULT NULL,
  `reservado` int(11) DEFAULT NULL,
  `direccion` text COLLATE latin1_spanish_ci,
  `id_usuario` int(11) DEFAULT NULL,
  `imagen` longtext COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_reserva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id_reserva`, `tipo_estancia`, `coste_dia`, `metros`, `reservado`, `direccion`, `id_usuario`, `imagen`) VALUES
(1, 'Hotel', 5, 55, 0, 'C/La palma el palmoso', NULL, 'https://images.hola.com/imagenes/decoracion/20230425230358/dormitorios-inspirados-en-habitaciones-hoteles-am/1-237-28/habitaciones-hotel-5a-a.jpg');

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
  `Domicilio` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id`, `Nombre`, `Apellido1`, `Apellido2`, `Email`, `Telefono`, `DNI`, `Password`, `Fecha_alta`, `Fecha_baja`, `Foto`, `Credito`, `Domicilio`) VALUES
(1, 'test', 'testApellido', 'testApellido2', 'testEmail@gmail.com', NULL, '49758631D', 'passTest', NULL, NULL, NULL, NULL, NULL),
(2, 'toni', 'chiquero', 'encinas', 'toni@mail.com', NULL, '125485F', '1234', '2023-10-30 19:11:34', NULL, NULL, NULL, 'hola'),
(3, 'aaaa', 'aaaa', 'aaaa', 'aa', NULL, '8594545F', 'aaa', '2023-10-30 19:20:38', NULL, NULL, NULL, 'hvgbvbnvvbvnm'),
(4, 'a', 'a', 'a', 'a', NULL, 'a', 'a', '2023-11-02 15:40:01', NULL, NULL, 740, 'Indica tu domicilio');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
