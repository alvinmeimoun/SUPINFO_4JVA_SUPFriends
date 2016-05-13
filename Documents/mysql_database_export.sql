-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Mer 11 Mai 2016 à 14:15
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `supfriends`
--
CREATE DATABASE IF NOT EXISTS `supfriends` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `supfriends`;

-- --------------------------------------------------------

--
-- Structure de la table `GROUPE`
--

CREATE TABLE `GROUPE` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `OWNERID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `GROUPE`
--

INSERT INTO `GROUPE` (`ID`, `NAME`, `OWNERID`) VALUES
(1, 'SUPINFO Nice', 1);

-- --------------------------------------------------------

--
-- Structure de la table `GROUPE_MEMBERS`
--

CREATE TABLE `GROUPE_MEMBERS` (
  `GroupId` bigint(20) NOT NULL,
  `UserId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `GROUPE_MEMBERS`
--

INSERT INTO `GROUPE_MEMBERS` (`GroupId`, `UserId`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `USER`
--

CREATE TABLE `USER` (
  `ID` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONENUMBER` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `USER`
--

INSERT INTO `USER` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `LATITUDE`, `LONGITUDE`, `PASSWORD`, `PHONENUMBER`, `USERNAME`) VALUES
(1, 'alvin.meimoun@supinfo.com', 'Alvin', 'Meimoun', 43.696, 7.2656, '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', '0600000001', 'alvin'),
(2, 'antonin.malfatti@supinfo.com', 'Antonin', 'Malfatti', 43.671, 7.1761, '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', '0600000002', 'antonin');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `GROUPE`
--
ALTER TABLE `GROUPE`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `GROUPE_MEMBERS`
--
ALTER TABLE `GROUPE_MEMBERS`
  ADD PRIMARY KEY (`GroupId`,`UserId`),
  ADD KEY `FK_GROUPE_MEMBERS_UserId` (`UserId`);

--
-- Index pour la table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `GROUPE`
--
ALTER TABLE `GROUPE`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `GROUPE_MEMBERS`
--
ALTER TABLE `GROUPE_MEMBERS`
  ADD CONSTRAINT `FK_GROUPE_MEMBERS_GroupId` FOREIGN KEY (`GroupId`) REFERENCES `GROUPE` (`ID`),
  ADD CONSTRAINT `FK_GROUPE_MEMBERS_UserId` FOREIGN KEY (`UserId`) REFERENCES `USER` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
