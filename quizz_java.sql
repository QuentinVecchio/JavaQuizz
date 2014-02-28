-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 21, 2014 at 04:32 PM
-- Server version: 5.5.33
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `quizz_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
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
-- Table structure for table `joueur`
--

CREATE TABLE `joueur` (
  `code_joueur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_joueur` varchar(255) NOT NULL,
  `mail_joueur` varchar(255) NOT NULL,
  `passwd_joueur` varchar(255) NOT NULL,
  PRIMARY KEY (`code_joueur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `joueur`
--

INSERT INTO `joueur` (`code_joueur`, `nom_joueur`, `mail_joueur`, `passwd_joueur`) VALUES
(1, 'Czekala Hugo', 'hugoBG54@hotmail.fr', 'cindy'),
(2, 'Vecchio Quentin', 'vecchioquentin@hotmail.fr', 'vecchio');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `code_question` int(11) NOT NULL AUTO_INCREMENT,
  `texte_question` varchar(1000) NOT NULL,
  `reponse_joueur` varchar(255) NOT NULL,
  PRIMARY KEY (`code_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`code_question`, `texte_question`, `reponse_joueur`) VALUES
(1, 'Combien l''Italie compte-elle d''étoile ?', '4'),
(2, 'Date de sortie du macintosh ?', '1984'),
(3, 'Capitale de la France ?', 'paris'),
(4, 'La couleur du cheval blanc d''Henri IV ?', 'blanc'),
(5, 'Prénom de Madame Zertal ?', 'lynda');

-- --------------------------------------------------------

--
-- Table structure for table `quizz`
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
-- Dumping data for table `quizz`
--

INSERT INTO `quizz` (`code_quizz`, `date_quizz`, `nb_questions_quizz`, `code_joueur`) VALUES
(1, '2014-02-21', 3, 1),
(2, '2014-02-21', 2, 1),
(3, '2014-02-22', 32, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`code_quizz`) REFERENCES `quizz` (`code_quizz`),
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`code_question`) REFERENCES `question` (`code_question`);

--
-- Constraints for table `quizz`
--
ALTER TABLE `quizz`
  ADD CONSTRAINT `quizz_ibfk_1` FOREIGN KEY (`code_joueur`) REFERENCES `joueur` (`code_joueur`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
