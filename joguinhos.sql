-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 03-Set-2021 às 03:54
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdjava`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `joguinhos`
--

CREATE TABLE `joguinhos` (
  `dataJogo` varchar(20) DEFAULT NULL,
  `nomeJogo` tinytext DEFAULT NULL,
  `tipoJogo` tinytext DEFAULT NULL,
  `quantpJogo` float DEFAULT NULL,
  `idJogo` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `joguinhos`
--

INSERT INTO `joguinhos` (`dataJogo`, `nomeJogo`, `tipoJogo`, `quantpJogo`, `idJogo`) VALUES
('25/07/2017', 'Fortnite', 'Battle Royale', 350000000, 1),
('18/11/2011', 'Minecraft', 'Sandbox', 112000000, 2),
('23/08/2017', 'Free Fire', 'Battle Royale', 50000000, 3),
('27/10/2009', 'League of 																					  Legends', 'MOBA', 115000000, 4),
('25/10/20019', 'CoD: Modern Warfare', 'FPS', 50000000, 5),
('09/07/2013', 'DOTA 2', 'MOBA', 8665000, 6),
('01/09/2006', 'Roblox', 'Sandbox', 164000000, 7),
('03/05/2007', 'CrossFire', 'FPS', 8000000, 8),
('15/06/2018', 'Among Us', 'Dedução', 500000000, 9),
('21/08/2012', 'CS GO', 'FPS', 1300000, 10);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `joguinhos`
--
ALTER TABLE `joguinhos`
  ADD PRIMARY KEY (`idJogo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `joguinhos`
--
ALTER TABLE `joguinhos`
  MODIFY `idJogo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
