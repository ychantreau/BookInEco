-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 21 nov. 2019 à 11:05
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bookinecobdd`
--
CREATE DATABASE IF NOT EXISTS `bookinecobdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bookinecobdd`;

-- --------------------------------------------------------

--
-- Structure de la table `correspondre`
--

DROP TABLE IF EXISTS `correspondre`;
CREATE TABLE IF NOT EXISTS `correspondre` (
  `IDLIVRE` int(11) NOT NULL,
  `CODETHEME` varchar(256) NOT NULL,
  PRIMARY KEY (`IDLIVRE`,`CODETHEME`),
  KEY `FK_CORRESPONDRE2` (`CODETHEME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `factures`
--

DROP TABLE IF EXISTS `factures`;
CREATE TABLE IF NOT EXISTS `factures` (
  `NUMFACTURE` int(11) NOT NULL,
  `ID_UTILISATEUR` int(11) NOT NULL,
  `DATEFACTURE` date NOT NULL,
  PRIMARY KEY (`NUMFACTURE`),
  KEY `FK_RELATION_5` (`ID_UTILISATEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `IDLIVRE` int(11) NOT NULL,
  `ID_UTILISATEUR` int(11) NOT NULL,
  `AUTEUR` varchar(256) NOT NULL,
  `TITRE` varchar(256) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `PHOTO` longblob,
  `FORMAT` varchar(256) NOT NULL,
  `DATEAJOUTLIBRE` date NOT NULL,
  PRIMARY KEY (`IDLIVRE`),
  KEY `FK_RELATION_6` (`ID_UTILISATEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `panier_commande`
--

DROP TABLE IF EXISTS `panier_commande`;
CREATE TABLE IF NOT EXISTS `panier_commande` (
  `IDLIVRE` int(11) NOT NULL,
  `ID_UTILISATEUR` int(11) NOT NULL,
  `DATEDECOMMANDE` date NOT NULL,
  `STATUTLIVRAISON` char(20) NOT NULL,
  PRIMARY KEY (`IDLIVRE`,`ID_UTILISATEUR`),
  KEY `FK_RELATION_4` (`ID_UTILISATEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `themeslibre`
--

DROP TABLE IF EXISTS `themeslibre`;
CREATE TABLE IF NOT EXISTS `themeslibre` (
  `CODETHEME` varchar(256) NOT NULL,
  `INTITULETHEME` varchar(256) NOT NULL,
  PRIMARY KEY (`CODETHEME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID_UTILISATEUR` int(11) NOT NULL,
  `PRENOM` varchar(1024) NOT NULL,
  `NOM` varchar(256) NOT NULL,
  `PSEUDOUNIQUE` varchar(256) NOT NULL,
  `NBPOINTS` int(11) NOT NULL,
  `PREFERENCESLITERAIRE` varchar(256) DEFAULT NULL,
  `EMAILUTIISATEUR` varchar(256) NOT NULL,
  `MDPUTILISATEUR` varchar(256) NOT NULL,
  `AVATARUTILISATEUR` longblob,
  `ADRESSE` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID_UTILISATEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
