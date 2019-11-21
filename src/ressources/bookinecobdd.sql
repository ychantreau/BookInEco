-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 21 nov. 2019 à 13:34
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
  `idLivre` int(11) NOT NULL,
  `codeTheme` varchar(256) NOT NULL,
  PRIMARY KEY (`idLivre`,`codeTheme`),
  KEY `FK_CORRESPONDRE2` (`codeTheme`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `factures`
--

DROP TABLE IF EXISTS `factures`;
CREATE TABLE IF NOT EXISTS `factures` (
  `numFacture` int(11) NOT NULL,
  `id_Utilisateur` int(11) NOT NULL,
  `dateFacture` date NOT NULL,
  PRIMARY KEY (`numFacture`),
  KEY `FK_RELATION_5` (`id_Utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `idLivre` int(11) NOT NULL,
  `id_Utilisateur` int(11) NOT NULL,
  `auteur` varchar(256) NOT NULL,
  `titre` varchar(256) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `photo` longblob,
  `format` varchar(256) NOT NULL,
  `dateAjoutLivre` date NOT NULL,
  PRIMARY KEY (`idLivre`),
  KEY `FK_RELATION_6` (`id_Utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `panier_commande`
--

DROP TABLE IF EXISTS `panier_commande`;
CREATE TABLE IF NOT EXISTS `panier_commande` (
  `idLivre` int(11) NOT NULL,
  `id_Utilisateur` int(11) NOT NULL,
  `dateDeCommande` date NOT NULL,
  `statutLivraison` char(20) NOT NULL,
  PRIMARY KEY (`idLivre`,`id_Utilisateur`),
  KEY `FK_RELATION_4` (`id_Utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `themeslibre`
--

DROP TABLE IF EXISTS `themeslibre`;
CREATE TABLE IF NOT EXISTS `themeslibre` (
  `codeTheme` varchar(256) NOT NULL,
  `intituleTheme` varchar(256) NOT NULL,
  PRIMARY KEY (`codeTheme`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_Utilisateur` int(11) NOT NULL,
  `prenom` varchar(1024) NOT NULL,
  `nom` varchar(256) NOT NULL,
  `pseudoUnique` varchar(256) NOT NULL,
  `nbPoints` int(11) NOT NULL,
  `preferencesLitteraires` varchar(256) DEFAULT NULL,
  `emailUtilisateur` varchar(256) NOT NULL,
  `mdpUtilisateur` varchar(256) NOT NULL,
  `avatarUtilisateur` longblob,
  `adresse` varchar(1024) DEFAULT NULL,
  `role` int(2) NOT NULL COMMENT '0:admin ; 1:user',
  PRIMARY KEY (`id_Utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
