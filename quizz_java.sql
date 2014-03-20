-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 11 Mars 2014 à 09:02
-- Version du serveur: 5.5.33
-- Version de PHP: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `quizz_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `code_question` int(11) NOT NULL,
  `code_quizz` int(11) NOT NULL,
  `reponse_joueur` varchar(255) NOT NULL,
  PRIMARY KEY (`code_question`,`code_quizz`),
  KEY `code_quizz` (`code_quizz`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `code_joueur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_joueur` varchar(255) NOT NULL,
  `mail_joueur` varchar(255) NOT NULL,
  `passwd_joueur` varchar(255) NOT NULL,
  `admin` int(1) NOT NULL,
  PRIMARY KEY (`code_joueur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`code_joueur`, `nom_joueur`, `mail_joueur`, `passwd_joueur`, `admin`) VALUES
(1, 'Czekala Hugo', 'hugoBG54@hotmail.fr', 'cindy', 0),
(2, 'Vecchio Quentin', 'vecchioquentin@hotmail.fr', 'vecchio', 0),
(4, '', '', '', 0),
(6, 'root', 'root', 'root', 1),
(7, 'dd', 'dddddd', 'dddd', 0),
(8, 'quentin1', 'quentin@google.com', 'testt', 0);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `code_question` int(11) NOT NULL AUTO_INCREMENT,
  `texte_question` varchar(1000) NOT NULL,
  `reponse_joueur` varchar(255) NOT NULL,
  PRIMARY KEY (`code_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`code_question`, `texte_question`, `reponse_joueur`) VALUES
(1, 'Combien l''Italie compte-elle d''étoile ?', '4'),
(2, 'Date de sortie du macintosh ?', '1984'),
(3, 'Capitale de la France ?', 'paris'),
(4, 'La couleur du cheval blanc d''Henri IV ?', 'blanc');

-- --------------------------------------------------------

--
-- Structure de la table `quizz`
--

CREATE TABLE `quizz` (
  `code_quizz` int(11) NOT NULL AUTO_INCREMENT,
  `date_quizz` date NOT NULL,
  `nb_questions_quizz` int(11) NOT NULL,
  `code_joueur` int(11) NOT NULL,
  PRIMARY KEY (`code_quizz`),
  KEY `code_joueur` (`code_joueur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `quizz`
--

INSERT INTO `quizz` (`code_quizz`, `date_quizz`, `nb_questions_quizz`, `code_joueur`) VALUES
(1, '2014-02-21', 3, 1),
(2, '2014-02-21', 2, 1),
(3, '2014-02-22', 32, 2);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`code_question`) REFERENCES `question` (`code_question`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`code_quizz`) REFERENCES `quizz` (`code_quizz`);

--
-- Contraintes pour la table `quizz`
--
ALTER TABLE `quizz`
  ADD CONSTRAINT `quizz_ibfk_1` FOREIGN KEY (`code_joueur`) REFERENCES `joueur` (`code_joueur`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
