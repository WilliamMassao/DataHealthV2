-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Nov-2021 às 02:39
-- Versão do servidor: 10.4.21-MariaDB
-- versão do PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `datahealth_db`
--
CREATE DATABASE datahealth_db;
-- --------------------------------------------------------

--
-- Estrutura da tabela `medicamento`
--

CREATE TABLE `medicamento` (
  `Id` int(255) NOT NULL,
  `NomeComercial` varchar(80) NOT NULL,
  `NomeGenerico` varchar(80) NOT NULL,
  `LinkBula` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `medicamento`
--

INSERT INTO `medicamento` (`Id`, `NomeComercial`, `NomeGenerico`, `LinkBula`) VALUES
(11, 'Loratadina', 'Loratadina Generico', 'www.loratadina.com.br'),
(12, 'Dipirona', 'Dipirona Generico', 'www.Dipirona.com.br'),
(13, 'SimioCorte', 'SimioCorte Generico', 'www.SimioCorte.com.br'),
(14, 'Dorflex', 'Dorflex Generico', 'www.Dorflex.com.br');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medicamento_paciente`
--

CREATE TABLE `medicamento_paciente` (
  `IdPaciente` int(255) NOT NULL,
  `IdRemedio` int(255) NOT NULL,
  `DataPrescricao` date NOT NULL,
  `Dosagem` varchar(80) NOT NULL,
  `Recorrencia` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `medicamento_paciente`
--

INSERT INTO `medicamento_paciente` (`IdPaciente`, `IdRemedio`, `DataPrescricao`, `Dosagem`, `Recorrencia`) VALUES
(12, 11, '2021-11-17', '', ''),
(12, 12, '2021-11-17', '', ''),
(12, 14, '2021-11-17', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_paciente`
--

CREATE TABLE `usuario_paciente` (
  `Nome` varchar(80) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `TipoSanguineo` varchar(3) NOT NULL,
  `Email` varchar(80) NOT NULL,
  `Telefone` varchar(12) NOT NULL,
  `Senha` varchar(255) NOT NULL,
  `Id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario_paciente`
--

INSERT INTO `usuario_paciente` (`Nome`, `Cpf`, `TipoSanguineo`, `Email`, `Telefone`, `Senha`, `Id`) VALUES
('Henrique Galli', '44406367802', 'A+', 'gali@otario.com', '11992467442', '123', 11),
('William Saito', '86999186073', 'O+', 'Wiliam@gmail.com', '11975203550', '123', 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_profissional`
--

CREATE TABLE `usuario_profissional` (
  `Nome` varchar(80) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `Senha` varchar(80) NOT NULL,
  `Matricula` varchar(80) NOT NULL,
  `Cargo` varchar(80) NOT NULL,
  `RegistroProfissional` varchar(80) NOT NULL,
  `Id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario_profissional`
--

INSERT INTO `usuario_profissional` (`Nome`, `Cpf`, `Senha`, `Matricula`, `Cargo`, `RegistroProfissional`, `Id`) VALUES
('Renan Oliveira', '1440779007', 'admin', '51440779007', 'Médico', '51440779007', 11);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`Id`);

--
-- Índices para tabela `medicamento_paciente`
--
ALTER TABLE `medicamento_paciente`
  ADD KEY `IdPaciente` (`IdPaciente`),
  ADD KEY `IdRemedio` (`IdRemedio`);

--
-- Índices para tabela `usuario_paciente`
--
ALTER TABLE `usuario_paciente`
  ADD PRIMARY KEY (`Id`);

--
-- Índices para tabela `usuario_profissional`
--
ALTER TABLE `usuario_profissional`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `medicamento`
--
ALTER TABLE `medicamento`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `usuario_paciente`
--
ALTER TABLE `usuario_paciente`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `usuario_profissional`
--
ALTER TABLE `usuario_profissional`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `medicamento_paciente`
--
ALTER TABLE `medicamento_paciente`
  ADD CONSTRAINT `medicamento_paciente_ibfk_1` FOREIGN KEY (`IdPaciente`) REFERENCES `usuario_paciente` (`Id`),
  ADD CONSTRAINT `medicamento_paciente_ibfk_2` FOREIGN KEY (`IdRemedio`) REFERENCES `medicamento` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
