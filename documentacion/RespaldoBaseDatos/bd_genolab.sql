-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 16-12-2023 a las 06:14:25
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
-- Base de datos: `bd_genolab`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `id_articulo` int(11) NOT NULL,
  `id_ffarmaceutica` int(11) NOT NULL,
  `descripcion` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `codigo` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`id_articulo`, `id_ffarmaceutica`, `descripcion`, `codigo`, `activo`) VALUES
(1, 1, 'AMOXICILINA 500MG', 'CAMOX500', b'1'),
(2, 3, 'COMPLEJO B 2ML', 'ICOMP2', b'1'),
(3, 1, 'PARACETAMOL 500MG', 'CPARA500', b'1'),
(4, 3, 'VITAMINA C 5ML', 'IVITA5', b'1'),
(5, 3, 'DEXAMETASONA 500 MG', 'IDEXA500', b'1'),
(6, 3, 'DICLOFENACO 75 ML', 'IDICL75', b'0'),
(7, 3, 'FLUCONAZOL 30 ML INYECTABLE', 'IFLUC30', b'1'),
(8, 2, 'AZITROMICINA 250 MG CAPSULA', 'CAZIT250', b'1'),
(9, 1, 'ATORVASTATINA 200 MG', 'CATOR200', b'1'),
(10, 3, 'AMIKACINA 2 ML INYECTABLE', 'IAMIK2', b'1'),
(11, 10, 'SALBUTAMOL 500 MCG AEROSOL', 'ASALB500', b'1'),
(12, 10, 'BECLOMETASONA 600 MCG AEROSOL', 'ABECL600', b'1'),
(13, 11, 'GENTAMICINA OFTALMICA 15ML', 'CGENT15', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tipo_documento` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `numero_documento` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telefono` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `tipo_documento`, `numero_documento`, `telefono`, `email`, `activo`) VALUES
(1, 'Juan Ramos', 'NIT', '123456789', '555-1234', 'juan.ramos@example.com', b'1'),
(2, 'Jose Perez', 'NIT', '987654321', '555-5678', 'jose.perez@example.com', b'1'),
(3, 'Ana Fernandez', 'NIT', '456789012', '555-9012', 'ana.fernandez@example.com', b'1'),
(4, 'Fabio Camacho', 'CI', '539843', '74482092', 'fc.11@gmail.com', b'1'),
(5, 'Carlos Lopez', 'NIT', '453235', '75423453', 'cf@hotmail.com', b'1'),
(6, 'Maria Miranda', 'NIT', '543246742', '75889653', 'mm@gmail.com', b'1'),
(7, 'Jorge Borda', 'NIT', '546423', '76356324', 'jb@gmail.com', b'1'),
(8, 'Alvaro Cornejo', 'NIT', '6780456', '78356313', 'ac@gmail.com', b'1'),
(9, 'Daniela Arandia', 'NIT', '78343346', '69843245', 'da@gmail.com', b'1'),
(10, 'Jose Luis Mejia', 'NIT', '7455442', '76923532', 'jmm@gmail.com', b'1'),
(11, 'Ingresos', 'NIT', '5555', '55555', 'ingresos@genolab.com', b'1'),
(12, 'Gonzalo Camacho', 'NIT', '53434', '54354', 'gc@gc.com', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distrito`
--

CREATE TABLE `distrito` (
  `id_distrito` int(11) NOT NULL,
  `nombre` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `distrito`
--

INSERT INTO `distrito` (`id_distrito`, `nombre`, `activo`) VALUES
(1, 'Cochabamba', b'1'),
(2, 'La Paz', b'0'),
(3, 'Santa Cruz', b'1'),
(4, 'Trinidad', b'1'),
(5, 'El Alto', b'1'),
(6, 'Montero', b'0'),
(7, 'Oruro', b'1'),
(8, 'Potosi', b'1'),
(9, 'Sucre', b'1'),
(10, 'Tarija', b'1'),
(11, 'Yacuiba', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `forma_farmaceutica`
--

CREATE TABLE `forma_farmaceutica` (
  `id_ffarmaceutica` int(11) NOT NULL,
  `descripcion` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `forma_farmaceutica`
--

INSERT INTO `forma_farmaceutica` (`id_ffarmaceutica`, `descripcion`, `activo`) VALUES
(1, 'Comprimido', b'1'),
(2, 'Cápsula', b'1'),
(3, 'Inyectable', b'1'),
(4, 'Polvo Inyectable', b'1'),
(5, 'Dispositivo Medico no invasivo', b'1'),
(6, 'Dispositivo Medico invasivo', b'1'),
(7, 'Gel', b'1'),
(8, 'Crema', b'1'),
(9, 'Reactivo', b'1'),
(10, 'Aerosol', b'1'),
(11, 'Colirio', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laboratorio`
--

CREATE TABLE `laboratorio` (
  `id_laboratorio` int(11) NOT NULL,
  `nombre` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `procedencia` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `laboratorio`
--

INSERT INTO `laboratorio` (`id_laboratorio`, `nombre`, `procedencia`, `activo`) VALUES
(1, 'Sinochem', 'China', b'1'),
(2, 'Ningbo', 'China', b'1'),
(3, 'Laboratorios Chile', 'Chile', b'1'),
(4, 'Bayer', 'Alemania', b'1'),
(5, 'Actimed', 'Chile', b'1'),
(6, 'Genoma Lab', 'Peru', b'1'),
(7, 'Nanjing', 'China', b'1'),
(8, 'Figma', 'Brasil', b'1'),
(9, 'J&J', 'Estados Unidos', b'1'),
(10, 'Moderna', 'Estados Unidos', b'1'),
(11, 'Vita', 'Bolivia', b'1'),
(12, 'Samsung', 'Corea', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lote`
--

CREATE TABLE `lote` (
  `id_lote` int(11) NOT NULL,
  `id_articulo` int(11) NOT NULL,
  `id_laboratorio` int(11) NOT NULL,
  `fecha_fabricacion` date NOT NULL,
  `fecha_expiracion` date NOT NULL,
  `stock` int(11) NOT NULL,
  `precio_unitario` decimal(5,2) NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  `codigo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `lote`
--

INSERT INTO `lote` (`id_lote`, `id_articulo`, `id_laboratorio`, `fecha_fabricacion`, `fecha_expiracion`, `stock`, `precio_unitario`, `activo`, `codigo`) VALUES
(1, 1, 1, '2023-11-01', '2024-11-01', 281268, 0.50, b'1', '231101'),
(2, 2, 2, '2019-07-15', '2024-12-25', 452431, 0.40, b'1', '190715'),
(3, 3, 1, '2023-11-01', '2024-11-01', 484600, 0.30, b'1', '231101'),
(4, 4, 3, '2023-11-01', '2024-11-01', 495000, 0.20, b'1', '231101'),
(17, 5, 9, '2023-11-01', '2024-11-01', 999749, 0.50, b'1', '234325'),
(18, 6, 8, '2023-11-01', '2024-11-01', 499100, 0.40, b'1', '231101'),
(19, 7, 7, '2023-11-01', '2024-11-01', 600000, 0.30, b'1', '231101'),
(20, 8, 6, '2023-11-01', '2024-11-01', 696854, 0.20, b'1', '235434'),
(21, 9, 5, '2023-11-01', '2024-11-01', 592452, 0.30, b'1', '236564'),
(22, 4, 4, '2023-11-01', '2024-11-01', 700000, 0.20, b'1', '235456'),
(23, 5, 5, '2020-01-01', '2020-01-01', 49500, 0.50, b'1', '200101'),
(24, 7, 4, '2020-03-09', '2020-08-19', 39978, 0.50, b'1', '200309'),
(25, 11, 4, '2020-05-13', '2023-05-16', 44262, 10.00, b'1', '200513'),
(27, 12, 8, '2020-01-01', '2020-01-01', 58335, 3.00, b'1', '200101'),
(28, 10, 5, '2020-01-01', '2020-01-01', 49300, 2.00, b'1', '200101'),
(29, 13, 8, '2022-01-06', '2020-01-01', 6850, 4.00, b'1', '220106');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_de_venta`
--

CREATE TABLE `orden_de_venta` (
  `id_orden` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `importe_total` decimal(10,2) DEFAULT NULL,
  `numero_orden` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `orden_de_venta`
--

INSERT INTO `orden_de_venta` (`id_orden`, `id_sucursal`, `id_usuario`, `fecha_hora`, `importe_total`, `numero_orden`) VALUES
(96, 8, 17, '2023-12-15 21:55:41', 130.00, 6),
(97, 9, 17, '2023-12-15 21:56:21', 2410.00, 84),
(98, 1, 17, '2023-12-15 21:56:47', 545.00, 26),
(99, 10, 17, '2023-12-15 21:57:16', 9011.50, 53),
(100, 11, 17, '2023-12-15 22:00:13', -180000.00, 39),
(101, 4, 17, '2023-12-15 22:00:57', 378.00, 56),
(102, 9, 17, '2023-12-15 22:01:35', 2408.00, 24),
(103, 1, 17, '2023-12-15 22:02:18', 329.20, 59),
(104, 5, 17, '2023-12-15 22:02:44', 8240.00, 83),
(105, 8, 17, '2023-12-16 00:34:09', 4220.00, 77),
(106, 15, 17, '2023-12-16 01:03:53', 660.00, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`, `activo`) VALUES
(1, 'Administrador', b'1'),
(2, 'Ventas', b'1'),
(3, 'Regencia Farmaceutica', b'1'),
(4, 'Almacenes', b'1'),
(5, 'Secretaria', b'0'),
(6, 'Jefatura comercial', b'0'),
(7, 'Gerencia fincanciera', b'0'),
(8, 'Jefatura almacenes', b'0'),
(9, 'Gerencia general', b'0'),
(10, 'Contabilidad', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL,
  `id_distrito` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `direccion` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`id_sucursal`, `id_distrito`, `id_cliente`, `direccion`, `activo`) VALUES
(1, 1, 1, 'Blanco galindo', b'1'),
(2, 2, 2, 'Avenida B', b'1'),
(3, 3, 3, 'Calle C', b'1'),
(4, 1, 3, 'Blanco galindo', b'1'),
(5, 2, 4, 'Gaspar jurado', b'1'),
(6, 1, 5, 'Calle 3', b'1'),
(7, 5, 9, '6 de marzo ', b'1'),
(8, 1, 10, 'Beijing', b'1'),
(9, 1, 5, 'Libertador', b'1'),
(10, 2, 9, 'Zabaleta', b'1'),
(11, 2, 11, 'Empresa', b'1'),
(12, 6, 7, 'Calle 5', b'1'),
(13, 5, 8, '6 de marzo 43', b'1'),
(14, 2, 3, 'Calle potosi #4', b'1'),
(15, 8, 12, 'Calle 2 #4', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `id_transaccion` int(11) NOT NULL,
  `id_lote` int(11) NOT NULL,
  `id_orden` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `transaccion`
--

INSERT INTO `transaccion` (`id_transaccion`, `id_lote`, `id_orden`, `cantidad`, `fecha`) VALUES
(134, 1, 96, 200, '2023-12-15 21:55:41'),
(135, 21, 96, 100, '2023-12-15 21:55:41'),
(136, 20, 97, 500, '2023-12-15 21:56:21'),
(137, 21, 97, 700, '2023-12-15 21:56:21'),
(138, 27, 97, 700, '2023-12-15 21:56:21'),
(139, 25, 98, 50, '2023-12-15 21:56:47'),
(140, 17, 98, 90, '2023-12-15 21:56:47'),
(141, 25, 99, 900, '2023-12-15 21:57:16'),
(142, 17, 99, 23, '2023-12-15 21:57:16'),
(143, 27, 100, -60000, '2023-12-15 22:00:14'),
(144, 21, 101, 500, '2023-12-15 22:00:57'),
(145, 23, 101, 456, '2023-12-15 22:00:57'),
(146, 27, 102, 765, '2023-12-15 22:01:35'),
(147, 20, 102, 565, '2023-12-15 22:01:35'),
(148, 20, 103, 70, '2023-12-15 22:02:18'),
(149, 2, 103, 788, '2023-12-15 22:02:18'),
(150, 18, 104, 900, '2023-12-15 22:02:44'),
(151, 25, 104, 788, '2023-12-15 22:02:44'),
(152, 28, 105, 700, '2023-12-16 00:34:09'),
(153, 27, 105, 900, '2023-12-16 00:34:09'),
(154, 3, 105, 400, '2023-12-16 00:34:09'),
(155, 21, 106, 200, '2023-12-16 01:03:53'),
(156, 29, 106, 150, '2023-12-16 01:03:53');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apellido` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ci` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `foto` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `password`, `nombre`, `apellido`, `ci`, `email`, `fecha_nacimiento`, `foto`, `activo`) VALUES
(17, 1, '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', 'admin', '1234', 'admin@admin.com', '1970-01-01', NULL, b'1'),
(18, 2, 'd5664fb801b128b57c8882a7dde10884cd6179b22534649e333bd6075e336698', 'ventas', 'ventas', '12345', 'ventas@ventas.com', '1970-01-01', '', b'1'),
(19, 3, '2af25218644081634b09ac9eae11ab9a78e0a215ad0dc8400af705a7f88388b2', 'regencia', 'regencia', '12345', 'regencia@regencia.com', '1970-01-01', NULL, b'1'),
(20, 4, '0753e550b44ba820487ebfae1b0fb366a043bb33feda67dc76f7dc53299f31ba', 'almacenes', 'almacenes', '12345', 'almacenes@almacenes.com', '1970-01-01', '', b'1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id_articulo`),
  ADD UNIQUE KEY `id_articulo_UNIQUE` (`id_articulo`),
  ADD UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  ADD KEY `fk_articulo_ffarmaceutica_idx` (`id_ffarmaceutica`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `id_cliente_UNIQUE` (`id_cliente`);

--
-- Indices de la tabla `distrito`
--
ALTER TABLE `distrito`
  ADD PRIMARY KEY (`id_distrito`),
  ADD UNIQUE KEY `id_distrito_UNIQUE` (`id_distrito`);

--
-- Indices de la tabla `forma_farmaceutica`
--
ALTER TABLE `forma_farmaceutica`
  ADD PRIMARY KEY (`id_ffarmaceutica`),
  ADD UNIQUE KEY `id_ffarmaceutica_UNIQUE` (`id_ffarmaceutica`);

--
-- Indices de la tabla `laboratorio`
--
ALTER TABLE `laboratorio`
  ADD PRIMARY KEY (`id_laboratorio`),
  ADD UNIQUE KEY `id_laboratorio_UNIQUE` (`id_laboratorio`);

--
-- Indices de la tabla `lote`
--
ALTER TABLE `lote`
  ADD PRIMARY KEY (`id_lote`),
  ADD UNIQUE KEY `id_lote_UNIQUE` (`id_lote`),
  ADD KEY `fk_lote_articulo_idx` (`id_articulo`),
  ADD KEY `fk_lote_laboratorio_idx` (`id_laboratorio`);

--
-- Indices de la tabla `orden_de_venta`
--
ALTER TABLE `orden_de_venta`
  ADD PRIMARY KEY (`id_orden`),
  ADD UNIQUE KEY `id_orden_UNIQUE` (`id_orden`),
  ADD KEY `fk_orden_usuario_idx` (`id_usuario`),
  ADD KEY `fk_orden_sucursal_idx` (`id_sucursal`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `id_rol_UNIQUE` (`id_rol`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`id_sucursal`),
  ADD UNIQUE KEY `iddireccion_UNIQUE` (`id_sucursal`),
  ADD KEY `fk_direccion_cliente_idx` (`id_cliente`),
  ADD KEY `fk_direccion_distrito_idx` (`id_distrito`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`id_transaccion`),
  ADD UNIQUE KEY `id_transaccion_UNIQUE` (`id_transaccion`),
  ADD KEY `fk_transaccion_lote_idx` (`id_lote`),
  ADD KEY `fk_transaccion_orden_idx` (`id_orden`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `idusuario_UNIQUE` (`id_usuario`),
  ADD KEY `fk_usuario_rol_idx` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `distrito`
--
ALTER TABLE `distrito`
  MODIFY `id_distrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `forma_farmaceutica`
--
ALTER TABLE `forma_farmaceutica`
  MODIFY `id_ffarmaceutica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `laboratorio`
--
ALTER TABLE `laboratorio`
  MODIFY `id_laboratorio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `lote`
--
ALTER TABLE `lote`
  MODIFY `id_lote` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `orden_de_venta`
--
ALTER TABLE `orden_de_venta`
  MODIFY `id_orden` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `id_transaccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=157;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_ffarmaceutica` FOREIGN KEY (`id_ffarmaceutica`) REFERENCES `forma_farmaceutica` (`id_ffarmaceutica`);

--
-- Filtros para la tabla `lote`
--
ALTER TABLE `lote`
  ADD CONSTRAINT `fk_lote_articulo` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`),
  ADD CONSTRAINT `fk_lote_laboratorio` FOREIGN KEY (`id_laboratorio`) REFERENCES `laboratorio` (`id_laboratorio`);

--
-- Filtros para la tabla `orden_de_venta`
--
ALTER TABLE `orden_de_venta`
  ADD CONSTRAINT `fk_orden_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`),
  ADD CONSTRAINT `fk_orden_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `fk_sucursal_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `fk_sucursal_distrito` FOREIGN KEY (`id_distrito`) REFERENCES `distrito` (`id_distrito`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `fk_transaccion_lote` FOREIGN KEY (`id_lote`) REFERENCES `lote` (`id_lote`),
  ADD CONSTRAINT `fk_transaccion_orden` FOREIGN KEY (`id_orden`) REFERENCES `orden_de_venta` (`id_orden`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
