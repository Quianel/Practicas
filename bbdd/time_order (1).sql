-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-05-2026 a las 17:21:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `time_order`
--

CREATE DATABASE `TIME_ORDER`;
USE `TIME_ORDER`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion_proyecto`
--

CREATE TABLE `asignacion_proyecto` (
  `id_asignacion_proyecto` int(11) NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `id_trabajador` int(11) DEFAULT NULL,
  `fecha_asignacion` date DEFAULT NULL,
  `fecha_fin_asignacion` date DEFAULT NULL,
  `motivo_fin` varchar(200) DEFAULT NULL,
  `activa` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignacion_proyecto`
--

INSERT INTO `asignacion_proyecto` (`id_asignacion_proyecto`, `id_proyecto`, `id_trabajador`, `fecha_asignacion`, `fecha_fin_asignacion`, `motivo_fin`, `activa`) VALUES
(1, 1, 1, '2026-05-05', '2026-06-05', 'motivo', 0),
(2, 1, 1, '2026-05-05', '2026-06-05', 'motivo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion_tarea`
--

CREATE TABLE `asignacion_tarea` (
  `id_asignacion_tarea` int(11) NOT NULL,
  `id_tarea_proyecto` int(11) DEFAULT NULL,
  `id_trabajador` int(11) DEFAULT NULL,
  `fecha_asignacion` date DEFAULT NULL,
  `fecha_fin_asignacion` date DEFAULT NULL,
  `motivo_fin` varchar(200) DEFAULT NULL,
  `activa` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignacion_tarea`
--

INSERT INTO `asignacion_tarea` (`id_asignacion_tarea`, `id_tarea_proyecto`, `id_trabajador`, `fecha_asignacion`, `fecha_fin_asignacion`, `motivo_fin`, `activa`) VALUES
(1, 1, 1, '2026-06-05', '2026-06-05', 'motivo', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria_tiempo`
--

CREATE TABLE `auditoria_tiempo` (
  `id_auditoria` int(11) NOT NULL,
  `id_registro` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `accion` varchar(30) DEFAULT NULL,
  `fecha_evento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auditoria_tiempo`
--

INSERT INTO `auditoria_tiempo` (`id_auditoria`, `id_registro`, `id_usuario`, `accion`, `fecha_evento`) VALUES
(1, 1, 1, 'Creacion', '2026-05-05'),
(2, 2, 1, 'Edicion', '2026-05-05'),
(3, 3, 1, 'Borrado logico', '2026-05-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo_tareas`
--

CREATE TABLE `catalogo_tareas` (
  `id_tarea_catalogo` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `solo_senior` tinyint(1) DEFAULT NULL,
  `id_tipo_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `catalogo_tareas`
--

INSERT INTO `catalogo_tareas` (`id_tarea_catalogo`, `nombre`, `solo_senior`, `id_tipo_proyecto`) VALUES
(1, 'DESARROLLO', 0, 1),
(2, 'MANTENIMIENTO', 0, 1),
(3, 'SERVICIOS', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_proyecto`
--

CREATE TABLE `estado_proyecto` (
  `id_estado_proyecto` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_proyecto`
--

INSERT INTO `estado_proyecto` (`id_estado_proyecto`, `nombre`) VALUES
(1, 'ACTIVO'),
(2, 'PAUSADO'),
(3, 'FINALIZADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_tarea`
--

CREATE TABLE `estado_tarea` (
  `id_estado_tarea` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_tarea`
--

INSERT INTO `estado_tarea` (`id_estado_tarea`, `nombre`) VALUES
(1, 'PENDIENTE'),
(2, 'EN CURSO'),
(3, 'PAUSADA'),
(4, 'TERMINADA'),
(5, 'CANCELADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nivel_experiencia`
--

CREATE TABLE `nivel_experiencia` (
  `id_nivel` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `nivel_experiencia`
--

INSERT INTO `nivel_experiencia` (`id_nivel`, `nombre`) VALUES
(1, 'JUNIOR'),
(2, 'SENIOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil_laboral`
--

CREATE TABLE `perfil_laboral` (
  `id_perfil` int(3) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `perfil_laboral`
--

INSERT INTO `perfil_laboral` (`id_perfil`, `nombre`) VALUES
(1, 'ANALISTA'),
(2, 'PROGRAMADOR'),
(3, 'CALL CENTER'),
(4, 'ASISTENCIA TECNICA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso`
--

CREATE TABLE `permiso` (
  `id_permiso` int(11) NOT NULL,
  `clave` varchar(100) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permiso`
--

INSERT INTO `permiso` (`id_permiso`, `clave`, `descripcion`) VALUES
(1, 'CREAR_PROYECTOS', 'PERMISO QUE TE PERMITE CREAR PROYECTOS'),
(2, 'CREAR_TRABAJADORES', 'PERMISO QUE TE PERMITE CREAR TRABAJADORES'),
(3, 'ASIGNAR_PERSONAL', 'PERMISO QUE TE PERMITE ASIGNAR PERSONAL'),
(4, 'REGISTRAR_TIEMPO', 'PERMISO QUE TE PERMITE REGISTRAR EL TIEMPO'),
(5, 'VER_REPORTES_PROPRIOS', 'PERMISO QUE TE PERMITE VER TUS PROPIOS REPORTES'),
(6, 'VER_REPORTES_GLOBALES', 'PERMISO QUE TE PERMITE VER LOS REPORTES GLOBALES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `id_proyecto` int(3) NOT NULL,
  `codigo_interno` varchar(100) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_limite` date DEFAULT NULL,
  `es_generico` tinyint(1) DEFAULT NULL,
  `id_tipo_proyecto` int(11) DEFAULT NULL,
  `id_estado_proyecto` int(11) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`id_proyecto`, `codigo_interno`, `nombre`, `fecha_inicio`, `fecha_limite`, `es_generico`, `id_tipo_proyecto`, `id_estado_proyecto`, `descripcion`) VALUES
(1, 'DES', 'Trabajador Prueba Proyecto 1', '2026-05-05', '2026-05-07', 0, 1, 2, 'DESARROLLO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_tiempo`
--

CREATE TABLE `registro_tiempo` (
  `id_registro` int(11) NOT NULL,
  `id_trabajador` int(11) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `id_tarea_proyecto` int(11) DEFAULT NULL,
  `fecha_hora_inicio` datetime DEFAULT NULL,
  `fecha_hora_fin` datetime DEFAULT NULL,
  `minutos_totales` int(11) DEFAULT NULL,
  `modo_registro` varchar(50) DEFAULT NULL,
  `comentario` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registro_tiempo`
--

INSERT INTO `registro_tiempo` (`id_registro`, `id_trabajador`, `id_proyecto`, `id_tarea_proyecto`, `fecha_hora_inicio`, `fecha_hora_fin`, `minutos_totales`, `modo_registro`, `comentario`) VALUES
(1, 1, 1, NULL, '2026-05-05 15:30:00', '2026-05-05 20:00:00', 100, 'slider', 'Primer registro'),
(2, 1, 1, NULL, '2026-05-05 15:30:00', '2026-05-05 15:30:00', 200, 'manual', 'Segundo registro'),
(3, 1, 1, NULL, '2026-05-05 15:30:00', '2026-05-05 15:30:00', 300, 'manual', 'Tercer registro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_permiso`
--

CREATE TABLE `rol_permiso` (
  `id_rol` int(1) NOT NULL,
  `id_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol_permiso`
--

INSERT INTO `rol_permiso` (`id_rol`, `id_permiso`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 6),
(2, 4),
(2, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_sistema`
--

CREATE TABLE `rol_sistema` (
  `id_rol` int(1) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol_sistema`
--

INSERT INTO `rol_sistema` (`id_rol`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Trabajador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea_proyecto`
--

CREATE TABLE `tarea_proyecto` (
  `id_tarea_proyecto` int(11) NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `id_tarea_catalogo` int(11) DEFAULT NULL,
  `id_tarea_padre` int(11) DEFAULT NULL,
  `id_estado_tarea` int(11) DEFAULT NULL,
  `nombre_visible` varchar(20) DEFAULT NULL,
  `activa` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarea_proyecto`
--

INSERT INTO `tarea_proyecto` (`id_tarea_proyecto`, `id_proyecto`, `id_tarea_catalogo`, `id_tarea_padre`, `id_estado_tarea`, `nombre_visible`, `activa`) VALUES
(1, 1, 1, NULL, 5, 'Tarea proyecto 1', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_proyecto`
--

CREATE TABLE `tipo_proyecto` (
  `id_tipo_proyecto` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_proyecto`
--

INSERT INTO `tipo_proyecto` (`id_tipo_proyecto`, `nombre`) VALUES
(1, 'DESARROLLO'),
(2, 'MANTENIMIENTO'),
(3, 'SERVICIOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id_trabajador` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `password_hash` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `id_rol` int(1) DEFAULT NULL,
  `id_perfil` int(3) DEFAULT NULL,
  `id_nivel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id_trabajador`, `nombre`, `correo`, `password_hash`, `activo`, `id_rol`, `id_perfil`, `id_nivel`) VALUES
(1, 'Trabajador Prueba 1', 'Trabajador1@prueba.com', '$2a$10$mRoX9DiV60RhP.r6xLNmJORNhzwJ0dpVSlK1GvxZlTlcVtHgNnHNO', 0, 2, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignacion_proyecto`
--
ALTER TABLE `asignacion_proyecto`
  ADD PRIMARY KEY (`id_asignacion_proyecto`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_trabajador` (`id_trabajador`);

--
-- Indices de la tabla `asignacion_tarea`
--
ALTER TABLE `asignacion_tarea`
  ADD PRIMARY KEY (`id_asignacion_tarea`),
  ADD KEY `id_tarea_proyecto` (`id_tarea_proyecto`),
  ADD KEY `id_trabajador` (`id_trabajador`);

--
-- Indices de la tabla `auditoria_tiempo`
--
ALTER TABLE `auditoria_tiempo`
  ADD PRIMARY KEY (`id_auditoria`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_registro` (`id_registro`);

--
-- Indices de la tabla `catalogo_tareas`
--
ALTER TABLE `catalogo_tareas`
  ADD PRIMARY KEY (`id_tarea_catalogo`),
  ADD KEY `id_tipo_proyecto` (`id_tipo_proyecto`);

--
-- Indices de la tabla `estado_proyecto`
--
ALTER TABLE `estado_proyecto`
  ADD PRIMARY KEY (`id_estado_proyecto`);

--
-- Indices de la tabla `estado_tarea`
--
ALTER TABLE `estado_tarea`
  ADD PRIMARY KEY (`id_estado_tarea`);

--
-- Indices de la tabla `nivel_experiencia`
--
ALTER TABLE `nivel_experiencia`
  ADD PRIMARY KEY (`id_nivel`);

--
-- Indices de la tabla `perfil_laboral`
--
ALTER TABLE `perfil_laboral`
  ADD PRIMARY KEY (`id_perfil`);

--
-- Indices de la tabla `permiso`
--
ALTER TABLE `permiso`
  ADD PRIMARY KEY (`id_permiso`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`id_proyecto`),
  ADD KEY `id_tipo_proyecto` (`id_tipo_proyecto`),
  ADD KEY `id_estado_proyecto` (`id_estado_proyecto`);

--
-- Indices de la tabla `registro_tiempo`
--
ALTER TABLE `registro_tiempo`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `id_trabajador` (`id_trabajador`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_tarea_proyecto` (`id_tarea_proyecto`);

--
-- Indices de la tabla `rol_permiso`
--
ALTER TABLE `rol_permiso`
  ADD PRIMARY KEY (`id_rol`,`id_permiso`),
  ADD KEY `id_permiso` (`id_permiso`);

--
-- Indices de la tabla `rol_sistema`
--
ALTER TABLE `rol_sistema`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `tarea_proyecto`
--
ALTER TABLE `tarea_proyecto`
  ADD PRIMARY KEY (`id_tarea_proyecto`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_tarea_catalogo` (`id_tarea_catalogo`),
  ADD KEY `id_tarea_padre` (`id_tarea_padre`),
  ADD KEY `id_estado_tarea` (`id_estado_tarea`);

--
-- Indices de la tabla `tipo_proyecto`
--
ALTER TABLE `tipo_proyecto`
  ADD PRIMARY KEY (`id_tipo_proyecto`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id_trabajador`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_perfil` (`id_perfil`),
  ADD KEY `id_nivel` (`id_nivel`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignacion_proyecto`
--
ALTER TABLE `asignacion_proyecto`
  MODIFY `id_asignacion_proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `asignacion_tarea`
--
ALTER TABLE `asignacion_tarea`
  MODIFY `id_asignacion_tarea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `auditoria_tiempo`
--
ALTER TABLE `auditoria_tiempo`
  MODIFY `id_auditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  MODIFY `id_proyecto` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `registro_tiempo`
--
ALTER TABLE `registro_tiempo`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tarea_proyecto`
--
ALTER TABLE `tarea_proyecto`
  MODIFY `id_tarea_proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id_trabajador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignacion_proyecto`
--
ALTER TABLE `asignacion_proyecto`
  ADD CONSTRAINT `asignacion_proyecto_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  ADD CONSTRAINT `asignacion_proyecto_ibfk_2` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`);

--
-- Filtros para la tabla `asignacion_tarea`
--
ALTER TABLE `asignacion_tarea`
  ADD CONSTRAINT `asignacion_tarea_ibfk_1` FOREIGN KEY (`id_tarea_proyecto`) REFERENCES `tarea_proyecto` (`id_tarea_proyecto`),
  ADD CONSTRAINT `asignacion_tarea_ibfk_2` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`);

--
-- Filtros para la tabla `auditoria_tiempo`
--
ALTER TABLE `auditoria_tiempo`
  ADD CONSTRAINT `auditoria_tiempo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `trabajador` (`id_trabajador`),
  ADD CONSTRAINT `auditoria_tiempo_ibfk_2` FOREIGN KEY (`id_registro`) REFERENCES `registro_tiempo` (`id_registro`);

--
-- Filtros para la tabla `catalogo_tareas`
--
ALTER TABLE `catalogo_tareas`
  ADD CONSTRAINT `catalogo_tareas_ibfk_1` FOREIGN KEY (`id_tipo_proyecto`) REFERENCES `tipo_proyecto` (`id_tipo_proyecto`);

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`id_tipo_proyecto`) REFERENCES `tipo_proyecto` (`id_tipo_proyecto`),
  ADD CONSTRAINT `proyecto_ibfk_2` FOREIGN KEY (`id_estado_proyecto`) REFERENCES `estado_proyecto` (`id_estado_proyecto`);

--
-- Filtros para la tabla `registro_tiempo`
--
ALTER TABLE `registro_tiempo`
  ADD CONSTRAINT `registro_tiempo_ibfk_1` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`),
  ADD CONSTRAINT `registro_tiempo_ibfk_2` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  ADD CONSTRAINT `registro_tiempo_ibfk_3` FOREIGN KEY (`id_tarea_proyecto`) REFERENCES `tarea_proyecto` (`id_tarea_proyecto`);

--
-- Filtros para la tabla `rol_permiso`
--
ALTER TABLE `rol_permiso`
  ADD CONSTRAINT `rol_permiso_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol_sistema` (`id_rol`),
  ADD CONSTRAINT `rol_permiso_ibfk_2` FOREIGN KEY (`id_permiso`) REFERENCES `permiso` (`id_permiso`);

--
-- Filtros para la tabla `tarea_proyecto`
--
ALTER TABLE `tarea_proyecto`
  ADD CONSTRAINT `tarea_proyecto_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  ADD CONSTRAINT `tarea_proyecto_ibfk_2` FOREIGN KEY (`id_tarea_catalogo`) REFERENCES `catalogo_tareas` (`id_tarea_catalogo`),
  ADD CONSTRAINT `tarea_proyecto_ibfk_3` FOREIGN KEY (`id_tarea_padre`) REFERENCES `tarea_proyecto` (`id_tarea_proyecto`),
  ADD CONSTRAINT `tarea_proyecto_ibfk_4` FOREIGN KEY (`id_estado_tarea`) REFERENCES `estado_tarea` (`id_estado_tarea`);

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol_sistema` (`id_rol`),
  ADD CONSTRAINT `trabajador_ibfk_2` FOREIGN KEY (`id_perfil`) REFERENCES `perfil_laboral` (`id_perfil`),
  ADD CONSTRAINT `trabajador_ibfk_3` FOREIGN KEY (`id_nivel`) REFERENCES `nivel_experiencia` (`id_nivel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
